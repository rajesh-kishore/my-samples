package org.example.library;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("CopyOnWriteList Tests")
class CopyOnWriteListTest {

	private CopyOnWriteList copyOnWriteList;

	@BeforeEach
	void setUp() {
		copyOnWriteList = new CopyOnWriteList();
	}

	@Nested
	@DisplayName("appendValue Tests")
	class AppendValueTests {

		@Test
		@DisplayName("test_appendValue_when_addingSingleValue_then_valueIsAddedToList")
		void test_appendValue_when_addingSingleValue_then_valueIsAddedToList() {
			copyOnWriteList.appendValue(10);

			assertEquals(1, copyOnWriteList.getCurrentVals().size());
			assertEquals(10, copyOnWriteList.getValue(0));
		}

		@Test
		@DisplayName("test_appendValue_when_addingMultipleValues_then_allValuesAreAddedInOrder")
		void test_appendValue_when_addingMultipleValues_then_allValuesAreAddedInOrder() {
			copyOnWriteList.appendValue(1);
			copyOnWriteList.appendValue(2);
			copyOnWriteList.appendValue(3);
			copyOnWriteList.appendValue(4);

			List<Integer> currentVals = copyOnWriteList.getCurrentVals();
			assertEquals(4, currentVals.size());
			assertEquals(List.of(1, 2, 3, 4), currentVals);
		}

		@Test
		@DisplayName("test_appendValue_when_addingNegativeValue_then_valueIsAdded")
		void test_appendValue_when_addingNegativeValue_then_valueIsAdded() {
			copyOnWriteList.appendValue(-100);

			assertEquals(1, copyOnWriteList.getCurrentVals().size());
			assertEquals(-100, copyOnWriteList.getValue(0));
		}

		@Test
		@DisplayName("test_appendValue_when_addingZero_then_valueIsAdded")
		void test_appendValue_when_addingZero_then_valueIsAdded() {
			copyOnWriteList.appendValue(0);

			assertEquals(1, copyOnWriteList.getCurrentVals().size());
			assertEquals(0, copyOnWriteList.getValue(0));
		}

		@Test
		@DisplayName("test_appendValue_when_addingAfterSnapshot_then_snapshotDoesNotSeeNewValue")
		void test_appendValue_when_addingAfterSnapshot_then_snapshotDoesNotSeeNewValue() {
			copyOnWriteList.appendValue(1);
			copyOnWriteList.appendValue(2);

			CopyOnWriteListSnapshot snapshot = copyOnWriteList.createSnapshot();

			copyOnWriteList.appendValue(3);

			assertEquals(3, copyOnWriteList.getCurrentVals().size());
			assertEquals(2, snapshot.getCurrentVals().size());
			assertEquals(List.of(1, 2), snapshot.getCurrentVals());
		}
	}

	@Nested
	@DisplayName("getValue Tests")
	class GetValueTests {

		@Test
		@DisplayName("test_getValue_when_indexIsValid_then_returnsCorrectValue")
		void test_getValue_when_indexIsValid_then_returnsCorrectValue() {
			copyOnWriteList.appendValue(10);
			copyOnWriteList.appendValue(20);
			copyOnWriteList.appendValue(30);

			assertEquals(10, copyOnWriteList.getValue(0));
			assertEquals(20, copyOnWriteList.getValue(1));
			assertEquals(30, copyOnWriteList.getValue(2));
		}

		@Test
		@DisplayName("test_getValue_when_indexIsNegative_then_throwsIndexOutOfBoundsException")
		void test_getValue_when_indexIsNegative_then_throwsIndexOutOfBoundsException() {
			copyOnWriteList.appendValue(10);

			assertThrows(IndexOutOfBoundsException.class, () -> {
				copyOnWriteList.getValue(-1);
			});
		}

		@Test
		@DisplayName("test_getValue_when_indexIsOutOfBounds_then_throwsIndexOutOfBoundsException")
		void test_getValue_when_indexIsOutOfBounds_then_throwsIndexOutOfBoundsException() {
			copyOnWriteList.appendValue(10);

			assertThrows(IndexOutOfBoundsException.class, () -> {
				copyOnWriteList.getValue(5);
			});
		}

