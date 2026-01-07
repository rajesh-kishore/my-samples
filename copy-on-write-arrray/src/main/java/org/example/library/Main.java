package org.example.library;

public class Main {
	public static void main(String[] args) {
		CopyOnWriteList list = new CopyOnWriteList();

		// Add initial values
		list.appendValue(1);
		list.appendValue(2);

		System.out.println("Initial list: " + list.getCurrentVals());

        // Create a snapshot
        CopyOnWriteListSnapshot snapshot1 = list.createSnapshot();
        System.out.println("Snapshot1 created: " + snapshot1.getCurrentVals());

        // Append more values to parent
        list.appendValue(3);
        list.appendValue(4);

        System.out.println("\nAfter appending 3 and 4:");
        System.out.println("Parent list: " + list.getCurrentVals());
        System.out.println("Snapshot1 (should still be [1, 2]): " + snapshot1.getCurrentVals());

        // Create another snapshot
        CopyOnWriteListSnapshot snapshot2 = list.createSnapshot();
		System.out.println("\nSnapshot2 created: " + snapshot2.getCurrentVals());

		// Update value in parent
		list.updateValue(0, 100);

		System.out.println("\nAfter updating index 0 to 100:");
		System.out.println("Parent list: " + list.getCurrentVals());
		System.out.println("Snapshot1 (should be [1, 2]): " + snapshot1.getCurrentVals());
		System.out.println("Snapshot2 (should be [1, 2, 3, 4]): " + snapshot2.getCurrentVals());


	}
}

