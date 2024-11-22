/*
 * Decompiled with CFR 0.152.
 */
package com.dmlab.util;

import com.dmlab.interfaces.Queue;
import com.dmlab.util.EmptyQueueException;

public class MyQueue<E>
implements Queue<E> {
    private static final int defaultSize = 128;
    private static final int arrSize = 129;
    private E[] mData = new Object[129];
    private int front = 1;
    private int rear = 0;

    @Override
    public void clear() {
        this.rear = 0;
        this.front = 1;
    }

    @Override
    public void add(E item) throws RuntimeException {
        if ((this.rear + 2) % 129 == this.front) {
            throw new RuntimeException("queue is full");
        }
        this.rear = (this.rear + 1) % 129;
        this.mData[this.rear] = item;
    }

    @Override
    public E poll() throws EmptyQueueException {
        if (this.empty()) {
            throw new EmptyQueueException();
        }
        E item = this.mData[this.front];
        this.front = (this.front + 1) % 129;
        return item;
    }

    @Override
    public E peek() throws EmptyQueueException {
        if (this.empty()) {
            throw new EmptyQueueException();
        }
        return this.mData[this.front];
    }

    private int length() {
        return (this.rear + 129 - this.front + 1) % 129;
    }

    @Override
    public boolean empty() {
        return this.length() == 0;
    }

    public String toString() {
        Object queueStr = "current queue: ";
        for (int i = this.front; i != (this.rear + 1) % 129; ++i) {
            queueStr = (String)queueStr + this.mData[i] + " ";
        }
        return queueStr;
    }
}

