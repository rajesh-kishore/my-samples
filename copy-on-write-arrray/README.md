# Copy-on-Write List

A Java implementation of a copy-on-write data structure that supports point-in-time snapshots with efficient memory usage.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [How It Works](#how-it-works)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Examples](#examples)
- [Testing](#testing)
- [Performance Characteristics](#performance-characteristics)
- [Contributing](#contributing)

## 🎯 Overview

This project implements a **Copy-on-Write List** that allows you to create immutable snapshots of the list at any point in time. When values are modified in the parent list, existing snapshots preserve their original values through a copy-on-write mechanism, ensuring data consistency and isolation.

### What is Copy-on-Write?

Copy-on-Write (CoW) is a resource optimization technique where multiple callers can share the same data until one of them needs to modify it. Instead of making copies upfront, the data is only copied when a write operation occurs.

## ✨ Features

- ✅ **Point-in-Time Snapshots**: Create immutable views of the list at any moment
- ✅ **Memory Efficient**: Only copies data when modifications occur
- ✅ **Snapshot Isolation**: Each snapshot maintains its view independent of parent changes
- ✅ **Type-Safe**: Strongly typed with comprehensive API
- ✅ **Well-Documented**: Extensive JavaDoc and inline comments
- ✅ **Thoroughly Tested**: 41+ unit tests covering all scenarios

## 🔧 How It Works

### Basic Concept

```
Initial State: [1, 2, 3, 4]
    ↓
Create Snapshot-1: [1, 2, 3, 4]
    ↓
Update parent[1] = 20
    ↓
Parent:     [1, 20, 3, 4]  ← Updated
Snapshot-1: [1, 2, 3, 4]   ← Unchanged (preserves original)
```

### Internal Mechanism

1. **Snapshots share data with parent** - Initially, snapshots don't copy any data
2. **Copy-on-write triggers on update** - When parent updates a value, it's first copied to all snapshots
3. **Snapshots store only changes** - Each snapshot maintains a HashMap of modified values
4. **Read-through for unchanged values** - For values not in the HashMap, snapshots read from parent

This approach minimizes memory usage while maintaining snapshot immutability.

## 💾 Installation

### Prerequisites

- Java 17 or higher
- Gradle 7.x or higher

### Clone the Repository

```bash
git clone <repository-url>
cd copy-on-write-arrray
```

### Build the Project

```bash
./gradlew build
```

## 🚀 Usage

### Quick Start

```java
import org.example.library.CopyOnWriteList;
import org.example.library.CopyOnWriteListSnapshot;

public class Example {
    public static void main(String[] args) {
        // Create a new copy-on-write list
        CopyOnWriteList list = new CopyOnWriteList();
        
        // Add some values
        list.appendValue(1);
        list.appendValue(2);
        list.appendValue(3);
        
        // Create a snapshot
        CopyOnWriteListSnapshot snapshot = list.createSnapshot();
        System.out.println("Snapshot: " + snapshot.getCurrentVals());
        // Output: Snapshot: [1, 2, 3]
        
        // Modify the parent list
        list.updateValue(0, 100);
        list.appendValue(4);
        
        // Parent shows changes
        System.out.println("Parent: " + list.getCurrentVals());
        // Output: Parent: [100, 2, 3, 4]
        
        // Snapshot remains unchanged
        System.out.println("Snapshot: " + snapshot.getCurrentVals());
        // Output: Snapshot: [1, 2, 3]
    }
}
```

### Running the Demo

```bash
./gradlew run
```

Or compile and run manually:

```bash
cd src/main/java
javac org/example/library/*.java
java org.example.library.Main
```

## 📚 API Documentation

### CopyOnWriteList

#### Methods

| Method | Description | Returns |
|--------|-------------|---------|
| `appendValue(int val)` | Appends a value to the end of the list | `void` |
| `updateValue(int index, int newValue)` | Updates value at specified index (triggers copy-on-write) | `void` |
| `getValue(int index)` | Returns value at specified index | `int` |
| `getCurrentVals()` | Returns unmodifiable view of current list | `List<Integer>` |
| `createSnapshot()` | Creates a point-in-time snapshot | `CopyOnWriteListSnapshot` |

### CopyOnWriteListSnapshot

#### Methods

| Method | Description | Returns |
|--------|-------------|---------|
| `getValue(int index)` | Returns value at index as it was at snapshot creation | `int` |
| `getCurrentVals()` | Returns unmodifiable view of snapshot values | `List<Integer>` |
| `getSnapshotId()` | Returns unique identifier of the snapshot | `String` |

## 💡 Examples

### Example 1: Basic Snapshot Creation

```java
CopyOnWriteList list = new CopyOnWriteList();
list.appendValue(10);
list.appendValue(20);

CopyOnWriteListSnapshot snapshot = list.createSnapshot();

list.updateValue(0, 99);

System.out.println(list.getValue(0));      // 99
System.out.println(snapshot.getValue(0));  // 10 (original value preserved)
```

### Example 2: Multiple Snapshots

```java
CopyOnWriteList list = new CopyOnWriteList();
list.appendValue(1);
list.appendValue(2);

CopyOnWriteListSnapshot snap1 = list.createSnapshot();  // [1, 2]

list.updateValue(0, 10);

CopyOnWriteListSnapshot snap2 = list.createSnapshot();  // [10, 2]

list.updateValue(0, 100);

System.out.println(list.getCurrentVals());   // [100, 2]
System.out.println(snap1.getCurrentVals());  // [1, 2]
System.out.println(snap2.getCurrentVals());  // [10, 2]
```

### Example 3: Snapshot Size Isolation

```java
CopyOnWriteList list = new CopyOnWriteList();
list.appendValue(1);
list.appendValue(2);

CopyOnWriteListSnapshot snapshot = list.createSnapshot();  // Size: 2

list.appendValue(3);
list.appendValue(4);

System.out.println(list.getCurrentVals().size());      // 4
System.out.println(snapshot.getCurrentVals().size());  // 2 (size fixed at creation)
```

## 🧪 Testing

The project includes comprehensive test coverage with 41+ test cases.

### Run Tests

```bash
./gradlew test
```

### Test Coverage

- ✅ Basic operations (append, update, get)
- ✅ Snapshot creation and isolation
- ✅ Copy-on-write behavior verification
- ✅ Multiple snapshots handling
- ✅ Boundary conditions
- ✅ Exception handling
- ✅ Complex integration scenarios
- ✅ Edge cases

### Test Report

After running tests, view the HTML report:

```bash
open build/reports/tests/test/index.html
```

## ⚡ Performance Characteristics

### Time Complexity

| Operation | Best Case | Average Case | Worst Case |
|-----------|-----------|--------------|------------|
| `appendValue()` | O(1) | O(1) | O(1) amortized |
| `updateValue()` | O(1) | O(n) | O(n) * |
| `getValue()` | O(1) | O(1) | O(1) |
| `createSnapshot()` | O(1) | O(1) | O(1) |
| `snapshot.getValue()` | O(1) | O(1) | O(1) |

\* Where n = number of active snapshots

### Space Complexity

- **Parent List**: O(n) where n = number of elements
- **Each Snapshot**: O(k) where k = number of modifications since snapshot creation
- **Best Case**: O(1) per snapshot (no modifications)
- **Worst Case**: O(n) per snapshot (all values modified)

### Memory Efficiency

The implementation is memory-efficient because:
1. Snapshots share unmodified data with the parent
2. Only modified values are stored in snapshot's HashMap
3. No upfront copying occurs during snapshot creation

## 🏗️ Architecture

### Class Diagram

```
┌─────────────────────────┐
│   CopyOnWriteList       │
├─────────────────────────┤
│ - currentVals: List     │
│ - snapshots: List       │
├─────────────────────────┤
│ + appendValue()         │
│ + updateValue()         │
│ + getValue()            │
│ + createSnapshot()      │
│ + getCurrentVals()      │
└───────────┬─────────────┘
            │ creates
            │ notifies
            ↓
┌─────────────────────────┐
│ CopyOnWriteListSnapshot │
├─────────────────────────┤
│ - snapshotId: String    │
│ - parent: CopyOnWrite.. │
│ - snapshotData: Map     │
│ - snapshotSize: int     │
├─────────────────────────┤
│ + getValue()            │
│ + getCurrentVals()      │
│ + getSnapshotId()       │
│ + insertEntry()         │
└─────────────────────────┘
```

### Design Decisions

1. **HashMap for Snapshot Storage**: O(1) lookup for modified values
2. **LinkedList for Snapshot Collection**: Efficient iteration during updates
3. **Size Capture**: Fixed size at creation prevents array growth issues
4. **Unmodifiable Views**: Ensures external code can't break immutability

## 🔐 Thread Safety

**⚠️ Important**: This implementation is **not thread-safe**. 

If you need concurrent access, consider:
- External synchronization using `synchronized` blocks
- Using concurrent data structures
- Implementing a thread-safe wrapper class

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

### Development Setup

1. Clone the repository
2. Import into IntelliJ IDEA or Eclipse
3. Run tests to verify setup: `./gradlew test`

### Code Style

- Follow Java naming conventions
- Add JavaDoc for public APIs
- Include unit tests for new features
- Maintain test coverage above 90%

## 👥 Authors

- Copy-on-Write Implementation Team

## 🙏 Acknowledgments

- Inspired by Copy-on-Write patterns in concurrent programming
- Follows design principles from Java Collections Framework
- Test patterns based on JUnit 5 best practices

## 📞 Contact

For questions or issues, please open an issue on the repository.

---

**Built with ❤️ using Java and Gradle**

