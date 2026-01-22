/*
 * Copyright (c) 2017, 2024, Red Hat, Inc. and/or its affiliates.
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

package sun.jvm.hotspot.gc.serial;

import java.io.*;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.gc.shared.*;
import sun.jvm.hotspot.memory.MemRegion;
import sun.jvm.hotspot.runtime.*;
import sun.jvm.hotspot.types.*;
import sun.jvm.hotspot.utilities.*;

public class SerialHeap extends CollectedHeap {

  public SerialHeap(Address addr) {
    super(addr);
  }

  public CollectedHeapName kind() {
    return CollectedHeapName.SERIAL;
  }

  private static AddressField youngGenField;
  private static AddressField oldGenField;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) {
    Type type = db.lookupType("SerialHeap");

    youngGenField = type.getAddressField("_young_gen");
    oldGenField = type.getAddressField("_old_gen");
  }

  public DefNewGeneration youngGen() {
    return VMObjectFactory.newObject(DefNewGeneration.class, youngGenField.getValue(addr));
  }

  public TenuredGeneration oldGen() {
    return VMObjectFactory.newObject(TenuredGeneration.class, oldGenField.getValue(addr));
  }

  public boolean isIn(Address a) {
    return youngGen().isIn(a) || oldGen().isIn(a);
  }

  public long capacity() {
    long capacity = 0;
    capacity += youngGen().capacity();
    capacity += oldGen().capacity();
    return capacity;
  }

  public long used() {
    long used = 0;
    used += youngGen().used();
    used += oldGen().used();
    return used;
  }

  public void liveRegionsIterate(LiveRegionsClosure closure) {
    youngGen().liveRegionsIterate(closure);
    oldGen().liveRegionsIterate(closure);
  }

  public void printOn(PrintStream tty) {
    tty.println("SerialHeap:");

    tty.println("Young Generation: ");
    youngGen().printOn(tty);
    tty.println();

    tty.println("Old Generation: ");
    oldGen().printOn(tty);
    tty.println();
  }
}
