package com.interviewprep.dsa.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class QueueTest {
    @Test
    void startsEmpty() {
        Queue<String> queue = new Queue<>();

        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        assertThrows(NoSuchElementException.class, queue::peek);
        assertThrows(NoSuchElementException.class, queue::dequeue);
    }

    @Test
    void enqueueAddsToBackAndPeekReturnsFront() {
        Queue<String> queue = new Queue<>();

        queue.enqueue("front");
        queue.enqueue("back");

        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("front", queue.peek());
    }

    @Test
    void dequeueReturnsValuesInFirstInFirstOutOrder() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");

        assertEquals("first", queue.dequeue());
        assertEquals("second", queue.dequeue());
        assertEquals("third", queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    void peekDoesNotRemoveValue() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(10);
        queue.enqueue(20);

        assertEquals(10, queue.peek());
        assertEquals(10, queue.peek());
        assertEquals(2, queue.size());
    }

    @Test
    void dequeueOnlyElementLeavesQueueEmptyAndReusable() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("only");

        assertEquals("only", queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());

        queue.enqueue("new");

        assertEquals(1, queue.size());
        assertEquals("new", queue.peek());
    }

    @Test
    void containsUsesValueEquality() {
        Queue<String> queue = new Queue<>();
        String stored = new String("same-value");
        String probe = new String("same-value");

        queue.enqueue("other");
        queue.enqueue(stored);

        assertTrue(queue.contains(probe));
        assertFalse(queue.contains("missing"));
        assertEquals(2, queue.size());
        assertEquals("other", queue.peek());
    }

    @Test
    void clearEmptiesQueueAndAllowsReuse() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("a");
        queue.enqueue("b");

        queue.clear();

        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        assertThrows(NoSuchElementException.class, queue::peek);

        queue.enqueue("new");

        assertEquals(1, queue.size());
        assertEquals("new", queue.dequeue());
    }

    @Test
    void methodsRejectNullValues() {
        Queue<String> queue = new Queue<>();

        assertThrows(IllegalArgumentException.class, () -> queue.enqueue(null));
        assertThrows(IllegalArgumentException.class, () -> queue.contains(null));
    }

    @Test
    void mixedEnqueueDequeueSequencePreservesOrderAndSize() {
        Queue<String> queue = new Queue<>();

        queue.enqueue("a");
        queue.enqueue("b");
        assertEquals("a", queue.dequeue());

        queue.enqueue("c");
        queue.enqueue("d");

        assertEquals(3, queue.size());
        assertEquals("b", queue.dequeue());
        assertEquals("c", queue.dequeue());
        assertEquals("d", queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}
