package com.interviewprep.dsa.heap;

import java.util.NoSuchElementException;

public class MinHeap<T extends Comparable<? super T>> {
    private static final int DEFAULT_CAPACITY = 8;

    private T[] values;
    private int size;

    public MinHeap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MinHeap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("initialCapacity must be positive");
        }

        values = (T[]) new Comparable[initialCapacity + 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int capacity() {
        return values.length - 1;
    }

    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }

        if (capacity() == size) {
            resize((capacity() * 2)+1);
        }

        values[++size] = value;
        swim(size);
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return values[1];
    }

    public T removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T val = values[1];

        exch(1, size--);
        values[size+1] = null;
        sink(1);

        return val;
    }

    public void clear() {
        for (int i = 0; i < values.length; i++) {
            values[i] = null;
        }
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] temp = (T[]) new Comparable[newCapacity];
        for (int i = 0; i < values.length; i++) {
            temp[i] = values[i];
        }
        values = temp;
    }

    private void swim(int k) {
        while (k > 1 && less(k, k/2)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int head) {
        while (2*head <= size) {
            int currChild = head * 2;
            if (currChild < size && less(currChild+1, currChild)){
                currChild++;
            }
            if (!less(currChild, head)) {
                break;
            }
            exch(head, currChild);
            head = currChild;
        }
    }

    private boolean less(int i, int j) {
        return values[i].compareTo(values[j]) < 0;
    }

    private void exch(int i, int j) {
        T temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }
}
