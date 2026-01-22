/*
 * Copyright (c) 2011, 2025, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package jdk.xml.internal;

/**
 * This is the base class for features and properties
 */
public abstract class FeaturePropertyBase {

    /**
     * States of the settings of a property, in the order: default value, value
     * set by FEATURE_SECURE_PROCESSING, jaxp.properties file, jaxp system
     * properties, and jaxp api properties
     */
    public static enum State {
        //this order reflects the overriding order
        DEFAULT, FSP, JAXPDOTPROPERTIES, SYSTEMPROPERTY, APIPROPERTY
    }


    /**
     * Values of the properties as defined in enum Properties
     */
    String[] values = null;
    /**
     * States of the settings for each property in Properties above
     */
    State[] states = {State.DEFAULT, State.DEFAULT, State.DEFAULT};


    /**
     * Set the value for a specific property.
     *
     * @param property the property
     * @param state the state of the property
     * @param value the value of the property
     */
    public void setValue(Enum<?> property, State state, String value) {
        //only update if it shall override
        if (state.compareTo(states[property.ordinal()]) >= 0) {
            values[property.ordinal()] = value;
            states[property.ordinal()] = state;
        }
    }

    /**
     * Set the value of a property by its index
     * @param index the index of the property
     * @param state the state of the property
     * @param value the value of the property
     */
    public void setValue(int index, State state, String value) {
        //only update if it shall override
        if (state.compareTo(states[index]) >= 0) {
            values[index] = value;
            states[index] = state;
        }
    }

    /**
     * Set value by property name and state
     * @param propertyName property name
     * @param state the state of the property
     * @param value the value of the property
     * @return true if the property is managed by the security property manager;
     *         false if otherwise.
     */
    public boolean setValue(String propertyName, State state, Object value) {
        int index = getIndex(propertyName);
        if (index > -1) {
            setValue(index, state, (String)value);
            return true;
        }
        return false;
    }

    /**
     * Set value by property name and state
     * @param propertyName property name
     * @param state the state of the property
     * @param value the value of the property
     * @return true if the property is managed by the security property manager;
     *         false if otherwise.
     */
    public boolean setValue(String propertyName, State state, boolean value) {
        int index = getIndex(propertyName);
        if (index > -1) {
            if (value) {
                setValue(index, state, JdkConstants.FEATURE_TRUE);
            } else {
                setValue(index, state, JdkConstants.FEATURE_FALSE);
            }
            return true;
        }
        return false;
    }

    /**
     * Return the value of the specified property
     *
     * @param property the property
     * @return the value of the property
     */
    public String getValue(Enum<?> property) {
        return values[property.ordinal()];
    }

    /**
     * Return the value of the specified property
     *
     * @param property the property
     * @return the value of the property
     */
    public String getValue(String property) {
        int index = getIndex(property);
        if (index > -1) {
            return getValueByIndex(index);
        }
        return null;
    }

    /**
     * Return the value of the specified property.
     *
     * @param propertyName the property name
     * @return the value of the property as a string. If a property is managed
     * by this manager, its value shall not be null.
     */
    public String getValueAsString(String propertyName) {
        int index = getIndex(propertyName);
        if (index > -1) {
            return getValueByIndex(index);
        }

        return null;
    }

    /**
     * Return the value of a property by its ordinal
     * @param index the index of a property
     * @return value of a property
     */
    public String getValueByIndex(int index) {
        return values[index];
    }

    /**
     * Get the index by property name
     * @param propertyName property name
     * @return the index of the property if found; return -1 if not
     */
    public abstract int getIndex(String propertyName);

    public <E extends Enum<E>> int getIndex(Class<E> property, String propertyName) {
        for (Enum<E> enumItem : property.getEnumConstants()) {
            if (enumItem.toString().equals(propertyName)) {
                //internally, ordinal is used as index
                return enumItem.ordinal();
            }
        }
        return -1;
    };


    /**
     * Reads the system property, and sets the value if successful.
     *
     * @param property the property
     * @param systemProperty the name of the system property
     */
    boolean getSystemProperty(Enum<?> property, String systemProperty) {
        String value = System.getProperty(systemProperty);
        if (value != null) {
            values[property.ordinal()] = value;
            states[property.ordinal()] = State.SYSTEMPROPERTY;
            return true;
        }
        return false;
    }

    /**
     * Reads the property from the JAXP Configuration File.
     *
     * @param property the property
     * @param sysPropertyName the name of the system property
     * @return true if the property is read successfully from the JAXP Config,
     * false otherwise.
     */
    boolean getPropertyConfig(Enum<?> property, String sysPropertyName) {
        String value = SecuritySupport.readConfig(sysPropertyName);
        if (value != null) {
            values[property.ordinal()] = value;
            states[property.ordinal()] = State.JAXPDOTPROPERTIES;
            return true;
        }
        return false;
    }
}
