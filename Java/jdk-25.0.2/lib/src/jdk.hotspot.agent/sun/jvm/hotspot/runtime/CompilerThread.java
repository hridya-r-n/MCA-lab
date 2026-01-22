/*
 * Copyright (c) 2000, 2025, Oracle and/or its affiliates. All rights reserved.
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

package sun.jvm.hotspot.runtime;

import java.io.*;
import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.types.*;
import sun.jvm.hotspot.utilities.Observable;
import sun.jvm.hotspot.utilities.Observer;

public class CompilerThread extends JavaThread {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static AddressField envField;

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException { }

  public CompilerThread(Address addr) {
    super(addr);
  }

  @Override
  public boolean isHiddenFromExternalView() {
      /*
       * See JDK-8348317. CompilerThreads are sometimes hidden and sometimes not. They
       * are not when JVMCI is enabled and a compiler implemented in java is running
       * on the CompilerThread. This is hard for SA to determine, and not something a customer
       * is likely to ever run across or care about, so by default all CompilerThreads
       * are considered to be hidden. However, we allow this behaviour to be overridden
       * in case the user has a need to make the CompilerThreads visible.
       */
      return !Boolean.getBoolean("sun.jvm.hotspot.runtime.CompilerThread.visible");
  }

}
