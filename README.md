# Java Data Structures

Educational implementations of common data structures in Java, extracted from my independent programming exercises. This repository contains runnable Maven modules; it is separate from my private learning tracker.

## Implementations

| Module | Implementation details |
| --- | --- |
| [Dynamic Array](dynamic-array/src/main/java/com/interviewprep/dsa/array/DynamicArray.java) | Resizable array storage and indexed operations |
| [Singly Linked List](singly-linked-list/src/main/java/com/interviewprep/dsa/list/SinglyLinkedList.java) | Linked nodes and list operations |
| [Stack](stack/src/main/java/com/interviewprep/dsa/stack/Stack.java) | Last-in, first-out structure |
| [Queue](queue/src/main/java/com/interviewprep/dsa/queue/Queue.java) | First-in, first-out structure |
| [Hash Map](hash-map/src/main/java/com/interviewprep/dsa/hashmap/HashMap.java) | Separate chaining, collisions, key replacement, removal, and resizing |
| [Min Heap](min-heap/src/main/java/com/interviewprep/dsa/heap/MinHeap.java) | Generic array-backed priority structure with swim/sink operations |

## Build and run tests

Requires **JDK 17+** and **Apache Maven**.

```bash
mvn test
```

To test an individual module:

```bash
mvn -pl hash-map test
```

Each module contains its own `pom.xml`, implementation source, and JUnit tests. Run the tests from a fresh clone to verify behavior.

## Authorship and scope

- **Implementation code:** Written by me as independent exercise solutions.
- **Automated JUnit tests and exercise scaffolding:** Generated with AI assistance; I did **not** author the automated test suites.
- Educational implementations, not production-ready replacements for Java's standard collections.
- The original private study repository, textbooks, progress logs, and personal review notes are **not** included.
