
package org.exparity.expectamundo.core;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.MethodProxy;

/**
 * @author Stewart Bissett
 */
public class PrototypeInterceptorImpl implements PrototypeInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(PrototypeInterceptorImpl.class);
	private final PrototypeFactory factory;

	public PrototypeInterceptorImpl(final PrototypeFactory factory) {
		this.factory = factory;
	}

	@Override
	public Object intercept(final Object obj, final Method method, final Object[] args, final MethodProxy proxy, final Prototype<?> currentPrototype) throws Throwable {

		PrototypeProperty activeProperty = new PrototypeProperty(currentPrototype.getParentProperty(), method, proxy, args, currentPrototype.getTypeParameters());
		if (isInvokedByLogger(activeProperty)) {
			LOG.debug("Discard Method [{}] invoke via logger", method);
			return proxy.invokeSuper(obj, args);
		}

		PrototypeMatcherContext.setCurrentPrototype(currentPrototype);
		currentPrototype.setActiveProperty(activeProperty);
		if (isProxiableMethod(method)) {
			Class<?> returnType = getClassForPrototype(activeProperty, currentPrototype);
			if (isValueType(returnType)) {
				return null;
			} else {
				Object child = factory.createPrototype(activeProperty, currentPrototype);
				currentPrototype.addChild((Prototyped<?>) child);
				return child;
			}
		} else {
			LOG.debug("Discard Method [{}]", method);
			return proxy.invokeSuper(obj, args);
		}
	}

	private boolean isValueType(final Class<?> returnType) {
		return returnType.isPrimitive() || Modifier.isFinal(returnType.getModifiers()) || isJava8AndTypeNotProxiable(returnType) || isNumber(returnType);
	}

	private boolean isNumber(final Class<?> returnType) {
		return Number.class.isAssignableFrom(returnType);
	}

	private boolean isJava8AndTypeNotProxiable(final Class<?> returnType) {
		return System.getProperty("java.version").startsWith("1.8") && Date.class.isAssignableFrom(returnType);
	}

	private boolean isInvokedByLogger(final PrototypeProperty activeProperty) {
		boolean logging = false;
		for (StackTraceElement x : Thread.currentThread().getStackTrace()) {
			if (x.getClassName().startsWith("org.slf4j")) {
				LOG.debug("Discard {} during Logging", activeProperty);
				logging = true;
			}
		}
		return logging;
	}

	private boolean isProxiableMethod(final Method method) {
		switch (method.getName()) {
			case "equals":
			case "iterator":
			case "finalize":
			case "hashCode":
			case "toString":
				return false;
			default:
				return method.getReturnType() != null;
		}
	}

	private Class<?> getClassForPrototype(final PrototypeProperty activeProperty, final Prototype<?> currentPrototype) {
		Type genericType = activeProperty.getGenericReturnType();
		if (genericType instanceof Class) {
			return (Class<?>) genericType;
		} else if (genericType instanceof ParameterizedType) {
			return (Class<?>) ((ParameterizedType) genericType).getRawType();
		} else if (genericType instanceof TypeVariable<?>) {
			return currentPrototype.getTypeParameters().get(((TypeVariable<?>) genericType).getName());
		} else {
			throw new RuntimeException("Failed to get prototype class for '" + genericType + "'");
		}
	}
}
