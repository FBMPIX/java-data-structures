package com.interviewprep.dsa.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private static final class Node<T> {
        private T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void addFirst(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Invalid value");
        }

        Node<T> newNode = new Node<>(value);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void addLast(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Invalid value");
        }

        Node<T> newNode = new Node<T>(value);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.value;
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.value;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        int j = 0;
        for (Node<T> i = head; i != null; i = i.next, j++) {
            if (j == index) {
                return i.value;
            }
        }

        return null;
    }

    public T set(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        if (value == null) {
            throw new IllegalArgumentException("Invalid value");
        }

        int j = 0;

        for (Node<T> i = head; i != null; i = i.next, j++) {
            if (j == index) {
                T oldVal = i.value;
                i.value = value;
                return oldVal;
            }
        }

        return null;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T oldValue = head.value;
        head = head.next;
        size--;

        if (isEmpty()) {
            tail = null;
        }

        return oldValue;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            return removeFirst();
        }

        T oldValue = tail.value;

        Node<T> current = head;
        while (current.next != tail) {
            current = current.next;
        }

        current.next = null;
        tail = current;
        size--;

        return oldValue;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }

        Node<T> prev = head;
        for (int j = 0; j < index - 1; j++) {
            prev = prev.next;
        }

        Node<T> toRemove = prev.next;
        T val = toRemove.value;

        prev.next = toRemove.next;
        toRemove.next = null;

        size--;

        return val;
    }

    public boolean contains(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Invalid value");
        }

        for (Node<T> i = head; i != null; i = i.next) {
            if (i.value.equals(value)) {
                return true;
            }
        }

        return false;
    }

    public void clear() {
        Node<T> current = head;
        while (current != null) {
            Node<T> currentNext = current.next;
            current.value = null;
            current.next = null;
            current = currentNext;
        }

        head = tail = null;

        size = 0;
    }
}
