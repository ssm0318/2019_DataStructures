/*
 * Decompiled with CFR 0.152.
 */
package ds.test;

import ds.hash.HashTable;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int TABLE_SIZE = 577;
    private static final int ERROR = -1;
    private static final int CREATE = 0;
    private static final int INSERT = 1;
    private static final int DELETE = 2;
    private static final int SEARCH = 3;
    private static final int PRINT = 4;

    public static void main(String[] args) throws IOException {
        String filePath;
        HashTable hashTable = new HashTable(577);
        try {
            filePath = args[0];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            filePath = "sample_input.txt";
        }
        FileInputStream fis = new FileInputStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        String line = reader.readLine();
        while (line != null) {
            String[] lineSplit = line.split(" ");
            String cmd = lineSplit[0];
            switch (Main.getCommandNum(cmd)) {
                case 0: {
                    int c1 = Integer.parseInt(lineSplit[1]);
                    int c2 = Integer.parseInt(lineSplit[2]);
                    int c3 = Integer.parseInt(lineSplit[3]);
                    hashTable.create(c1, c2, c3);
                    break;
                }
                case 1: {
                    int key = Integer.parseInt(lineSplit[1]);
                    hashTable.insert(key);
                    break;
                }
                case 2: {
                    int key = Integer.parseInt(lineSplit[1]);
                    hashTable.delete(key);
                    break;
                }
                case 3: {
                    int key = Integer.parseInt(lineSplit[1]);
                    hashTable.search(key);
                    break;
                }
                case 4: {
                    hashTable.printAll();
                }
            }
            line = reader.readLine();
        }
        reader.close();
    }

    private static int getCommandNum(String cmd) {
        switch (cmd) {
            case "create": {
                return 0;
            }
            case "insert": {
                return 1;
            }
            case "delete": {
                return 2;
            }
            case "search": {
                return 3;
            }
            case "print": {
                return 4;
            }
        }
        return -1;
    }
}

