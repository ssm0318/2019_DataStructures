/*
 * Decompiled with CFR 0.152.
 */
package com.dmlab.bst;

import com.dmlab.bst.BinaryNode;

public class BinarySearchTree<Key extends Comparable<? super Key>, E> {
    private BinaryNode<Key, E> root = null;
    private int nodeCount = 0;

    public BinaryNode<Key, E> getRoot() {
        return this.root;
    }

    public void clear() {
        this.root = null;
        this.nodeCount = 0;
    }

    public void insert(Key key, E value) {
        this.root = this.insertHelp(this.root, key, value);
        ++this.nodeCount;
    }

    private BinaryNode<Key, E> insertHelp(BinaryNode<Key, E> rt, Key key, E value) {
        if (rt == null) {
            return new BinaryNode<Key, E>(key, value);
        }
        if (((Comparable)rt.getKey()).compareTo(key) > 0) {
            rt.setLeft(this.insertHelp(rt.getLeft(), key, value));
        } else if (((Comparable)rt.getKey()).compareTo(key) < 0) {
            rt.setRight(this.insertHelp(rt.getRight(), key, value));
        }
        rt.increaseSize();
        return rt;
    }

    public E remove(Key key) {
        E nodeValToRemove = this.find(key);
        if (nodeValToRemove != null) {
            this.root = this.removeHelp(this.root, key);
            --this.nodeCount;
        }
        return nodeValToRemove;
    }

    private BinaryNode<Key, E> removeHelp(BinaryNode<Key, E> rt, Key key) {
        if (rt == null) {
            return null;
        }
        int rtKeyCompared = ((Comparable)rt.getKey()).compareTo(key);
        if (rtKeyCompared < 0) {
            rt.setRight(this.removeHelp(rt.getRight(), key));
        } else if (rtKeyCompared > 0) {
            rt.setLeft(this.removeHelp(rt.getLeft(), key));
        } else {
            if (rt.getLeft() == null) {
                return rt.getRight();
            }
            if (rt.getRight() == null) {
                return rt.getLeft();
            }
            BinaryNode<Key, E> temp = this.getMin(rt.getRight());
            rt.setKey((Comparable)temp.getKey());
            rt.setValue(temp.getValue());
            rt.setRight(this.deleteMin(rt.getRight()));
        }
        rt.decreaseSize();
        return rt;
    }

    private BinaryNode<Key, E> getMin(BinaryNode<Key, E> rt) {
        if (rt.getLeft() == null) {
            return rt;
        }
        return this.getMin(rt.getLeft());
    }

    private BinaryNode<Key, E> deleteMin(BinaryNode<Key, E> rt) {
        assert (rt != null);
        rt.decreaseSize();
        if (rt.getLeft() == null) {
            return rt.getRight();
        }
        rt.setLeft(this.deleteMin(rt.getLeft()));
        return rt;
    }

    public E find(Key key) {
        return this.findHelp(this.root, key);
    }

    public int size() {
        return this.nodeCount;
    }

    private E findHelp(BinaryNode<Key, E> rt, Key key) {
        if (rt == null) {
            return null;
        }
        if (((Comparable)rt.getKey()).compareTo(key) == 0) {
            return rt.getValue();
        }
        if (((Comparable)rt.getKey()).compareTo(key) < 0) {
            return this.findHelp(rt.getRight(), key);
        }
        return this.findHelp(rt.getLeft(), key);
    }

    public void printBookList() {
        this.printBookListHelper(this.root);
    }

    public void printBookListHelper(BinaryNode<Key, E> rt) {
        if (this.size() == 0) {
            System.out.println("BookSearch does not have any book");
            return;
        }
        if (rt == null) {
            return;
        }
        this.printBookListHelper(rt.getLeft());
        System.out.println("BOOK:\t" + rt.getKey());
        this.printBookListHelper(rt.getRight());
    }

    public Key orderSearch(int order) {
        if (order > this.size() || order < 1) {
            return null;
        }
        return this.orderSearchHelper(this.root, order);
    }

    private Key orderSearchHelper(BinaryNode<Key, E> rt, int order) {
        int myOrder;
        if (rt == null) {
            return null;
        }
        int n = myOrder = rt.getLeft() == null ? 1 : rt.getLeft().getSize() + 1;
        if (myOrder < order) {
            return this.orderSearchHelper(rt.getRight(), order - myOrder);
        }
        if (myOrder > order) {
            return this.orderSearchHelper(rt.getLeft(), order);
        }
        return (Key)((Comparable)rt.getKey());
    }

    public int orderSearch(Key key) {
        return this.orderSearchHelper(this.root, key, 0);
    }

    private int orderSearchHelper(BinaryNode<Key, E> rt, Key key, int count) {
        if (rt == null) {
            return 0;
        }
        if (((Comparable)rt.getKey()).compareTo(key) > 0) {
            count = this.orderSearchHelper(rt.getLeft(), key, count);
        } else {
            if (rt.getLeft() != null) {
                count += rt.getLeft().getSize();
            }
            count = ((Comparable)rt.getKey()).compareTo(key) < 0 ? this.orderSearchHelper(rt.getRight(), key, count + 1) : ++count;
        }
        return count;
    }
}

