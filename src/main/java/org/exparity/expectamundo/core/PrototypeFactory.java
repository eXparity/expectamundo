package org.exparity.expectamundo.core;

import static java.lang.reflect.Modifier.isFinal;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Stewart Bissett
 */
public class PrototypeFactory {

	private static final Logger LOG = LoggerFactory.getLogger(PrototypeFactory.class);

	private final ProxyFactory proxyFactory = new ProxyFactory();

	public <T> T createPrototype(final Class<T> type) {
		if (isFinal(type.getModifiers())) {
			throw new IllegalArgumentException("Final classes cannot be prototyped");
		} else if (isGenericType(type)) {
			throw new IllegalArgumentException(
					"Use Expectamundo.prototype(final TypeReference<T> typeRef) method to create prototypes for generic types. See javadocs on method for example.");
		} else {
			PrototypeInterceptor interceptor = createInterceptor(type);
			LOG.debug("Produce Interceptor [{}] for [{}]", interceptor.getClass().getSimpleName(), type.getName());
			T proxy = createProxy(new Prototype<T>(type, getGenericTypeArguments(type), interceptor));
			LOG.info("Proxied {} using [{}]", type.getSimpleName(), proxy);
			return proxy;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object createPrototype(final Type genericType) {
		Class<?> type = getClassForPrototype(genericType);
		if (isFinal(type.getModifiers())) {
			throw new IllegalArgumentException("Final classes cannot be prototyped");
		}
		PrototypeInterceptor interceptor = createInterceptor(type);
		LOG.debug("Produce Interceptor [{}] for [{}]", interceptor.getClass().getSimpleName(), type.getName());
		Prototype prototype = new Prototype(type, getGenericTypeArguments(genericType), interceptor);
		Object proxy = createProxy(prototype);
		LOG.info("Proxied {} using [{}]", type.getSimpleName(), proxy);
		return proxy;
	}

	public <T> T createPrototype(final Class<T> type,
			final PrototypeProperty activeProperty,
			final Prototype<?> currentPrototype) {
		if (isFinal(type.getModifiers())) {
			throw new IllegalArgumentException("Final classes cannot be prototyped");
		}
		PrototypeInterceptor interceptor = createInterceptor(type);
		LOG.debug("Produce Interceptor [{}] for [{}]", interceptor.getClass().getSimpleName(), type.getName());
		Prototype<T> prototype = new Prototype<T>(
				activeProperty,
					type,
					activeProperty.getGenericTypeArguments(),
					interceptor);
		T proxy = createProxy(prototype);
		LOG.debug("Produce Proxy [{}] for [{}]", proxy, type.getName());
		return proxy;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object createPrototype(final PrototypeProperty activeProperty, final Prototype<?> currentPrototype) {
		Class<?> type = getClassForPrototype(activeProperty, currentPrototype);
		if (isFinal(type.getModifiers())) {
			throw new IllegalArgumentException("Final classes cannot be prototyped");
		}
		PrototypeInterceptor interceptor = createInterceptor(type);
		LOG.debug("Produce Interceptor [{}] for [{}]", interceptor.getClass().getSimpleName(), type.getName());
		Prototype prototype = new Prototype(activeProperty, type, activeProperty.getGenericTypeArguments(), interceptor);
		Object proxy = createProxy(prototype);
		LOG.debug("Produce Proxy [{}] for [{}]", proxy, type.getName());
		return proxy;
	}

	private Class<?> getClassForPrototype(final PrototypeProperty activeProperty, final Prototype<?> currentPrototype) {
		final Type genericType = activeProperty.getGenericReturnType();
		if (genericType instanceof Class) {
			return (Class<?>) genericType;
		} else if (genericType instanceof ParameterizedType) {
			return (Class<?>) ((ParameterizedType) genericType).getRawType();
		} else if (genericType instanceof TypeVariable<?>) {
			return currentPrototype.getTypeParameters().get(((TypeVariable<?>) genericType).getName());
		} else {
			throw new RuntimeException("Failed to get protype class for '" + genericType + "'");
		}
	}

	private Class<?> getClassForPrototype(final Type genericType) {
		if (genericType instanceof Class) {
			return (Class<?>) genericType;
		} else if (genericType instanceof ParameterizedType) {
			return (Class<?>) ((ParameterizedType) genericType).getRawType();
		} else {
			throw new RuntimeException("Failed to get protype class for '" + genericType + "'");
		}
	}

	private <T> PrototypeInterceptor createInterceptor(final Class<T> type) {
		return new PrototypeInterceptorImpl(this);
	}

	private <T> T createProxy(final Prototype<T> prototype) {
		return proxyFactory.createProxy(
				prototype.getRawType(),
					prototype,
					Prototyped.class,
					PrototypeValue.class);
	}

	private Map<String, Class<?>> getGenericTypeArguments(final Class<?> type) {
		if (!isGenericType(type) && type.getGenericSuperclass() instanceof ParameterizedType) {
			return getGenericTypeArguments(type.getGenericSuperclass());
		} else {
			return new HashMap<>();
		}
	}

	private Map<String, Class<?>> getGenericTypeArguments(final Type genericType) {
		Map<String, Class<?>> parameterizedTypes = new HashMap<String, Class<?>>();
		if (genericType instanceof ParameterizedType) {
			Type[] typeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
			TypeVariable<?>[] typeKeys = ((Class<?>) ((ParameterizedType) genericType).getRawType())
					.getTypeParameters();
			for (int i = 0; i < typeKeys.length; ++i) {
				parameterizedTypes.put(typeKeys[i].getName(), (Class<?>) typeArguments[i]);
			}
			return parameterizedTypes;
		}
		return parameterizedTypes;
	}

	private <T> boolean isGenericType(final Class<T> type) {
		return type.getTypeParameters() != null && type.getTypeParameters().length > 0;
	}
}
