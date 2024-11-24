/*
 * Decompiled with CFR 0.152.
 */
package com.sort;

public class Pair<K extends Comparable<? super K>, Integer> {
    private K key;
    private int value;

    public Pair(K key, int value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

