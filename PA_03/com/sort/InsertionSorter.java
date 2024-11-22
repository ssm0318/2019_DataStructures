/*
 * Decompiled with CFR 0.152.
 */
package com.sort;

import com.sort.Pair;

public class InsertionSorter<K extends Comparable<? super K>> {
    public void sort(Pair<K, ?>[] array, int left, int right, String sortType) {
        if (sortType.equals("keys")) {
            this.sortByKeys(array, left, right);
        } else if (sortType.equals("values")) {
            this.sortByValues(array, left, right);
        }
    }

    private void sortByKeys(Pair<K, ?>[] array, int left, int right) {
        for (int i = left + 1; i <= right; ++i) {
            for (int j = i; j > left && array[j].getKey().compareTo(array[j - 1].getKey()) < 0; --j) {
                Pair<K, ?> temp = array[j - 1];
                array[j - 1] = array[j];
                array[j] = temp;
            }
        }
    }

    private void sortByValues(Pair<K, ?>[] array, int left, int right) {
        for (int i = left + 1; i <= right; ++i) {
            for (int j = i; j > left && array[j].getValue() < array[j - 1].getValue(); --j) {
                Pair<K, ?> temp = array[j - 1];
                array[j - 1] = array[j];
                array[j] = temp;
            }
        }
    }
}

