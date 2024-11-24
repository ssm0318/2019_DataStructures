/*
 * Decompiled with CFR 0.152.
 */
package com.dmlab;

import com.dmlab.util.EmptyQueueException;
import com.dmlab.util.MyQueue;
import com.dmlab.util.MyStack;

public class StackCalculator {
    private MyStack<String> mStack = new MyStack();
    private MyQueue<String> mQueue = new MyQueue();
    private MyStack<Integer> mStackCalc = new MyStack();
    private static final int TYPE_OPERATOR = 0;
    private static final int TYPE_BRACE = 1;
    private static final int TYPE_NUMBER = 2;

    private int operation(String operator, int a, int b) {
        if (operator.equals("+")) {
            return a + b;
        }
        if (operator.equals("-")) {
            return a - b;
        }
        if (operator.equals("*")) {
            return a * b;
        }
        return a / b;
    }

    public int solve(String infix) throws EmptyQueueException {
        this.convert(infix);
        while (!this.mQueue.empty()) {
            String item = this.mQueue.poll();
            if (this.typeOf(item) == 0) {
                int operand1 = Integer.parseInt(this.mStack.pop());
                int operand2 = Integer.parseInt(this.mStack.pop());
                String res = Integer.toString(this.operation(item, operand2, operand1));
                this.mStack.push(res);
                continue;
            }
            if (this.typeOf(item) != 2) continue;
            this.mStack.push(item);
        }
        this.mStackCalc.push(Integer.parseInt(this.mStack.peek()));
        return Integer.parseInt(this.mStack.pop());
    }

    private void convert(String infix) {
        String[] items;
        for (String item : items = infix.split(" ")) {
            if (this.typeOf(item) == 0) {
                while (!this.mStack.empty() && !this.mStack.peek().equals("(") && this.prior(this.mStack.peek()) >= this.prior(item)) {
                    this.mQueue.add(this.mStack.pop());
                }
                this.mStack.push(item);
                continue;
            }
            if (item.equals("(")) {
                this.mStack.push(item);
                continue;
            }
            if (item.equals(")")) {
                while (!this.mStack.empty() && !this.mStack.peek().equals("(")) {
                    this.mQueue.add(this.mStack.pop());
                }
                if (!this.mStack.peek().equals("(")) continue;
                this.mStack.pop();
                continue;
            }
            if (this.typeOf(item) != 2) continue;
            this.mQueue.add(item);
        }
        while (!this.mStack.empty()) {
            this.mQueue.add(this.mStack.pop());
        }
    }

    private int typeOf(String entry) {
        if (entry.equals("+") || entry.equals("-") || entry.equals("*") || entry.equals("/")) {
            return 0;
        }
        if (entry.equals("(") || entry.equals(")")) {
            return 1;
        }
        return 2;
    }

    private int prior(String item) {
        if (item.equals("+") || item.equals("-")) {
            return 0;
        }
        return 1;
    }
}

