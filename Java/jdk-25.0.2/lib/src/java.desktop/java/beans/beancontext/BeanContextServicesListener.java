/*
 * Copyright (c) 1998, 2024, Oracle and/or its affiliates. All rights reserved.
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

package java.beans.beancontext;

import java.beans.beancontext.BeanContextServiceAvailableEvent;
import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.beans.beancontext.BeanContextServiceRevokedListener;

/**
 * The listener interface for receiving
 * {@code BeanContextServiceAvailableEvent} objects.
 * A class that is interested in processing a
 * {@code BeanContextServiceAvailableEvent} implements this interface.
 */

@SuppressWarnings("removal")
@Deprecated(since = "23", forRemoval = true)
public interface BeanContextServicesListener extends BeanContextServiceRevokedListener {

    /**
     * The service named has been registered. getService requests for
     * this service may now be made.
     * @param bcsae the {@code BeanContextServiceAvailableEvent}
     */
    void serviceAvailable(BeanContextServiceAvailableEvent bcsae);
}
