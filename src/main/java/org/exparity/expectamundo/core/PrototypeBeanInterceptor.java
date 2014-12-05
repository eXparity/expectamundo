
package org.exparity.expectamundo.core;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Stewart Bissett
 */
public class PrototypeBeanInterceptor implements PrototypeInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(PrototypeBeanInterceptor.class);
	private final PrototypeFactory factory;

	public PrototypeBeanInterceptor(final PrototypeFactory factory) {
		this.factory = factory;
	}

	@Override
	public Object intercept(final Object obj, final Method method, final Object[] args, final MethodProxy proxy, final Prototype<?> currentPrototype) throws Throwable {
		PrototypeProperty activeProperty = new PrototypeProperty(currentPrototype.getParentProperty(), method, proxy, args);
		currentPrototype.setActiveProperty(activeProperty);
		if (isProxiableMethod(method)) {
			Class<?> returnType = getClassForPrototype(activeProperty, currentPrototype);
			if (returnType.isPrimitive() || Modifier.isFinal(returnType.getModifiers())) {
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

	private boolean isProxiableMethod(final Method method) {
		switch (method.getName()) {
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
