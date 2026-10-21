package com.interviewprep.dsa.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class HashMapTest {
    @Test
    void startsEmptyWithDefaultCapacity() {
        HashMap<String, Integer> map = new HashMap<>();

        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertEquals(4, map.capacity());
        assertNull(map.get("missing"));
        assertFalse(map.containsKey("missing"));
        assertNull(map.remove("missing"));
    }

    @Test
    void putStoresNewKeyAndGetReturnsValue() {
        HashMap<String, Integer> map = new HashMap<>();

        assertNull(map.put("a", 1));

        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        assertEquals(1, map.get("a"));
        assertTrue(map.containsKey("a"));
    }

    @Test
    void putReplacesExistingKeyWithoutGrowingSize() {
        HashMap<String, Integer> map = new HashMap<>();

        assertNull(map.put("a", 1));
        assertEquals(1, map.put("a", 2));

        assertEquals(1, map.size());
        assertEquals(2, map.get("a"));
    }

    @Test
    void methodsRejectNullKeysAndValues() {
        HashMap<String, Integer> map = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> map.put(null, 1));
        assertThrows(IllegalArgumentException.class, () -> map.put("a", null));
        assertThrows(IllegalArgumentException.class, () -> map.get(null));
        assertThrows(IllegalArgumentException.class, () -> map.containsKey(null));
        assertThrows(IllegalArgumentException.class, () -> map.remove(null));
    }

    @Test
    void containsKeyUsesValueEqualityNotReferenceIdentity() {
        HashMap<String, Integer> map = new HashMap<>();
        String stored = new String("same-key");
        String probe = new String("same-key");

        map.put(stored, 99);

        assertTrue(map.containsKey(probe));
        assertEquals(99, map.get(probe));
    }

    @Test
    void collisionChainStoresAndFindsMultipleKeys() {
        HashMap<CollisionKey, String> map = new HashMap<>();
        CollisionKey first = new CollisionKey("first");
        CollisionKey second = new CollisionKey("second");
        CollisionKey third = new CollisionKey("third");

        map.put(first, "A");
        map.put(second, "B");
        map.put(third, "C");

        assertEquals(3, map.size());
        assertEquals("A", map.get(first));
        assertEquals("B", map.get(second));
        assertEquals("C", map.get(third));
    }

    @Test
    void removeMiddleOfCollisionChainPreservesOtherEntries() {
        HashMap<CollisionKey, String> map = new HashMap<>();
        CollisionKey first = new CollisionKey("first");
        CollisionKey second = new CollisionKey("second");
        CollisionKey third = new CollisionKey("third");
        map.put(first, "A");
        map.put(second, "B");
        map.put(third, "C");

        assertEquals("B", map.remove(second));

        assertEquals(2, map.size());
        assertEquals("A", map.get(first));
        assertNull(map.get(second));
        assertEquals("C", map.get(third));
    }

    @Test
    void resizePreservesEntriesWhenLoadFactorIsExceeded() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);

        assertEquals(8, map.capacity());
        assertEquals(4, map.size());
        assertEquals(1, map.get("a"));
        assertEquals(2, map.get("b"));
        assertEquals(3, map.get("c"));
        assertEquals(4, map.get("d"));
    }

    @Test
    void clearEmptiesMapAndAllowsReuse() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);

        map.clear();

        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.get("a"));

        map.put("new", 10);

        assertEquals(1, map.size());
        assertEquals(10, map.get("new"));
    }

    private static final class CollisionKey {
        private final String value;

        private CollisionKey(String value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return 7;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CollisionKey)) {
                return false;
            }
            CollisionKey that = (CollisionKey) other;
            return value.equals(that.value);
        }
    }
}
