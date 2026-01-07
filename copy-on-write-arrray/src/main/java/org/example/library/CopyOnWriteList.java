package org.example.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A copy-on-write implementation of a list that supports point-in-time snapshots.
 *
 * <p>This data structure allows creating immutable snapshots of the list at any point in time.
 * When values are updated after a snapshot is created, the snapshot preserves the original values
 * through a copy-on-write mechanism. This ensures that snapshots remain consistent and isolated
 * from subsequent modifications to the parent list.
 *
 * <p><b>Usage Example:</b>
 * <pre>{@code
 * CopyOnWriteList list = new CopyOnWriteList();
 * list.appendValue(1);
 * list.appendValue(2);
 *
 * CopyOnWriteListSnapshot snapshot = list.createSnapshot(); // Creates snapshot: [1, 2]
 *
 * list.updateValue(0, 100); // Updates parent to [100, 2]
 *
 * // Parent list now shows [100, 2]
 * // Snapshot still shows [1, 2] - original values preserved
 * }</pre>
 *
 * <p><b>Thread Safety:</b> This implementation is not thread-safe. External synchronization
 * is required if multiple threads access this list concurrently.
 *
 * @author Copy-on-Write Implementation
 * @version 1.0
 */
public class CopyOnWriteList {

	/** The current state of the list containing all values */
	private final List<Integer> currentVals = new ArrayList<>();

	/** Collection of all active snapshots that need to be notified of updates */
	private final List<CopyOnWriteListSnapshot> snapshots = new LinkedList<>();

	/** Tracks the current size of the list (currently unused) */
	private int currentSize = -1;

	/**
	 * Creates a new snapshot capturing the current state of the list.
	 *
	 * <p>The snapshot is a point-in-time view of the list. Any subsequent modifications
	 * to the parent list will not be reflected in the snapshot. The snapshot maintains
	 * immutability by storing copies of values that are modified after its creation.
	 *
	 * @return a new {@link CopyOnWriteListSnapshot} representing the current state
	 */
	public CopyOnWriteListSnapshot createSnapshot() {
		String snapshotId = "Snapshot-" + (snapshots.size() + 1);
		CopyOnWriteListSnapshot snapshot = new CopyOnWriteListSnapshot(snapshotId, this);
		snapshots.add(snapshot);
		return snapshot;
	}

	/**
	 * Appends a new value to the end of the list.
	 *
	 * <p>Existing snapshots are not affected by this operation since they only track
	 * values that existed at the time they were created. Accessing an index that was
	 * added after a snapshot's creation will result in an {@link IndexOutOfBoundsException}.
	 *
	 * @param val the integer value to append to the list
	 */
	public void appendValue(int val) {
		currentSize++;
		currentVals.add(val);
	}

	/**
	 * Updates the value at the specified index in the list.
	 *
	 * <p>Before updating, this method copies the old value to all existing snapshots
	 * that haven't yet recorded a value for this index. This ensures that snapshots
	 * preserve the value as it was at the time of their creation.
	 *
	 * <p><b>Copy-on-Write Behavior:</b>
	 * <ul>
	 *   <li>All existing snapshots are notified and store the old value</li>
	 *   <li>The parent list is then updated with the new value</li>
	 *   <li>Each snapshot only stores the first value it sees for each index</li>
	 * </ul>
	 *
	 * @param index the zero-based index of the element to update
	 * @param newValue the new value to set at the specified index
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	public void updateValue(int index, int newValue) {
		// Copy old value to all snapshots before modification
		snapshots.forEach(entry -> {
			entry.insertEntry(index, currentVals.get(index));
		});

		// Now update the parent list with the new value
		currentVals.set(index, newValue);
	}

	/**
	 * Returns the value at the specified index in the current list.
	 *
	 * @param index the zero-based index of the element to retrieve
	 * @return the value at the specified index
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	public int getValue(int index) {
		return currentVals.get(index);
	}

	/**
	 * Returns an unmodifiable view of the current list values.
	 *
	 * <p>The returned list is a read-only view. Any attempts to modify it will
	 * result in an {@link UnsupportedOperationException}.
	 *
	 * @return an unmodifiable {@link List} containing the current values
	 */
	public List<Integer> getCurrentVals() {
		return Collections.unmodifiableList(currentVals);
	}

}