		@Test
		@DisplayName("test_getValue_when_listIsEmpty_then_throwsIndexOutOfBoundsException")
		void test_getValue_when_listIsEmpty_then_throwsIndexOutOfBoundsException() {
			assertThrows(IndexOutOfBoundsException.class, () -> {
				copyOnWriteList.getValue(0);
			});
		}
	}

	@Nested
	@DisplayName("updateValue Tests")
	class UpdateValueTests {

		@Test
		@DisplayName("test_updateValue_when_noSnapshotsExist_then_valueIsUpdated")
		void test_updateValue_when_noSnapshotsExist_then_valueIsUpdated() {
			copyOnWriteList.appendValue(10);
			copyOnWriteList.appendValue(20);

			copyOnWriteList.updateValue(0, 100);

			assertEquals(100, copyOnWriteList.getValue(0));
			assertEquals(20, copyOnWriteList.getValue(1));
		}

		@Test
		@DisplayName("test_updateValue_when_snapshotExistsBefore_then_snapshotRetainsOldValue")
		void test_updateValue_when_snapshotExistsBefore_then_snapshotRetainsOldValue() {
			copyOnWriteList.appendValue(1);
			copyOnWriteList.appendValue(2);
			copyOnWriteList.appendValue(3);

			CopyOnWriteListSnapshot snapshot = copyOnWriteList.createSnapshot();

			copyOnWriteList.updateValue(1, 20);

			assertEquals(20, copyOnWriteList.getValue(1));
			assertEquals(2, snapshot.getValue(1));
			assertEquals(List.of(1, 2, 3), snapshot.getCurrentVals());
		}

		@Test
		@DisplayName("test_updateValue_when_multipleSnapshotsExist_then_allSnapshotsRetainOldValue")
		void test_updateValue_when_multipleSnapshotsExist_then_allSnapshotsRetainOldValue() {
			copyOnWriteList.appendValue(1);
			copyOnWriteList.appendValue(2);
			copyOnWriteList.appendValue(3);

			CopyOnWriteListSnapshot snapshot1 = copyOnWriteList.createSnapshot();
			CopyOnWriteListSnapshot snapshot2 = copyOnWriteList.createSnapshot();

			copyOnWriteList.updateValue(0, 100);

			assertEquals(100, copyOnWriteList.getValue(0));
			assertEquals(1, snapshot1.getValue(0));
			assertEquals(1, snapshot2.getValue(0));
		}

		@Test
		@DisplayName("test_updateValue_when_updatingMultipleTimes_then_snapshotsRetainValueAtTimeOfCreation")
		void test_updateValue_when_updatingMultipleTimes_then_snapshotsRetainValueAtTimeOfCreation() {
			copyOnWriteList.appendValue(1);
			copyOnWriteList.appendValue(2);

			CopyOnWriteListSnapshot snapshot1 = copyOnWriteList.createSnapshot();

			copyOnWriteList.updateValue(0, 10);

			CopyOnWriteListSnapshot snapshot2 = copyOnWriteList.createSnapshot();

			copyOnWriteList.updateValue(0, 100);

			assertEquals(100, copyOnWriteList.getValue(0));
			assertEquals(1, snapshot1.getValue(0));
			assertEquals(10, snapshot2.getValue(0));
		}

		@Test
		@DisplayName("test_updateValue_when_updatingSameIndexMultipleTimes_then_snapshotsPreserveFirstValue")
		void test_updateValue_when_updatingSameIndexMultipleTimes_then_snapshotsPreserveFirstValue() {
			copyOnWriteList.appendValue(5);

			CopyOnWriteListSnapshot snapshot = copyOnWriteList.createSnapshot();

			copyOnWriteList.updateValue(0, 10);
			copyOnWriteList.updateValue(0, 20);
			copyOnWriteList.updateValue(0, 30);

			assertEquals(30, copyOnWriteList.getValue(0));
			assertEquals(5, snapshot.getValue(0));
		}

		@Test
		@DisplayName("test_updateValue_when_updatingLastElement_then_onlyLastElementIsUpdated")
		void test_updateValue_when_updatingLastElement_then_onlyLastElementIsUpdated() {
			copyOnWriteList.appendValue(1);
			copyOnWriteList.appendValue(2);
			copyOnWriteList.appendValue(3);

			copyOnWriteList.updateValue(2, 300);

			assertEquals(List.of(1, 2, 300), copyOnWriteList.getCurrentVals());
		}
	}

