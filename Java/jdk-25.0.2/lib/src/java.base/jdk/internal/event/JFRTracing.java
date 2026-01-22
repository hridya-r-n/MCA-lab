/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
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
package jdk.internal.event;

import sun.nio.ch.FileChannelImpl;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.lang.Throwable;
import java.lang.reflect.Field;

/**
 * Helper class to enable JFR tracing.
 */
public final class JFRTracing {

  public static void enable() throws NoSuchFieldException, IllegalAccessException {
      enable(Throwable.class);
      enable(FileInputStream.class);
      enable(FileOutputStream.class);
      enable(FileChannelImpl.class);
      enable(RandomAccessFile.class);
  }

  private static void enable(Class<?> clazz) throws NoSuchFieldException, IllegalAccessException {
      Field field = clazz.getDeclaredField("jfrTracing");
      field.setAccessible(true);
      field.setBoolean(null, true);
  }
}
