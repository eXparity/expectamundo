
package org.exparity.expectamundo.core;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author Stewart Bissett
 */
public class PrototypeListInterceptor extends PrototypeBeanInterceptor {

	public PrototypeListInterceptor(final PrototypeFactory factory) {
		super(factory);
	}

	@Override
	public Object intercept(final Object obj, final Method method, final Object[] args, final MethodProxy proxy, final Prototype<?> currentPrototype) throws Throwable {
		return super.intercept(obj, method, args, proxy, currentPrototype);
	}
}
