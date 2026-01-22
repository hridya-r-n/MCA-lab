/*
 * Copyright (c) 1999, 2024, Oracle and/or its affiliates. All rights reserved.
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

package javax.management;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;

/**
 * This class represents numbers that are arguments to relational constraints.
 * A NumericValueExp may be used anywhere a ValueExp is required.
 *
 * <p>The <b>serialVersionUID</b> of this class is <code>-4679739485102359104L</code>.
 *
 * @serial include
 *
 * @since 1.5
 */
class NumericValueExp extends QueryEval implements ValueExp {

    private static final long serialVersionUID = -4679739485102359104L;
    /**
     * @serialField val Number The numeric value
     *
     * <p>The <b>serialVersionUID</b> of this class is <code>-4679739485102359104L</code>.
     */
    private static final ObjectStreamField[] serialPersistentFields = {
        new ObjectStreamField("val", Number.class)
    };
    private Number val = 0.0;

    /**
     * Basic constructor.
     */
    public NumericValueExp() {
    }

    /** Creates a new NumericValue representing the numeric literal @{code val}.*/
    NumericValueExp(Number val)
    {
      this.val = val;
    }

    /**
     * Returns a double numeric value
     */
    public double doubleValue()  {
      if (val instanceof Long || val instanceof Integer)
      {
        return (double)(val.longValue());
      }
      return val.doubleValue();
    }

    /**
     * Returns a long numeric value
     */
    public long longValue()  {
      if (val instanceof Long || val instanceof Integer)
      {
        return val.longValue();
      }
      return (long)(val.doubleValue());
    }

    /**
     * Returns true is if the numeric value is a long, false otherwise.
     */
    public boolean isLong()  {
        return (val instanceof Long || val instanceof Integer);
    }

    /**
     * Returns the string representing the object
     */
    public String toString()  {
      if (val == null)
        return "null";
      if (val instanceof Long || val instanceof Integer)
      {
        return Long.toString(val.longValue());
      }
      double d = val.doubleValue();
      if (Double.isInfinite(d))
          return (d > 0) ? "(1.0 / 0.0)" : "(-1.0 / 0.0)";
      if (Double.isNaN(d))
          return "(0.0 / 0.0)";
      return Double.toString(d);
    }

    /**
     * Applies the ValueExp on a MBean.
     *
     * @param name The name of the MBean on which the ValueExp will be applied.
     *
     * @return  The <CODE>ValueExp</CODE>.
     *
     * @exception BadStringOperationException
     * @exception BadBinaryOpValueExpException
     * @exception BadAttributeValueExpException
     * @exception InvalidApplicationException
     */
    public ValueExp apply(ObjectName name)
            throws BadStringOperationException, BadBinaryOpValueExpException,
                   BadAttributeValueExpException, InvalidApplicationException {
        return this;
    }

    /**
     * Deserializes a {@link NumericValueExp} from an {@link ObjectInputStream}.
     */
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
      in.defaultReadObject();
    }


    /**
     * Serializes a {@link NumericValueExp} to an {@link ObjectOutputStream}.
     */
    private void writeObject(ObjectOutputStream out)
            throws IOException {
      out.defaultWriteObject();
    }

    @Deprecated
    public void setMBeanServer(MBeanServer s) {
        super.setMBeanServer(s);
    }

 }
