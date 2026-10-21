package com.interviewprep.dsa.array;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DynamicArrayTest {
    @Test
    void defaultConstructorStartsEmptyWithDefaultCapacity() {
        DynamicArray<String> array = new DynamicArray<>();

        assertEquals(0, array.size());
        assertEquals(4, array.capacity());
        assertTrue(array.isEmpty());
    }

    @Test
    void customConstructorRejectsInvalidCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new DynamicArray<String>(0));
        assertThrows(IllegalArgumentException.class, () -> new DynamicArray<String>(-1));
    }

    @Test
    void addAppendsValuesAndGetReturnsByIndex() {
        DynamicArray<String> array = new DynamicArray<>(2);

        array.add("alpha");
        array.add("beta");

        assertEquals(2, array.size());
        assertFalse(array.isEmpty());
        assertEquals("alpha", array.get(0));
        assertEquals("beta", array.get(1));
    }

    @Test
    void addGrowsByDoublingWhenFull() {
        DynamicArray<Integer> array = new DynamicArray<>(2);

        array.add(10);
        array.add(20);
        array.add(30);

        assertEquals(3, array.size());
        assertEquals(4, array.capacity());
        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(30, array.get(2));
    }

    @Test
    void setReplacesValueAndReturnsOldValue() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("old");
        array.add("keep");

        String oldValue = array.set(0, "new");

        assertEquals("old", oldValue);
        assertEquals("new", array.get(0));
        assertEquals("keep", array.get(1));
        assertEquals(2, array.size());
    }

    @Test
    void removeAtReturnsRemovedValueAndShiftsLeft() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("first");
        array.add("middle");
        array.add("last");

        String removed = array.removeAt(1);

        assertEquals("middle", removed);
        assertEquals(2, array.size());
        assertEquals("first", array.get(0));
        assertEquals("last", array.get(1));
    }

    @Test
    void removeAtHandlesFirstAndLastPositions() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("a");
        array.add("b");
        array.add("c");

        assertEquals("a", array.removeAt(0));
        assertEquals("c", array.removeAt(1));

        assertEquals(1, array.size());
        assertEquals("b", array.get(0));
    }

    @Test
    void containsUsesValueEquality() {
        DynamicArray<String> array = new DynamicArray<>();
        String stored = new String("same-value");
        String probe = new String("same-value");

        array.add(stored);

        assertTrue(array.contains(probe));
        assertFalse(array.contains("missing"));
    }

    @Test
    void clearRemovesValuesButKeepsCapacity() {
        DynamicArray<String> array = new DynamicArray<>(2);
        array.add("a");
        array.add("b");
        array.add("c");

        array.clear();

        assertEquals(0, array.size());
        assertTrue(array.isEmpty());
        assertEquals(4, array.capacity());
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(0));
    }

    @Test
    void methodsRejectInvalidIndexes() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("only");

        assertThrows(IndexOutOfBoundsException.class, () -> array.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> array.set(-1, "x"));
        assertThrows(IndexOutOfBoundsException.class, () -> array.set(1, "x"));
        assertThrows(IndexOutOfBoundsException.class, () -> array.removeAt(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> array.removeAt(1));
    }

    @Test
    void methodsRejectNullValues() {
        DynamicArray<String> array = new DynamicArray<>();
        array.add("value");

        assertThrows(IllegalArgumentException.class, () -> array.add(null));
        assertThrows(IllegalArgumentException.class, () -> array.set(0, null));
        assertThrows(IllegalArgumentException.class, () -> array.contains(null));
    }
}
