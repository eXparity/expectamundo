
package org.exparity.expectamundo.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Prototype<T> implements Prototyped<T>, MethodInterceptor, PrototypeValue {

	private static Logger LOG = LoggerFactory.getLogger(Prototype.class);

	private final Class<T> rawType;
	private final PrototypeInterceptor interceptor;
	private final List<Prototyped<?>> children = new ArrayList<Prototyped<?>>();
	private final List<PrototypeValueMatcher> expectations = new ArrayList<PrototypeValueMatcher>();
	private final Map<String, Class<?>> typeParameters;
	private PrototypeProperty parentProperty = null, activeProperty = null;

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
		LOG.debug("Current Property {}", activeProperty == null ? "NONE" : activeProperty.getPath());
		this.activeProperty = activeProperty;
	}

	public PrototypeProperty getActiveProperty() {
		return activeProperty;
	}

	@Override
	public List<PrototypeValueMatcher> getExpectations() {
		List<PrototypeValueMatcher> expectations = new ArrayList<PrototypeValueMatcher>();
		expectations.addAll(this.expectations);
		for (Prototyped<?> child : children) {
			expectations.addAll(child.getExpectations());
		}
		return expectations;
	}

	public void addExpectation(final PrototypeValueMatcher expecation) {
		LOG.info("Expects {} {}", expecation.getPropertyPath(), expecation.getExpectation());
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
		if (method.getDeclaringClass().equals(Prototyped.class) || method.getDeclaringClass().equals(PrototypeValue.class)) {
			return method.invoke(this, args);
		} else {
			return this.interceptor.intercept(obj, method, args, proxy, this);
		}
	}

	@Override
	public String getLabel() {
		return this.rawType.getSimpleName();
	}

	@Override
	public Object getValue(final Object actual) {
		return this;
	}

	@Override
	public String toString() {
		return "Prototype [" + rawType.getSimpleName() + "]";
	}
}