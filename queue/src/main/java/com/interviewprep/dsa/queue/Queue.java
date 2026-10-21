package com.interviewprep.dsa.queue;

import java.util.NoSuchElementException;

public class Queue<T> {
    private static final class Node<T> {
        private T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> front;
    private Node<T> back;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(T value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) {
            front = back = new Node<>(value);
        } else {
            Node<T> oldBack = back;
            back = new Node<>(value);
            oldBack.next = back;
        }

        size++;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return front.value;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node<T> oldFront = front;
        T val = front.value;
        front = front.next;
        oldFront.next = null;

        if (front == null) {
            back = null;
        }

        size--;

        return val;
    }

    public boolean contains(T value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        for (Node<T> x = front; x != null; x = x.next) {
            if (x.value.equals(value)) {
                return true;
            }
        }

        return false;
    }

    public void clear() {
        Node<T> current = front;

        while (current != null) {
            Node<T> next = current.next;
            current.next = null;
            current.value = null;
            current = next;
        }

        front = back = null;

        size = 0;
    }
}
