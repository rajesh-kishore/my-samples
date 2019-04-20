/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package algorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * The class demonstrates how we can merge K Sorted arrays effectively. The
 * generic is implemented at method level.
 *
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class MergeKSortedArrayGenericsMethod {

  /**
   * @author Rajesh Kishore
   * @version 1.0
   * @since V1
   * @param <K> The type of data, intentionally took it differently than parent
   *          class to demo that both are different
   */
  private static class Pair<K extends Comparable<K>> implements
      Comparable<Pair<K>> {

    private final K data;

    private final int arrayIndex;

    /**
     * @param t
     * @param arrayIndex
     */
    public Pair(K t, int arrayIndex) {
      this.data = t;
      this.arrayIndex = arrayIndex;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Pair<K> that) {
      return this.data.compareTo(that.data);
    }

    K value() {
      return data;
    }

    private int arrayIndex() {
      return arrayIndex;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
      return "[" + value() + ", array " + arrayIndex() + "]";
    }
  }

  public static <T extends Comparable<T>> List<T> sort(final T array[][]) {

    PriorityQueue<Pair<T>> minHeap =
        new PriorityQueue<Pair<T>>();

    final LinkedList<T> result =
        new LinkedList<>();

    int pos[] = new int[array.length];
    Arrays.fill(pos, 0);

    for ( int i = 0; i < array.length; i++ ) {
      Pair<T> pair =
          new Pair<>(array[i][pos[i]], i);
      minHeap.add(pair);
    }

    // minHeap.iterator().forEachRemaining(System.out::println);

    while ( !minHeap.isEmpty() ) {
      Pair<T> pair = minHeap.poll();
      int currentArrayIndex = pair.arrayIndex();
      pos[currentArrayIndex]++;
      if ( pos[currentArrayIndex] < array[currentArrayIndex].length ) {
        Pair<T> pairNew =
            new Pair<>(
                array[currentArrayIndex][pos[currentArrayIndex]],
                currentArrayIndex);
        minHeap.add(pairNew);
      }
      result.add(pair.value());
    }

    return result;
  }

}
