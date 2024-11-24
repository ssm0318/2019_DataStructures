/*
 * Decompiled with CFR 0.152.
 */
package ds.hash;

public class HashTable {
    private int[] table;
    private int c1;
    private int c2;
    private int c3;
    private int delMarker = -1;
    private int tableSize;

    public HashTable(int n) {
        this.table = new int[n];
        this.tableSize = n;
    }

    public void create(int c1, int c2, int c3) {
        assert (c1 != 0 || c2 != 0 || c3 != 0);
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }

    public void insert(int key) {
        int index;
        assert (key > 0 && this.searchIndex(key) == this.delMarker);
        int i = 0;
        while (this.table[index = this.probeVal(key, i++)] > 0) {
        }
        this.table[index] = key;
        System.out.printf("INSERT: %d / INDEX: %d\n", key, index);
    }

    public void delete(int key) {
        assert (key > 0);
        int index = this.searchIndex(key);
        if (index != this.delMarker) {
            this.table[index] = this.delMarker;
            System.out.printf("DELETE: %d / INDEX: %d\n", key, index);
            return;
        }
    }

    public void search(int key) {
        assert (key > 0);
        int index = this.searchIndex(key);
        if (index != this.delMarker) {
            System.out.printf("SEARCH: %d / INDEX: %d\n", key, index);
            return;
        }
    }

    private int probeVal(int key, int i) {
        return (key % this.tableSize + this.c1 * i * i + this.c2 * i + this.c3) % this.tableSize;
    }

    private int searchIndex(int key) {
        int index;
        int i = 0;
        do {
            if (this.table[index = this.probeVal(key, i++)] != key) continue;
            return index;
        } while (this.table[index] != 0);
        System.out.printf("Failed to find %d\n", key);
        return this.delMarker;
    }

    public void printAll() {
        boolean isFirstPrint = true;
        for (int i = 0; i < this.tableSize; ++i) {
            if (this.table[i] == 0) continue;
            if (isFirstPrint) {
                System.out.printf("%d(%d)", this.table[i], i);
                isFirstPrint = false;
                continue;
            }
            System.out.printf(", %d(%d)", this.table[i], i);
        }
        System.out.println();
    }
}

