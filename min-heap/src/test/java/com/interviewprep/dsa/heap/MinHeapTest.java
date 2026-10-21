package com.interviewprep.dsa.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class MinHeapTest {
    @Test
    void rejectsNonPositiveInitialCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new MinHeap<Integer>(0));
        assertThrows(IllegalArgumentException.class, () -> new MinHeap<Integer>(-1));
    }

    @Test
    void startsEmptyWithDefaultCapacity() {
        MinHeap<Integer> heap = new MinHeap<>();

        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());
        assertEquals(8, heap.capacity());
    }

    @Test
    void rejectsNullValue() {
        MinHeap<Integer> heap = new MinHeap<>();

        assertThrows(IllegalArgumentException.class, () -> heap.add(null));
    }

    @Test
    void addsValuesAndKeepsSmallestAtRoot() {
        MinHeap<Integer> heap = new MinHeap<>();

        heap.add(8);
        heap.add(3);
        heap.add(6);
        heap.add(1);

        assertEquals(4, heap.size());
        assertEquals(1, heap.peek());
    }

    @Test
    void removesValuesInAscendingOrder() {
        MinHeap<Integer> heap = new MinHeap<>();
        int[] input = { 7, 2, 9, 1, 5, 3, 8, 4, 6 };

        for (int value : input) {
            heap.add(value);
        }

        for (int expected = 1; expected <= 9; expected++) {
            assertEquals(expected, heap.removeMin());
        }

        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
    }

    @Test
    void preservesDuplicateValues() {
        MinHeap<Integer> heap = new MinHeap<>();

        heap.add(4);
        heap.add(2);
        heap.add(4);
        heap.add(1);
        heap.add(2);

        assertEquals(1, heap.removeMin());
        assertEquals(2, heap.removeMin());
        assertEquals(2, heap.removeMin());
        assertEquals(4, heap.removeMin());
        assertEquals(4, heap.removeMin());
    }

    @Test
    void ordersNegativeAndExtremeValues() {
        MinHeap<Integer> heap = new MinHeap<>();

        heap.add(0);
        heap.add(Integer.MAX_VALUE);
        heap.add(-1);
        heap.add(Integer.MIN_VALUE);
        heap.add(8);

        assertEquals(Integer.MIN_VALUE, heap.removeMin());
        assertEquals(-1, heap.removeMin());
        assertEquals(0, heap.removeMin());
        assertEquals(8, heap.removeMin());
        assertEquals(Integer.MAX_VALUE, heap.removeMin());
    }

    @Test
    void growsByDoublingWhenFull() {
        MinHeap<Integer> heap = new MinHeap<>(2);

        heap.add(5);
        heap.add(3);
        assertEquals(2, heap.capacity());

        heap.add(1);

        assertEquals(4, heap.capacity());
        assertEquals(3, heap.size());
        assertEquals(1, heap.peek());
    }

    @Test
    void supportsInterleavedAddsAndRemovals() {
        MinHeap<Integer> heap = new MinHeap<>();

        heap.add(10);
        heap.add(4);
        heap.add(7);
        assertEquals(4, heap.removeMin());

        heap.add(2);
        heap.add(12);
        assertEquals(2, heap.removeMin());
        assertEquals(7, heap.removeMin());
        assertEquals(10, heap.peek());
        assertEquals(2, heap.size());
    }

    @Test
    void worksWithOtherComparableTypes() {
        MinHeap<String> heap = new MinHeap<>();

        heap.add("pear");
        heap.add("apple");
        heap.add("orange");

        assertEquals("apple", heap.removeMin());
        assertEquals("orange", heap.removeMin());
        assertEquals("pear", heap.removeMin());
    }

    @Test
    void emptyPeekAndRemoveThrow() {
        MinHeap<Integer> heap = new MinHeap<>();

        assertThrows(NoSuchElementException.class, heap::peek);
        assertThrows(NoSuchElementException.class, heap::removeMin);
    }

    @Test
    void clearKeepsCapacityAndAllowsReuse() {
        MinHeap<Integer> heap = new MinHeap<>(2);
        heap.add(3);
        heap.add(1);
        heap.add(2);
        int grownCapacity = heap.capacity();

        heap.clear();

        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        assertEquals(grownCapacity, heap.capacity());
        assertThrows(NoSuchElementException.class, heap::peek);

        heap.add(9);
        heap.add(4);
        assertEquals(4, heap.peek());
    }
}
