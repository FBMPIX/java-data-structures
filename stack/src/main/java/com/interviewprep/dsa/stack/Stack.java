package com.interviewprep.dsa.stack;

import java.util.NoSuchElementException;

public class Stack<T> {
    private static final class Node<T> {
        private T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> top;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        Node<T> newNode = new Node<T>(value);

        if (isEmpty()) {
            top = newNode;
        } else {
            Node<T> oldTop = top;
            top = new Node<T>(value);
            top.next = oldTop;
        }

        size++;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return top.value;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T val = top.value;

        if (top.next == null) {
            top = null;
            size--;
            return val;
        }

        top = top.next;
        size--;

        return val;
    }

    public boolean contains(T value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        for (Node<T> x = top; x != null; x = x.next) {
            if (x.value.equals(value)) {
                return true;
            }
        }

        return false;
    }

    public void clear() {

        Node<T> current = top;
        while (current != null) {
            Node<T> next = current.next;
            current.value = null;
            current.next = null;
            current = next;
        }

        top = null;

        size = 0;
    }
}