/**
 * Represents an immutable point-in-time snapshot of a {@link CopyOnWriteList}.
 *
 * <p>A snapshot captures the state of the parent list at the moment it was created.
 * It maintains this state by storing copies of values that are modified in the parent
 * list after the snapshot's creation. For values that haven't been modified, the
 * snapshot delegates to the parent list for efficient memory usage.
 *
 * <p><b>Key Characteristics:</b>
 * <ul>
 *   <li><b>Immutability:</b> Once created, the snapshot's view never changes</li>
 *   <li><b>Size Fixed:</b> The snapshot only sees indices that existed at creation time</li>
 *   <li><b>Copy-on-Write:</b> Values are only copied when modified in the parent</li>
 *   <li><b>Memory Efficient:</b> Shares unmodified values with the parent list</li>
 * </ul>
 *
 * <p><b>Internal Implementation:</b>
 * The snapshot uses a {@link HashMap} to store only the values that have been modified
 * in the parent list after the snapshot was created. For unmodified values, it reads
 * directly from the parent list, ensuring memory efficiency.
 *
 * @author Copy-on-Write Implementation
 * @version 1.0
 */
class CopyOnWriteListSnapshot {

	/** Unique identifier for this snapshot (e.g., "Snapshot-1") */
	private final String snapshotId;

	/** Reference to the parent list for accessing unmodified values */
	private final CopyOnWriteList parent;

	/**
	 * Stores values that were modified in the parent after this snapshot was created.
	 * Key: index, Value: the original value at that index when snapshot was created
	 */
	private final Map<Integer, Integer> snapshotData = new HashMap<>();

	/** The size of the parent list at the time this snapshot was created */
	private final int snapshotSize;

	/**
	 * Creates a new snapshot of the parent list's current state.
	 *
	 * <p>This constructor captures the size of the parent list at creation time
	 * and establishes a reference to the parent for reading unmodified values.
	 *
	 * @param snapshotId unique identifier for this snapshot
	 * @param parent the parent {@link CopyOnWriteList} to snapshot
	 */
	public CopyOnWriteListSnapshot(String snapshotId, CopyOnWriteList parent) {
		this.snapshotId = snapshotId;
		this.parent = parent;
		this.snapshotSize = parent.getCurrentVals().size();
	}

	/**
	 * Inserts an entry into this snapshot's data map.
	 *
	 * <p>This method is called by the parent list when a value is being updated.
	 * It stores the original value before the modification occurs. If the index
	 * already has a stored value (from a previous update), this method does nothing,
	 * ensuring the snapshot preserves the value as it was at creation time.
	 *
	 * <p><b>Copy-on-Write Semantics:</b>
	 * Only the first value for each index is stored. Subsequent updates to the
	 * same index in the parent list will not overwrite the snapshot's stored value.
	 *
	 * @param index the index whose value is being updated in the parent
	 * @param value the original value before the update
	 */
	public void insertEntry(int index, int value) {
		// Only store the value if we haven't already captured it
		if (!snapshotData.containsKey(index)) {
			snapshotData.put(index, value);
		}
	}

	/**
	 * Returns the value at the specified index as it was when this snapshot was created.
	 *
	 * <p>This method first checks if the value for this index has been stored in the
	 * snapshot's data map (meaning it was modified in the parent after snapshot creation).
	 * If not found, it reads the value directly from the parent list, as it hasn't changed.
	 *
	 * @param index the zero-based index of the element to retrieve
	 * @return the value at the specified index at the time of snapshot creation
	 * @throws IndexOutOfBoundsException if the index is negative or greater than or
	 *         equal to the snapshot's size
	 */
	public int getValue(int index) {
		// Validate index against snapshot size (not current parent size)
		if (index < 0 || index >= snapshotSize) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Snapshot Size: " + snapshotSize);
		}

		// Return stored value if modified, otherwise read from parent
		return snapshotData.containsKey(index) ? snapshotData.get(index) : parent.getValue(index);
	}

	/**
	 * Returns an unmodifiable list of all values in this snapshot.
	 *
	 * <p>The returned list represents the state of the parent list at the time
	 * this snapshot was created. It contains exactly {@link #snapshotSize} elements,
	 * regardless of how many elements have been added to the parent since.
	 *
	 * @return an unmodifiable {@link List} containing all snapshot values
	 */
	public List<Integer> getCurrentVals() {
		List<Integer> values = new ArrayList<>();
		// Iterate only up to the size at snapshot creation time
		for (int i = 0; i < snapshotSize; i++) {
			values.add(getValue(i));
		}
		return Collections.unmodifiableList(values);
	}

	/**
	 * Returns the unique identifier of this snapshot.
	 *
	 * @return the snapshot ID (e.g., "Snapshot-1")
	 */
	public String getSnapshotId() {
		return snapshotId;
	}

}
