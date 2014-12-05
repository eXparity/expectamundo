package org.exparity.expactamundo.core;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodProxy;


/**
 * @author Stewart Bissett
 */
public interface PrototypeInterceptor {

	Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy, Prototype<?> currentPrototype) throws Throwable;

}
