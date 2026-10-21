package com.interviewprep.dsa.stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class StackTest {
    @Test
    void startsEmpty() {
        Stack<String> stack = new Stack<>();

        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        assertThrows(NoSuchElementException.class, stack::peek);
        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Test
    void pushAddsToTop() {
        Stack<String> stack = new Stack<>();

        stack.push("bottom");
        stack.push("top");

        assertEquals(2, stack.size());
        assertFalse(stack.isEmpty());
        assertEquals("top", stack.peek());
    }

    @Test
    void popReturnsValuesInLastInFirstOutOrder() {
        Stack<String> stack = new Stack<>();
        stack.push("first");
        stack.push("second");
        stack.push("third");

        assertEquals("third", stack.pop());
        assertEquals("second", stack.pop());
        assertEquals("first", stack.pop());
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    @Test
    void peekDoesNotRemoveValue() {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);

        assertEquals(20, stack.peek());
        assertEquals(20, stack.peek());
        assertEquals(2, stack.size());
    }

    @Test
    void popOnlyElementLeavesStackEmptyAndReusable() {
        Stack<String> stack = new Stack<>();
        stack.push("only");

        assertEquals("only", stack.pop());
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());

        stack.push("new");

        assertEquals(1, stack.size());
        assertEquals("new", stack.peek());
    }

    @Test
    void containsUsesValueEquality() {
        Stack<String> stack = new Stack<>();
        String stored = new String("same-value");
        String probe = new String("same-value");

        stack.push("other");
        stack.push(stored);

        assertTrue(stack.contains(probe));
        assertFalse(stack.contains("missing"));
        assertEquals(2, stack.size());
        assertEquals(stored, stack.peek());
    }

    @Test
    void clearEmptiesStackAndAllowsReuse() {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");

        stack.clear();

        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        assertThrows(NoSuchElementException.class, stack::peek);

        stack.push("new");

        assertEquals(1, stack.size());
        assertEquals("new", stack.pop());
    }

    @Test
    void methodsRejectNullValues() {
        Stack<String> stack = new Stack<>();

        assertThrows(IllegalArgumentException.class, () -> stack.push(null));
        assertThrows(IllegalArgumentException.class, () -> stack.contains(null));
    }

    @Test
    void mixedPushPopSequencePreservesOrderAndSize() {
        Stack<String> stack = new Stack<>();

        stack.push("a");
        stack.push("b");
        assertEquals("b", stack.pop());

        stack.push("c");
        stack.push("d");

        assertEquals(3, stack.size());
        assertEquals("d", stack.pop());
        assertEquals("c", stack.pop());
        assertEquals("a", stack.pop());
        assertTrue(stack.isEmpty());
    }
}
