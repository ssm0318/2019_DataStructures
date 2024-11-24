/*
 * Decompiled with CFR 0.152.
 */
package com.text;

import com.sort.HybridSorter;
import com.sort.Pair;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("testcase/input.txt"));
        int current = 0;
        Pair[] data = null;
        String[] array = new String[10000];
        String line = null;
        int n = 0;
        String sortType = "";
        StringBuilder builder = new StringBuilder(512);
        HybridSorter sorter = new HybridSorter();
        while ((line = reader.readLine()) != null) {
            int index = line.indexOf(32);
            String command = null;
            command = index == -1 ? line : line.substring(0, index);
            if ("n".equals(command)) {
                n = Integer.parseInt(line.substring(index + 1));
                data = (Pair[])Array.newInstance(Pair.class, n);
                continue;
            }
            if ("append".equals(command)) {
                int secondIndex = line.indexOf(32, index + 1);
                String key = line.substring(index + 1, secondIndex);
                int value = Integer.parseInt(line.substring(secondIndex + 1));
                data[current] = new Pair(key, value);
                array[current] = key;
                ++current;
                continue;
            }
            if ("sort".equals(command)) {
                sortType = line.substring(index + 1);
                sorter.sort(data, 0, current - 1, sortType);
                continue;
            }
            if (!"print".equals(command)) continue;
            builder.append("Sort by " + sortType + ": ");
            for (int i = 0; i < n; ++i) {
                Pair search = sorter.search(data, i, sortType);
                builder.append("[");
                builder.append((String)search.getKey());
                builder.append(" : ");
                builder.append(search.getValue());
                builder.append("] ");
            }
            System.out.println(builder.toString());
            builder.setLength(0);
        }
        reader.close();
    }
}

