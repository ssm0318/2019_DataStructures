/*
 * Decompiled with CFR 0.152.
 */
package com.dmlab;

import com.dmlab.StackCalculator;
import com.dmlab.util.MyQueue;
import com.dmlab.util.MyStack;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        FileInputStream fis = null;
        BufferedReader reader = null;
        try {
            fis = new FileInputStream("sample_input.txt");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        reader = new BufferedReader(new InputStreamReader(fis));
        LinkedList<String> outputs = new LinkedList<String>();
        MyStack<Integer> stack = new MyStack<Integer>();
        MyQueue<Integer> queue = new MyQueue<Integer>();
        StackCalculator calculator = new StackCalculator();
        try {
            String line = reader.readLine();
            while (line != null) {
                int value;
                String cmd;
                String[] line_split = line.split(",");
                String cmd_type = line_split[0];
                if (cmd_type.equals("stack")) {
                    cmd = line_split[1];
                    if (cmd.equals("clear")) {
                        stack.clear();
                    } else if (cmd.equals("push")) {
                        value = Integer.valueOf(line_split[2]);
                        stack.push(value);
                    } else if (cmd.equals("pop")) {
                        outputs.add(String.valueOf(stack.pop()));
                    } else if (cmd.equals("peek")) {
                        outputs.add(String.valueOf(stack.peek()));
                    } else if (cmd.equals("empty")) {
                        outputs.add(String.valueOf(stack.empty()));
                    }
                } else if (cmd_type.equals("queue")) {
                    cmd = line_split[1];
                    if (cmd.equals("clear")) {
                        queue.clear();
                    } else if (cmd.equals("add")) {
                        value = Integer.valueOf(line_split[2]);
                        queue.add(value);
                    } else if (cmd.equals("peek")) {
                        outputs.add(String.valueOf(queue.peek()));
                    } else if (cmd.equals("poll")) {
                        outputs.add(String.valueOf(queue.poll()));
                    } else if (cmd.equals("empty")) {
                        outputs.add(String.valueOf(queue.empty()));
                    }
                } else if (cmd_type.equals("calc")) {
                    String infix = line_split[1];
                    outputs.add(String.valueOf(calculator.solve(infix)));
                }
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (String output : outputs) {
            System.out.println(output);
        }
    }
}

