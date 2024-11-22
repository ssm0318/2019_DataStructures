/*
 * Decompiled with CFR 0.152.
 */
package com.dmlab;

import com.dmlab.bst.BookSearch;
import com.dmlab.bst.TreePrinter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
    private static final int ADD = 0;
    private static final int REMOVE = 1;
    private static final int GET = 2;
    private static final int SIZE = 3;
    private static final int PRINT_ALL = 4;
    private static final int ORDER_SEARCH = 5;
    private static final int PRINT_TREE = 6;
    private static final int ERROR = 7;

    public static void main(String[] args) throws Exception {
        BookSearch bookSearch = new BookSearch();
        TreePrinter<String, String> tp = new TreePrinter<String, String>();
        FileInputStream fis = new FileInputStream("sample_input.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        String line = reader.readLine();
        while (line != null) {
            String[] line_split = line.split(" ");
            String cmd = line_split[0];
            String name = null;
            String position = null;
            String query = null;
            switch (Main.getCommandNum(cmd)) {
                case 0: {
                    name = line_split[1];
                    position = line_split[2];
                    bookSearch.add(name, position);
                    System.out.println("ADD:\t" + name + " " + position);
                    break;
                }
                case 1: {
                    name = line_split[1];
                    position = bookSearch.remove(name);
                    if (name == null || position == null) {
                        System.out.println("BookSearch cannot find the book");
                        break;
                    }
                    System.out.println("REMOVE:\t" + name + " " + position);
                    break;
                }
                case 2: {
                    name = line_split[1];
                    position = bookSearch.get(name);
                    if (position == null) {
                        System.out.println("BookSearch cannot find the book");
                        break;
                    }
                    System.out.println("GET:\t" + name + " " + position);
                    break;
                }
                case 3: {
                    System.out.println("SIZE:\t" + bookSearch.size());
                    break;
                }
                case 4: {
                    bookSearch.printBookList();
                    break;
                }
                case 5: {
                    int order;
                    query = line_split[1];
                    if (Main.isNumeric(query)) {
                        order = Integer.parseInt(query);
                        String result = bookSearch.orderSearch(order);
                        if (result == null) {
                            System.out.println("BookSearch cannot find the book");
                            break;
                        }
                        System.out.println("ORDER:\t" + result);
                        break;
                    }
                    order = bookSearch.orderSearch(query);
                    if (order == 0) {
                        System.out.println("BookSearch cannot find the book");
                        break;
                    }
                    System.out.println("ORDER:\t" + order);
                    break;
                }
                case 6: {
                    System.out.println("PRINT_TREE:\t");
                    tp.printNode(bookSearch.getRoot());
                    break;
                }
            }
            line = reader.readLine();
        }
    }

    private static int getCommandNum(String cmd) {
        if (cmd.equals("add")) {
            return 0;
        }
        if (cmd.equals("remove")) {
            return 1;
        }
        if (cmd.equals("get")) {
            return 2;
        }
        if (cmd.equals("size")) {
            return 3;
        }
        if (cmd.equals("print_all")) {
            return 4;
        }
        if (cmd.equals("order_search")) {
            return 5;
        }
        if (cmd.equals("print_tree")) {
            return 6;
        }
        return 7;
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}