	@Nested
	@DisplayName("createSnapshot Tests")
	class CreateSnapshotTests {

		@Test
		@DisplayName("test_createSnapshot_when_listIsEmpty_then_snapshotIsCreated")
		void test_createSnapshot_when_listIsEmpty_then_snapshotIsCreated() {
			CopyOnWriteListSnapshot snapshot = copyOnWriteList.createSnapshot();

			assertNotNull(snapshot);
			assertEquals("Snapshot-1", snapshot.getSnapshotId());
			assertEquals(0, snapshot.getCurrentVals().size());
		}

		@Test
		@DisplayName("test_createSnapshot_when_listHasValues_then_snapshotReflectsCurrentState")
		void test_createSnapshot_when_listHasValues_then_snapshotReflectsCurrentState() {
			copyOnWriteList.appendValue(1);
			copyOnWriteList.appendValue(2);
			copyOnWriteList.appendValue(3);

			CopyOnWriteListSnapshot snapshot = copyOnWriteList.createSnapshot();

			assertEquals(List.of(1, 2, 3), snapshot.getCurrentVals());
		}

		@Test
		@DisplayName("test_createSnapshot_when_creatingMultipleSnapshots_then_eachHasUniqueId")
		void test_createSnapshot_when_creatingMultipleSnapshots_then_eachHasUniqueId() {
			CopyOnWriteListSnapshot snapshot1 = copyOnWriteList.createSnapshot();
			CopyOnWriteListSnapshot snapshot2 = copyOnWriteList.createSnapshot();
			CopyOnWriteListSnapshot snapshot3 = copyOnWriteList.createSnapshot();

			assertEquals("Snapshot-1", snapshot1.getSnapshotId());
			assertEquals("Snapshot-2", snapshot2.getSnapshotId());
			assertEquals("Snapshot-3", snapshot3.getSnapshotId());
		}

		@Test
		@DisplayName("test_createSnapshot_when_createdAfterUpdates_then_snapshotReflectsLatestState")
		void test_createSnapshot_when_createdAfterUpdates_then_snapshotReflectsLatestState() {
			copyOnWriteList.appendValue(1);
			copyOnWriteList.appendValue(2);
			copyOnWriteList.updateValue(0, 100);

			CopyOnWriteListSnapshot snapshot = copyOnWriteList.createSnapshot();

			assertEquals(List.of(100, 2), snapshot.getCurrentVals());
		}
	}

	@Nested
	@DisplayName("getCurrentVals Tests")
	class GetCurrentValsTests {

		@Test
		@DisplayName("test_getCurrentVals_when_listIsEmpty_then_returnsEmptyList")
		void test_getCurrentVals_when_listIsEmpty_then_returnsEmptyList() {
			List<Integer> currentVals = copyOnWriteList.getCurrentVals();

			assertNotNull(currentVals);
			assertTrue(currentVals.isEmpty());
		}

		@Test
		@DisplayName("test_getCurrentVals_when_listHasValues_then_returnsAllValues")
		void test_getCurrentVals_when_listHasValues_then_returnsAllValues() {
			copyOnWriteList.appendValue(10);
			copyOnWriteList.appendValue(20);
			copyOnWriteList.appendValue(30);

			List<Integer> currentVals = copyOnWriteList.getCurrentVals();

			assertEquals(3, currentVals.size());
			assertEquals(List.of(10, 20, 30), currentVals);
		}

		@Test
		@DisplayName("test_getCurrentVals_when_returningList_then_listIsUnmodifiable")
		void test_getCurrentVals_when_returningList_then_listIsUnmodifiable() {
			copyOnWriteList.appendValue(1);

			List<Integer> currentVals = copyOnWriteList.getCurrentVals();

			assertThrows(UnsupportedOperationException.class, () -> {
				currentVals.add(2);
			});
		}

