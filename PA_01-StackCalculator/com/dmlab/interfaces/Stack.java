/*
 * Decompiled with CFR 0.152.
 */
package com.dmlab.interfaces;

import java.util.EmptyStackException;

public interface Stack<E> {
    public void clear();

    public void push(E var1) throws RuntimeException;

    public E pop() throws EmptyStackException;

    public E peek() throws EmptyStackException;

    public boolean empty();
}

