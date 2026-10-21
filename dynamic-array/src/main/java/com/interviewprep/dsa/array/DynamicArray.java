package com.interviewprep.dsa.array;

public class DynamicArray<T> {
    private static final int DEFAULT_CAPACITY = 4;

    private Object[] elements;
    private int size;

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than zero.");
        }

        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return elements.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Invalid Value");
        }

        if (size == elements.length) {
            Object[] temp = new Object[elements.length * 2];
            for (int i = 0; i < size; ++i) {
                temp[i] = elements[i];
            }

            elements = temp;
        }

        elements[size++] = value;
    }

    public T get(int index) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }

        return (T) elements[index];
    }

    public T set(int index, T value) {
        if (value == null) {
            throw new IllegalArgumentException("Invalid Value");
        }
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }

        T old = (T) elements[index];

        elements[index] = value;

        return old;
    }

    public T removeAt(int index) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }

        T old = (T) elements[index];

        elements[index] = null;

        if (index != size) {
            for (int i = index; i < size - 1; i++) {
                elements[i] = elements[i + 1];
            }
            elements[size - 1] = null;
        }

        --size;

        return old;
    }

    public boolean contains(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Invalid Value");
        }

        for (int i = 0; i < size; ++i) {
            T val = (T) elements[i];
            if (val != null && val.equals(value)) {
                return true;
            }
        }

        return false;
    }

    public void clear() {
        for (int i = 0; i < size; ++i) {
            elements[i] = null;
        }

        size = 0;
    }
}