		@Test
		@DisplayName("test_getCurrentVals_when_afterUpdate_then_returnsUpdatedValues")
		void test_getCurrentVals_when_afterUpdate_then_returnsUpdatedValues() {
			copyOnWriteList.appendValue(1);
			copyOnWriteList.appendValue(2);
			copyOnWriteList.updateValue(0, 100);

			List<Integer> currentVals = copyOnWriteList.getCurrentVals();

			assertEquals(List.of(100, 2), currentVals);
		}
	}

	@Nested
	@DisplayName("CopyOnWriteListSnapshot Tests")
	class CopyOnWriteListSnapshotTests {

		@Test
		@DisplayName("test_snapshotGetValue_when_accessingUnmodifiedIndex_then_returnsOriginalValue")
		void test_snapshotGetValue_when_accessingUnmodifiedIndex_then_returnsOriginalValue() {
			copyOnWriteList.appendValue(10);
			copyOnWriteList.appendValue(20);

			CopyOnWriteListSnapshot snapshot = copyOnWriteList.createSnapshot();

			assertEquals(10, snapshot.getValue(0));
			assertEquals(20, snapshot.getValue(1));
		}

		@Test
		@DisplayName("test_snapshotGetValue_when_parentUpdatesAfterSnapshot_then_returnsCopiedValue")
		void test_snapshotGetValue_when_parentUpdatesAfterSnapshot_then_returnsCopiedValue() {
			copyOnWriteList.appendValue(10);

			CopyOnWriteListSnapshot snapshot = copyOnWriteList.createSnapshot();

			copyOnWriteList.updateValue(0, 999);

			assertEquals(10, snapshot.getValue(0));
			assertEquals(999, copyOnWriteList.getValue(0));
		}

		@Test
		@DisplayName("test_snapshotGetCurrentVals_when_parentAppendsAfterSnapshot_then_snapshotDoesNotSeeNewValues")
		void test_snapshotGetCurrentVals_when_parentAppendsAfterSnapshot_then_snapshotDoesNotSeeNewValues() {
			copyOnWriteList.appendValue(1);
			copyOnWriteList.appendValue(2);

			CopyOnWriteListSnapshot snapshot = copyOnWriteList.createSnapshot();

			copyOnWriteList.appendValue(3);
			copyOnWriteList.appendValue(4);

			assertEquals(4, copyOnWriteList.getCurrentVals().size());
			assertEquals(2, snapshot.getCurrentVals().size());
			assertEquals(List.of(1, 2), snapshot.getCurrentVals());
		}

		@Test
		@DisplayName("test_snapshotGetCurrentVals_when_parentUpdatesMultipleIndices_then_snapshotRetainsAllOriginalValues")
		void test_snapshotGetCurrentVals_when_parentUpdatesMultipleIndices_then_snapshotRetainsAllOriginalValues() {
			copyOnWriteList.appendValue(1);
			copyOnWriteList.appendValue(2);
			copyOnWriteList.appendValue(3);
			copyOnWriteList.appendValue(4);

			CopyOnWriteListSnapshot snapshot = copyOnWriteList.createSnapshot();

			copyOnWriteList.updateValue(0, 10);
			copyOnWriteList.updateValue(2, 30);
			copyOnWriteList.updateValue(3, 40);

			assertEquals(List.of(10, 2, 30, 40), copyOnWriteList.getCurrentVals());
			assertEquals(List.of(1, 2, 3, 4), snapshot.getCurrentVals());
		}

		@Test
		@DisplayName("test_snapshotGetSnapshotId_when_created_then_returnsCorrectId")
		void test_snapshotGetSnapshotId_when_created_then_returnsCorrectId() {
			CopyOnWriteListSnapshot snapshot1 = copyOnWriteList.createSnapshot();
			CopyOnWriteListSnapshot snapshot2 = copyOnWriteList.createSnapshot();

			assertEquals("Snapshot-1", snapshot1.getSnapshotId());
			assertEquals("Snapshot-2", snapshot2.getSnapshotId());
		}
	}

	@Nested
	@DisplayName("Integration Tests")
	class IntegrationTests {

