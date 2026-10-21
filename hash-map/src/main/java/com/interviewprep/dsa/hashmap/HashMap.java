package com.interviewprep.dsa.hashmap;

public class HashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 4;
    private static final double MAX_LOAD_FACTOR = 0.75;

    private static final class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry<K, V>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        buckets = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int capacity() {
        return buckets.length;
    }

    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }

        int ind = hash(key);
        for (Entry<K, V> x = buckets[ind]; x != null; x = x.next) {
            if (x.key.equals(key)) {
                V val = x.value;
                x.value = value;
                return val;
            }
        }

        if (size >= capacity() * MAX_LOAD_FACTOR) {
            resize(capacity() * 2);
            ind = hash(key);
        }

        Entry<K, V> newFront = new Entry<K, V>(key, value);
        newFront.next = buckets[ind];
        buckets[ind] = newFront;

        size++;
        return null;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        int ind = hash(key);

        for (Entry<K, V> x = buckets[ind]; x != null; x = x.next) {
            if (x.key.equals(key)) {
                return x.value;
            }
        }

        return null;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        int ind = hash(key);
        for (Entry<K, V> x = buckets[ind]; x != null; x = x.next) {
            if (x.key.equals(key)) {
                return true;
            }
        }

        return false;
    }

    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        int ind = hash(key);
        Entry<K, V> current = buckets[ind];

        if (current != null && current.key.equals(key)) {
            V val = current.value;
            buckets[ind] = current.next;
            size--;
            return val;
        }

        for (Entry<K, V> x = buckets[ind]; x != null; x = x.next) {
            if (x.next != null && x.next.key.equals(key)) {
                Entry<K, V> beforeRemove = x;
                Entry<K, V> toRemove = beforeRemove.next;
                beforeRemove.next = toRemove.next;
                size--;
                return toRemove.value;
            }
        }

        return null;
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;
        }
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Entry<K, V>[] newBuckets = (Entry<K, V>[]) new Entry[newCapacity];

        for (int i = 0; i < buckets.length; i++) {
            Entry<K, V> current = buckets[i];

            while (current != null) {
                int index = (current.key.hashCode() & 0x7fffffff) % newCapacity;
                Entry<K, V> nextEntry = current.next;

                current.next = newBuckets[index];
                newBuckets[index] = current;

                current = nextEntry;
            }
        }

        buckets = newBuckets;
    }

    private int hash(K x) {
        return (x.hashCode() & 0x7fffffff) % capacity();
    }
}
