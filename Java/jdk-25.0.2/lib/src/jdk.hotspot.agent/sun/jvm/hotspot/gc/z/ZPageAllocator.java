/*
 * Copyright (c) 2017, 2025, Oracle and/or its affiliates. All rights reserved.
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
import sun.jvm.hotspot.types.CIntegerField;
import sun.jvm.hotspot.types.Type;
import sun.jvm.hotspot.types.TypeDataBase;

// Mirror class for ZPageAllocator

public class ZPageAllocator extends VMObject {

    private static CIntegerField maxCapacityField;
    private static long partitionsOffset;
    private static long numaCount;

    static {
        VM.registerVMInitializedObserver((o, d) -> initialize(VM.getVM().getTypeDataBase()));
    }

    private static synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("ZPageAllocator");

        maxCapacityField = type.getCIntegerField("_max_capacity");
        partitionsOffset = type.getAddressField("_partitions").getOffset();
        numaCount = ZNUMA.count();
    }

    private ZPerNUMAZPartition partitions() {
        Address partitionsAddr = addr.addOffsetTo(partitionsOffset);
        return VMObjectFactory.newObject(ZPerNUMAZPartition.class, partitionsAddr);
    }

    public long maxCapacity() {
        return maxCapacityField.getValue(addr);
    }

    public long capacity() {
        long total_capacity = 0;
        for (int id = 0; id < numaCount; id++) {
          total_capacity += partitions().value(id).capacity();
        }
        return total_capacity;
    }

    public long used() {
        long total_used = 0;
        for (int id = 0; id < numaCount; id++) {
          total_used += partitions().value(id).used();
        }
        return total_used;
    }

    public ZPageAllocator(Address addr) {
        super(addr);
    }
}