		@Test
		@DisplayName("test_integration_when_complexScenarioWithMultipleSnapshotsAndUpdates_then_eachSnapshotIsIsolated")
		void test_integration_when_complexScenarioWithMultipleSnapshotsAndUpdates_then_eachSnapshotIsIsolated() {
			// Initial state
			copyOnWriteList.appendValue(1);
			copyOnWriteList.appendValue(2);
			copyOnWriteList.appendValue(3);
			copyOnWriteList.appendValue(4);

			// Snapshot 1: [1, 2, 3, 4]
			CopyOnWriteListSnapshot snapshot1 = copyOnWriteList.createSnapshot();

			// Update
			copyOnWriteList.updateValue(1, 20);

			// Snapshot 2: [1, 20, 3, 4]
			CopyOnWriteListSnapshot snapshot2 = copyOnWriteList.createSnapshot();

			// More updates
			copyOnWriteList.updateValue(0, 10);
			copyOnWriteList.updateValue(3, 40);

			// Append new value
			copyOnWriteList.appendValue(5);

			// Snapshot 3: [10, 20, 3, 40, 5]
			CopyOnWriteListSnapshot snapshot3 = copyOnWriteList.createSnapshot();

			// Final update
			copyOnWriteList.updateValue(2, 30);

			// Verify current state: [10, 20, 30, 40, 5]
			assertEquals(List.of(10, 20, 30, 40, 5), copyOnWriteList.getCurrentVals());

			// Verify snapshot1: [1, 2, 3, 4]
			assertEquals(List.of(1, 2, 3, 4), snapshot1.getCurrentVals());

			// Verify snapshot2: [1, 20, 3, 4]
			assertEquals(List.of(1, 20, 3, 4), snapshot2.getCurrentVals());

			// Verify snapshot3: [10, 20, 3, 40, 5]
			assertEquals(List.of(10, 20, 3, 40, 5), snapshot3.getCurrentVals());
		}

		@Test
		@DisplayName("test_integration_when_multipleUpdatesToSameIndex_then_snapshotsOnlySaveFirstUpdate")
		void test_integration_when_multipleUpdatesToSameIndex_then_snapshotsOnlySaveFirstUpdate() {
			copyOnWriteList.appendValue(1);
			copyOnWriteList.appendValue(2);

			CopyOnWriteListSnapshot snapshot1 = copyOnWriteList.createSnapshot();

			// Multiple updates to same index
			copyOnWriteList.updateValue(0, 10);
			copyOnWriteList.updateValue(0, 100);
			copyOnWriteList.updateValue(0, 1000);

			CopyOnWriteListSnapshot snapshot2 = copyOnWriteList.createSnapshot();

			copyOnWriteList.updateValue(0, 10000);

			assertEquals(10000, copyOnWriteList.getValue(0));
			assertEquals(1, snapshot1.getValue(0));
			assertEquals(1000, snapshot2.getValue(0));
		}

		@Test
		@DisplayName("test_integration_when_noModificationsAfterSnapshot_then_snapshotSharesParentData")
		void test_integration_when_noModificationsAfterSnapshot_then_snapshotSharesParentData() {
			copyOnWriteList.appendValue(100);
			copyOnWriteList.appendValue(200);
			copyOnWriteList.appendValue(300);

			CopyOnWriteListSnapshot snapshot = copyOnWriteList.createSnapshot();

			// No modifications, snapshot should read from parent
			assertEquals(100, snapshot.getValue(0));
			assertEquals(200, snapshot.getValue(1));
			assertEquals(300, snapshot.getValue(2));
			assertEquals(List.of(100, 200, 300), snapshot.getCurrentVals());
		}

		@Test
		@DisplayName("test_integration_when_alternatingAppendsAndUpdates_then_behaviorIsCorrect")
		void test_integration_when_alternatingAppendsAndUpdates_then_behaviorIsCorrect() {
			copyOnWriteList.appendValue(1);

			CopyOnWriteListSnapshot snapshot1 = copyOnWriteList.createSnapshot();

			copyOnWriteList.appendValue(2);
			copyOnWriteList.updateValue(0, 10);

			CopyOnWriteListSnapshot snapshot2 = copyOnWriteList.createSnapshot();

			copyOnWriteList.appendValue(3);
			copyOnWriteList.updateValue(1, 20);

			assertEquals(List.of(10, 20, 3), copyOnWriteList.getCurrentVals());
			assertEquals(List.of(1), snapshot1.getCurrentVals());
			assertEquals(List.of(10, 2), snapshot2.getCurrentVals());
		}

