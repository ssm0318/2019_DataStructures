/*
 * Decompiled with CFR 0.152.
 */
package com.dmlab.bst;

import com.dmlab.bst.BinaryNode;
import com.dmlab.bst.BinarySearchTree;

public class BookSearch {
    private BinarySearchTree<String, String> bst = new BinarySearchTree();

    public BinaryNode<String, String> getRoot() {
        if (this.bst == null) {
            return null;
        }
        return this.bst.getRoot();
    }

    public void add(String name, String position) {
        this.bst.insert(name, position);
    }

    public String remove(String name) {
        return this.bst.remove(name);
    }

    public String get(String name) {
        return this.bst.find(name);
    }

    public int size() {
        return this.bst.size();
    }

    public void printBookList() {
        this.bst.printBookList();
    }

    public String orderSearch(int order) {
        return this.bst.orderSearch(order);
    }

    public int orderSearch(String name) {
        return this.bst.orderSearch(name);
    }
}

