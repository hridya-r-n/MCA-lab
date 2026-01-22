/*
 * Copyright (c) 2002, 2024, Oracle and/or its affiliates. All rights reserved.
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

package sun.jvm.hotspot.interpreter;

import sun.jvm.hotspot.oops.ConstantPoolCache;
import sun.jvm.hotspot.oops.Method;

// any bytecode with constant pool index

public abstract class BytecodeWithCPIndex extends Bytecode {
  BytecodeWithCPIndex(Method method, int bci) {
    super(method, bci);
  }

  // the constant pool index for this bytecode
  public int index() {
    if (code() == Bytecodes._invokedynamic) {
      return getIndexU4();
    } else {
      return getIndexU2(code(), false);
    }
  }

  protected int indexForFieldOrMethod() {
     ConstantPoolCache cpCache = method().getConstants().getCache();
     // get ConstantPool index from ConstantPoolCacheIndex at given bci
     int cpCacheIndex = index();
     if (cpCache == null) {
        return cpCacheIndex;
     } else if (code() == Bytecodes._invokedynamic) {
        return cpCache.getIndyEntryAt(cpCacheIndex).getConstantPoolIndex();
     } else if (Bytecodes.isFieldCode(code())) {
        return cpCache.getFieldEntryAt(cpCacheIndex).getConstantPoolIndex();
     } else {
        return cpCache.getMethodEntryAt(cpCacheIndex).getConstantPoolIndex();
     }
  }
}
