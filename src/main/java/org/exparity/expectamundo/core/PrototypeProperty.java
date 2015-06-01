package org.exparity.expectamundo.core;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.commons.lang.StringUtils;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrototypeProperty implements PrototypeValue {

	private static final Logger LOG = LoggerFactory.getLogger(PrototypeProperty.class);

	private final Method method;
	private final MethodProxy proxy;
	private final Object[] args;
	private final Map<String, Class<?>> typeParameters;
	private PrototypeProperty parent;

	public PrototypeProperty(final PrototypeProperty parent, final Method method, final MethodProxy proxy,
			final Object[] args, final Map<String, Class<?>> typeParameters) {
		this.parent = parent;
		this.method = method;
		this.args = args;
		this.proxy = proxy;
		this.typeParameters = typeParameters;
	}

	/**
	 * Return the value of this property on the given object
	 */
	public Object getValue(final Object actual) {
		try {
			return proxy.invoke(parent != null ? parent.getValue(actual) : actual, args);
		} catch (final IndexOutOfBoundsException e) {
			Class<?> actualReturnType = getReturnType(method);
			return isProxiable(actualReturnType) ? createNullProxy(actualReturnType) : null;
		} catch (ClassCastException e) {
			throw e;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	private Class<?> getReturnType(Method method) {
		Type genericType = method.getGenericReturnType();
		if (genericType instanceof Class) {
			return ((Class<?>) genericType);
		} else if (genericType instanceof ParameterizedType) {
			return (Class<?>) ((ParameterizedType) genericType).getRawType();
		} else if (genericType instanceof TypeVariable<?>) {
			return typeParameters.get(((TypeVariable<?>) genericType).getName());
		} else {
			throw new RuntimeException("Failed to get return type for '" + genericType + "'");
		}
	}

	private <T> T createNullProxy(final Class<T> type) {
		return createProxyInstance(createProxy(type));
	}

	private <T> T createProxyInstance(final Class<T> proxyType) {
		Objenesis instantiatorFactory = new ObjenesisStd();
		ObjectInstantiator<T> instanceFactory = instantiatorFactory.getInstantiatorOf(proxyType);
		T instance = instanceFactory.newInstance();
		LOG.debug("Produce Proxy Instance [{}] for [{}]", System.identityHashCode(instance), proxyType.getName());
		return instance;
	}

	@SuppressWarnings("unchecked")
	private <T> Class<T> createProxy(final Class<T> rawType) {
		Enhancer classFactory = new Enhancer();
		if (rawType.isInterface()) {
			classFactory.setInterfaces(new Class[] { rawType });
		} else {
			classFactory.setSuperclass(rawType);
		}
		classFactory.setCallbackType(MethodInterceptor.class);
		Class<T> proxyType = classFactory.createClass();
		Enhancer.registerCallbacks(proxyType, new Callback[] { new MethodInterceptor() {

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				return null;
			}
		} });
		return proxyType;
	}

	private boolean isProxiable(Class<?> type) {
		return !Modifier.isFinal(type.getModifiers());
	}

	@Override
	public String getLabel() {
		return getPath();
	}

	public String getPath() {
		return parent != null ? parent.getPath() + "." + stripPrefix(method.getName()) : stripPrefix(method.getName());
	}

	private String stripPrefix(final String name) {
		return args.length == 0 ? name + "()" : name + "(" + StringUtils.join(args, ",") + ")";
	}

	public Type getGenericReturnType() {
		return method.getGenericReturnType();
	}

	public Map<String, Class<?>> getGenericTypeArguments() {
		Map<String, Class<?>> parameterizedTypes = new HashMap<String, Class<?>>();
		if (method.getGenericReturnType() instanceof ParameterizedType) {
			Type[] typeArguments = ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments();
			TypeVariable<?>[] typeKeys = ((Class<?>) ((ParameterizedType) method.getGenericReturnType()).getRawType())
					.getTypeParameters();
			for (int i = 0; i < typeKeys.length; ++i) {
				if (typeArguments[i] instanceof Class) {
					parameterizedTypes.put(typeKeys[i].getName(), (Class<?>) typeArguments[i]);
				} else if (typeArguments[i] instanceof TypeVariable) {
					Class<?> resolvedType = resolveType((TypeVariable<?>) typeArguments[i]);
					if (resolvedType != null) {
						parameterizedTypes.put(typeKeys[i].getName(), resolvedType);
					}
				}
			}
			return parameterizedTypes;
		}
		return parameterizedTypes;
	}

	private Class<?> resolveType(final TypeVariable<?> type) {
		return typeParameters.get(type.getName());
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " ["
				+ method.getDeclaringClass().getSimpleName()
				+ ":"
				+ proxy.getSignature().getName()
				+ "]";
	}
}