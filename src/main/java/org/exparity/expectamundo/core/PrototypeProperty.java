package org.exparity.expectamundo.core;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.commons.lang.StringUtils;
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
		} catch (final IndexOutOfBoundsException|NullPointerException e) {
			return createNullMethodProxy(method);
		} catch (ClassCastException e) {
			throw e;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	private Object createNullMethodProxy(Method method) {
		Class<?> returnType = getReturnType(method);
		if ( isProxiable(returnType) ) {
			return new ProxyFactory().createProxy(returnType, new MethodInterceptor() {
			
				@Override
				public Object intercept(Object obj, Method inner, Object[] args, MethodProxy proxy) throws Throwable {
					return createNullMethodProxy(inner);
				}
			});
		} else {
			return null;
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
		return new ProxyFactory().createProxy(type, new MethodInterceptor() {

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				return null;
			}
		});
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