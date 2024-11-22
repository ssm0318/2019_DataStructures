/*
 * Decompiled with CFR 0.152.
 */
package com.dmlab.util;

import com.dmlab.interfaces.Stack;
import java.util.EmptyStackException;

public class MyStack<E>
implements Stack<E> {
    private static final int defaultSize = 128;
    private E[] mData = new Object[128];
    private int mCursor = 0;

    @Override
    public void clear() {
        this.mCursor = 0;
    }

    @Override
    public void push(E item) throws RuntimeException {
        if (this.mCursor >= 128) {
            throw new RuntimeException("stack is full");
        }
        this.mData[this.mCursor++] = item;
    }

    @Override
    public E pop() throws EmptyStackException {
        if (this.mCursor <= 0) {
            throw new EmptyStackException();
        }
        return this.mData[--this.mCursor];
    }

    @Override
    public E peek() throws EmptyStackException {
        if (this.mCursor <= 0) {
            throw new EmptyStackException();
        }
        return this.mData[this.mCursor - 1];
    }

    @Override
    public boolean empty() {
        return this.mCursor == 0;
    }

    public String toString() {
        Object stackStr = "current stack: ";
        for (int i = 0; i < this.mCursor; ++i) {
            stackStr = (String)stackStr + this.mData[i] + " ";
        }
        return stackStr;
    }
}

