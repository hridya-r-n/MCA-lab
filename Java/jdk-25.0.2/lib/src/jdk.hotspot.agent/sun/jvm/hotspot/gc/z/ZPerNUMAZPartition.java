/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
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

package sun.jvm.hotspot.gc.z;

import sun.jvm.hotspot.debugger.Address;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.runtime.VMObject;
import sun.jvm.hotspot.runtime.VMObjectFactory;
import sun.jvm.hotspot.types.AddressField;
import sun.jvm.hotspot.types.Type;
import sun.jvm.hotspot.types.TypeDataBase;

// Mirror class for ZPerNUMA<ZPartition>

public class ZPerNUMAZPartition extends VMObject {

    private static AddressField addrField;
    private static long valueOffset = 4096; // 4k

    static {
        VM.registerVMInitializedObserver((o, d) -> initialize(VM.getVM().getTypeDataBase()));
    }

    private static synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("ZPerNUMAZPartition");
        addrField = type.getAddressField("_addr");
    }

    public ZPartition value(long id) {
        Address valueArrayAddr = addrField.getValue(addr);
        Address partitionAddr = valueArrayAddr.addOffsetTo(id * valueOffset);
        return VMObjectFactory.newObject(ZPartition.class, partitionAddr);
    }

    public ZPerNUMAZPartition(Address addr) {
        super(addr);
    }
}

