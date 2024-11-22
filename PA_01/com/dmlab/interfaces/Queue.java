/*
 * Decompiled with CFR 0.152.
 */
package com.dmlab.interfaces;

import com.dmlab.util.EmptyQueueException;

public interface Queue<E> {
    public void clear();

    public void add(E var1) throws RuntimeException;

    public E poll() throws EmptyQueueException;

    public E peek() throws EmptyQueueException;

    public boolean empty();
}