		@Test
		@DisplayName("test_integration_when_largeNumberOfSnapshots_then_allSnapshotsAreIndependent")
		void test_integration_when_largeNumberOfSnapshots_then_allSnapshotsAreIndependent() {
			copyOnWriteList.appendValue(0);

			CopyOnWriteListSnapshot[] snapshots = new CopyOnWriteListSnapshot[10];

			for (int i = 0; i < 10; i++) {
				snapshots[i] = copyOnWriteList.createSnapshot();
				copyOnWriteList.updateValue(0, i + 1);
			}

			assertEquals(10, copyOnWriteList.getValue(0));

			for (int i = 0; i < 10; i++) {
				assertEquals(i, snapshots[i].getValue(0),
						"Snapshot " + i + " should have value " + i);
			}
		}
	}

	@Nested
	@DisplayName("Edge Cases")
	class EdgeCaseTests {

		@Test
		@DisplayName("test_edgeCase_when_updatingWithSameValue_then_updateStillCopiesValueToSnapshots")
		void test_edgeCase_when_updatingWithSameValue_then_updateStillCopiesValueToSnapshots() {
			copyOnWriteList.appendValue(10);

			CopyOnWriteListSnapshot snapshot = copyOnWriteList.createSnapshot();

			// Update with the same value
			copyOnWriteList.updateValue(0, 10);

			assertEquals(10, copyOnWriteList.getValue(0));
			assertEquals(10, snapshot.getValue(0));
		}

		@Test
		@DisplayName("test_edgeCase_when_appendingManyValues_then_allValuesAreStored")
		void test_edgeCase_when_appendingManyValues_then_allValuesAreStored() {
			for (int i = 0; i < 1000; i++) {
				copyOnWriteList.appendValue(i);
			}

			assertEquals(1000, copyOnWriteList.getCurrentVals().size());
			assertEquals(0, copyOnWriteList.getValue(0));
			assertEquals(999, copyOnWriteList.getValue(999));
		}

		@Test
		@DisplayName("test_edgeCase_when_snapshotAccessesIndexBeyondItsSize_then_throwsException")
		void test_edgeCase_when_snapshotAccessesIndexBeyondItsSize_then_throwsException() {
			copyOnWriteList.appendValue(1);

			CopyOnWriteListSnapshot snapshot = copyOnWriteList.createSnapshot();

			copyOnWriteList.appendValue(2);
			copyOnWriteList.appendValue(3);

			// Snapshot only has 1 element, accessing index 1 should throw exception
			assertThrows(IndexOutOfBoundsException.class, () -> {
				snapshot.getValue(1);
			});
		}

		@Test
		@DisplayName("test_edgeCase_when_updatingIndexZero_then_firstElementIsUpdated")
		void test_edgeCase_when_updatingIndexZero_then_firstElementIsUpdated() {
			copyOnWriteList.appendValue(5);
			copyOnWriteList.appendValue(10);

			copyOnWriteList.updateValue(0, 999);

			assertEquals(999, copyOnWriteList.getValue(0));
			assertEquals(10, copyOnWriteList.getValue(1));
		}

		@Test
		@DisplayName("test_edgeCase_when_creatingSnapshotAfterEveryOperation_then_allSnapshotsAreCorrect")
		void test_edgeCase_when_creatingSnapshotAfterEveryOperation_then_allSnapshotsAreCorrect() {
			copyOnWriteList.appendValue(1);
			CopyOnWriteListSnapshot s1 = copyOnWriteList.createSnapshot();

			copyOnWriteList.appendValue(2);
			CopyOnWriteListSnapshot s2 = copyOnWriteList.createSnapshot();

			copyOnWriteList.updateValue(0, 10);
			CopyOnWriteListSnapshot s3 = copyOnWriteList.createSnapshot();

			copyOnWriteList.appendValue(3);
			CopyOnWriteListSnapshot s4 = copyOnWriteList.createSnapshot();

			assertEquals(List.of(1), s1.getCurrentVals());
			assertEquals(List.of(1, 2), s2.getCurrentVals());
			assertEquals(List.of(10, 2), s3.getCurrentVals());
			assertEquals(List.of(10, 2, 3), s4.getCurrentVals());
		}
	}
}

