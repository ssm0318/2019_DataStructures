/*
 * Decompiled with CFR 0.152.
 */
package com.sort;

import com.sort.InsertionSorter;
import com.sort.Pair;

public class HybridSorter<K extends Comparable<? super K>> {
    InsertionSorter<K> insertionSort = new InsertionSorter();
    private final int RUN = 32;
    String sortType = "";

    public Pair<K, ?> search(Pair<K, ?>[] array, int k, String sortType) {
        if (sortType.equals(this.sortType)) {
            return array[k];
        }
        throw new IllegalArgumentException("Array is not sorted by " + sortType);
    }

    public void sort(Pair<K, ?>[] array, int left, int right, String sortType) {
        int k;
        assert (array != null && left >= 0 && right < array.length);
        this.sortType = sortType;
        if (sortType.equals("keys")) {
            this.findPivotByKey(array, left, right);
            k = this.partitionByKey(array, left - 1, right, array[right].getKey());
        } else {
            this.findPivotByValue(array, left, right);
            k = this.partitionByValue(array, left - 1, right, array[right].getValue());
        }
        this.swap(array, k, right);
        if (k - left > 32) {
            this.sort(array, left, k - 1, sortType);
        } else if (k - left > 1) {
            this.insertionSort.sort(array, left, k - 1, sortType);
        }
        if (right - k > 32) {
            this.sort(array, k + 1, right, sortType);
        } else if (right - k > 1) {
            this.insertionSort.sort(array, k + 1, right, sortType);
        }
    }

    private void findPivotByKey(Pair<K, ?>[] array, int left, int right) {
        int mid = (left + right) / 2;
        int compLeftMid = array[left].getKey().compareTo(array[mid].getKey());
        int compLeftRight = array[left].getKey().compareTo(array[right].getKey());
        int compMidRight = array[mid].getKey().compareTo(array[right].getKey());
        if (compLeftMid * compMidRight < 0) {
            this.swap(array, mid, right);
        } else if (compLeftMid * compLeftRight < 0) {
            this.swap(array, left, right);
        }
    }

    private void findPivotByValue(Pair<K, ?>[] array, int left, int right) {
        int compMidRight;
        int mid = (left + right) / 2;
        int compLeftMid = array[left].getValue() < array[mid].getValue() ? -1 : 1;
        int compLeftRight = array[left].getValue() < array[right].getValue() ? -1 : 1;
        int n = compMidRight = array[mid].getValue() < array[right].getValue() ? -1 : 1;
        if (compLeftMid * compMidRight < 0) {
            this.swap(array, mid, right);
        } else if (compLeftMid * compLeftRight < 0) {
            this.swap(array, left, right);
        }
    }

    private int partitionByKey(Pair<K, ?>[] array, int left, int right, K pivot) {
        while (true) {
            if (array[++left].getKey().compareTo(pivot) < 0) {
                continue;
            }
            while (right > 0 && array[--right].getKey().compareTo(pivot) > 0) {
            }
            this.swap(array, left, right);
            if (left >= right) break;
        }
        this.swap(array, left, right);
        return left;
    }

    private int partitionByValue(Pair<K, ?>[] array, int left, int right, int pivot) {
        while (true) {
            if (array[++left].getValue() < pivot) {
                continue;
            }
            while (right > 0 && array[--right].getValue() > pivot) {
            }
            this.swap(array, left, right);
            if (left >= right) break;
        }
        this.swap(array, left, right);
        return left;
    }

    private void swap(Pair<K, ?>[] array, int a, int b) {
        Pair<K, ?> temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}

