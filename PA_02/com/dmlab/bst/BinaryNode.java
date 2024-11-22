/*
 * Decompiled with CFR 0.152.
 */
package com.dmlab.bst;

public class BinaryNode<Key, E> {
    private Key key;
    private E value;
    private int size;
    private BinaryNode<Key, E> left;
    private BinaryNode<Key, E> right;

    public BinaryNode() {
        this.value = null;
        this.left = null;
        this.right = null;
        this.size = 1;
    }

    public BinaryNode(Key k, E val) {
        this.right = null;
        this.left = null;
        this.key = k;
        this.value = val;
        this.size = 1;
    }

    public void setKey(Key k) {
        this.key = k;
    }

    public void setValue(E v) {
        this.value = v;
    }

    public Key getKey() {
        return this.key;
    }

    public E getValue() {
        return this.value;
    }

    public int getSize() {
        return this.size;
    }

    public BinaryNode<Key, E> getLeft() {
        return this.left;
    }

    public BinaryNode<Key, E> getRight() {
        return this.right;
    }

    public void setLeft(BinaryNode<Key, E> l) {
        this.left = l;
    }

    public void setRight(BinaryNode<Key, E> r) {
        this.right = r;
    }

    public void increaseSize() {
        ++this.size;
    }

    public void decreaseSize() {
        --this.size;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
}

