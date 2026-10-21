package com.interviewprep.dsa.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class SinglyLinkedListTest {
    @Test
    void startsEmpty() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertThrows(NoSuchElementException.class, list::getFirst);
        assertThrows(NoSuchElementException.class, list::getLast);
        assertThrows(NoSuchElementException.class, list::removeFirst);
        assertThrows(NoSuchElementException.class, list::removeLast);
    }

    @Test
    void addFirstInsertsAtFront() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addFirst("middle");
        list.addFirst("first");

        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertEquals("first", list.getFirst());
        assertEquals("middle", list.getLast());
        assertEquals("first", list.get(0));
        assertEquals("middle", list.get(1));
    }

    @Test
    void addLastAppendsAtEnd() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("first");
        list.addLast("middle");
        list.addLast("last");

        assertEquals(3, list.size());
        assertEquals("first", list.getFirst());
        assertEquals("last", list.getLast());
        assertEquals("middle", list.get(1));
    }

    @Test
    void addFirstAndAddLastWorkTogether() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.addLast(2);
        list.addFirst(1);
        list.addLast(3);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void setReplacesValueAndReturnsOldValue() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("first");
        list.addLast("old");
        list.addLast("last");

        String old = list.set(1, "new");

        assertEquals("old", old);
        assertEquals("new", list.get(1));
        assertEquals(3, list.size());
        assertEquals("first", list.getFirst());
        assertEquals("last", list.getLast());
    }

    @Test
    void removeFirstUpdatesHeadAndTail() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("first");
        list.addLast("last");

        assertEquals("first", list.removeFirst());
        assertEquals(1, list.size());
        assertEquals("last", list.getFirst());
        assertEquals("last", list.getLast());

        assertEquals("last", list.removeFirst());
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertThrows(NoSuchElementException.class, list::getFirst);
        assertThrows(NoSuchElementException.class, list::getLast);
    }

    @Test
    void removeLastUpdatesTailAndAllowsAppendAgain() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("a");
        list.addLast("b");
        list.addLast("c");

        assertEquals("c", list.removeLast());
        assertEquals(2, list.size());
        assertEquals("a", list.getFirst());
        assertEquals("b", list.getLast());

        list.addLast("d");

        assertEquals(3, list.size());
        assertEquals("d", list.getLast());
        assertEquals("b", list.get(1));
        assertEquals("d", list.get(2));
    }

    @Test
    void removeAtHandlesFirstMiddleAndLast() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("a");
        list.addLast("b");
        list.addLast("c");
        list.addLast("d");

        assertEquals("b", list.removeAt(1));
        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("c", list.get(1));
        assertEquals("d", list.get(2));

        assertEquals("a", list.removeAt(0));
        assertEquals("d", list.removeAt(1));
        assertEquals(1, list.size());
        assertEquals("c", list.getFirst());
        assertEquals("c", list.getLast());
    }

    @Test
    void containsUsesValueEquality() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        String stored = new String("same-value");
        String probe = new String("same-value");

        list.addLast(stored);

        assertTrue(list.contains(probe));
        assertFalse(list.contains("missing"));
    }

    @Test
    void clearEmptiesListAndAllowsReuse() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("a");
        list.addLast("b");

        list.clear();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertThrows(NoSuchElementException.class, list::getFirst);
        assertThrows(NoSuchElementException.class, list::getLast);

        list.addLast("new");

        assertEquals(1, list.size());
        assertEquals("new", list.getFirst());
        assertEquals("new", list.getLast());
    }

    @Test
    void methodsRejectInvalidIndexes() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("only");

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "x"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, "x"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(1));
    }

    @Test
    void methodsRejectNullValues() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("value");

        assertThrows(IllegalArgumentException.class, () -> list.addFirst(null));
        assertThrows(IllegalArgumentException.class, () -> list.addLast(null));
        assertThrows(IllegalArgumentException.class, () -> list.set(0, null));
        assertThrows(IllegalArgumentException.class, () -> list.contains(null));
    }
}
