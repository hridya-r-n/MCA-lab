/*
 * Copyright (c) 2000, 2024, Oracle and/or its affiliates. All rights reserved.
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
 */

package sun.jvm.hotspot.code;

import java.io.*;
import java.nio.charset.StandardCharsets;

import sun.jvm.hotspot.utilities.*;

/** <P> Classes used for serializing debugging information. These
    abstractions are introducted to provide symmetric read and write
    operations. </P>

    <P>
    <UL>
    <LI> ScopeValue: describes the value of a variable/expression in a scope
    <UL>
    <LI> LocationValue: describes a value in a given location (in frame or register)
    <LI> ConstantValue: describes a constant
    </UL>
    </UL>
    </P> */

public abstract class ScopeValue {
  // Package private enumeration values (FIXME: read from target VM)
  static final int LOCATION_CODE        = 0;
  static final int CONSTANT_INT_CODE    = 1;
  static final int CONSTANT_OOP_CODE    = 2;
  static final int CONSTANT_LONG_CODE   = 3;
  static final int CONSTANT_DOUBLE_CODE = 4;
  static final int CONSTANT_OBJECT_CODE = 5;
  static final int CONSTANT_OBJECT_ID_CODE = 6;
  static final int AUTO_BOX_OBJECT_CODE = 7;
  static final int MARKER_CODE = 8;
  static final int OBJECT_MERGE_CODE = 9;

  public boolean isLocation()       { return false; }
  public boolean isConstantInt()    { return false; }
  public boolean isConstantDouble() { return false; }
  public boolean isConstantLong()   { return false; }
  public boolean isConstantOop()    { return false; }
  public boolean isObject()         { return false; }
  public boolean isMarker()         { return false; }
  public boolean isObjectMerge()    { return false; }

  public static ScopeValue readFrom(DebugInfoReadStream stream) {
    switch (stream.readInt()) {
    case LOCATION_CODE:
      return new LocationValue(stream);
    case CONSTANT_INT_CODE:
      return new ConstantIntValue(stream);
    case CONSTANT_OOP_CODE:
      return new ConstantOopReadValue(stream);
    case CONSTANT_LONG_CODE:
      return new ConstantLongValue(stream);
    case CONSTANT_DOUBLE_CODE:
      return new ConstantDoubleValue(stream);
    case CONSTANT_OBJECT_CODE:
    case AUTO_BOX_OBJECT_CODE:
      // The C++ code handles these 2 cases separately because the autobox case needs
      // some extra state during deoptimization.  That's not required to display the
      // information so treat it like a normal object value.
      return stream.readObjectValue();
    case CONSTANT_OBJECT_ID_CODE:
      return stream.getCachedObject();
    case MARKER_CODE:
      return new MarkerValue();
    case OBJECT_MERGE_CODE:
      return stream.readObjectMergeValue();
    default:
      Assert.that(false, "should not reach here");
      return null;
    }
  }

  public abstract void printOn(PrintStream tty);

  public String toString() {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos, true, StandardCharsets.UTF_8);
    printOn(ps);
    return baos.toString(StandardCharsets.UTF_8);
  }
}
