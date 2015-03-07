package org.exparity.expectamundo.core;


/**
 * @author Stewart Bissett
 */
public interface PrototypeValue {

	Object getValue(Object actual);

	String getLabel();

}
