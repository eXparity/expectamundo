
package org.exparity.expectamundo.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Prototype<T> implements Prototyped<T>, MethodInterceptor {

	private static Logger LOG = LoggerFactory.getLogger(Prototype.class);
	private final Class<T> rawType;
	private final PrototypeInterceptor interceptor;
	private final List<Prototyped<?>> children = new ArrayList<Prototyped<?>>();
	private final List<PrototypePropertyMatcher> expectations = new ArrayList<PrototypePropertyMatcher>();
	private final Map<String, Class<?>> typeParameters;
	private PrototypeProperty parentProperty = null, activeProperty = null;

	public Prototype(final PrototypeProperty parentProperty, final Class<T> rawType, final PrototypeInterceptor proxier) {
		this(parentProperty, rawType, new HashMap<String, Class<?>>(), proxier);
	}

	public Prototype(final PrototypeProperty parentProperty, final Class<T> rawType, final Map<String, Class<?>> typeParameters, final PrototypeInterceptor proxier) {
		this.parentProperty = parentProperty;
		this.rawType = rawType;
		this.interceptor = proxier;
		this.typeParameters = typeParameters;
	}

	public Prototype(final Class<T> rawType, final Map<String, Class<?>> typeParameters, final PrototypeInterceptor proxier) {
		this.rawType = rawType;
		this.interceptor = proxier;
		this.typeParameters = typeParameters;
	}

	public void addChild(final Prototyped<?> child) {
		this.children.add(child);
	}

	public PrototypeProperty getParentProperty() {
		return parentProperty;
	}

	public void setActiveProperty(final PrototypeProperty activeProperty) {
		LOG.debug("SetProp {}", activeProperty);
		this.activeProperty = activeProperty;
	}

	public PrototypeProperty getActiveProperty() {
		return activeProperty;
	}

	@Override
	public List<PrototypePropertyMatcher> getExpectations() {
		List<PrototypePropertyMatcher> expectations = new ArrayList<PrototypePropertyMatcher>();
		expectations.addAll(this.expectations);
		for (Prototyped<?> child : children) {
			expectations.addAll(child.getExpectations());
		}
		return expectations;
	}

	public void addExpectation(final PrototypePropertyMatcher expecation) {
		LOG.debug("AddExpd {}", expecation);
		this.expectations.add(expecation);
	}

	@Override
	public Class<T> getRawType() {
		return rawType;
	}

	public Map<String, Class<?>> getTypeParameters() {
		return typeParameters;
	}

	@Override
	public Object intercept(final Object obj, final Method method, final Object[] args, final MethodProxy proxy) throws Throwable {
		PrototypeMatcherContext.setCurrentPrototype(this);
		if (method.getDeclaringClass().equals(Prototyped.class)) {
			return getClass().getMethod(method.getName(), parameterTypes(args)).invoke(this);
		} else {
			return this.interceptor.intercept(obj, method, args, proxy, this);
		}
	}

	private Class<?>[] parameterTypes(final Object[] args) {
		if (args == null || args.length == 0) {
			return new Class<?>[0];
		} else {
			Class<?>[] types = new Class[args.length];
			for (int i = 0; i < args.length; ++i) {
				types[i] = args[i].getClass();
			}
			return types;
		}
	}

	@Override
	public String toString() {
		return "Prototype [" + rawType.getSimpleName() + "]";
	}
}