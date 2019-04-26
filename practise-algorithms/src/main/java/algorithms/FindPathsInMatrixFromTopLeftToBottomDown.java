/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package algorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The algorithm for finding different paths from top left to right most bottom.
 * <code>
 * Input : 1 2 3
           4 5 6
  Output : 1 4 5 6
           1 2 5 6
           1 2 3 6
   </code>
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */

public final class FindPathsInMatrixFromTopLeftToBottomDown<T> {

  public List<String> findPaths(T matrix[][], int m, int n) {

    List<String> paths = new LinkedList<>();
    backTrack(matrix, m, n, 0, 0, paths, new String[(m + n - 1)], 0);
    return paths;
  }

  private void backTrack(T matrix[][], int m, int n, int currentI,
      int currentJ, List<String> paths, String[] individualPath,
      int index) {

    individualPath[index] = "" + matrix[currentI][currentJ];

    if ( currentI == m - 1 ) {
      for ( int k = currentJ + 1; k < n; k++ ) {
        individualPath[index + k - currentJ] = ""
            + matrix[currentI][k];
      }
      /*
       * System.out.println(Arrays.asList(individualPath));
       * System.out.println(Arrays.stream(individualPath).collect(
       * Collectors.joining(",")));
       */
      paths.add("{"
          + Arrays.stream(individualPath).collect(Collectors.joining(","))
          + "}");
      return;
    }

    if ( currentJ == n - 1 ) {
      for ( int k = currentI + 1; k < m; k++ ) {
        individualPath[index + k - currentI] = ""
            + matrix[k][currentJ];
      }
      /*
       * System.out.println(Arrays.asList(individualPath));
       * System.out.println(Arrays.stream(individualPath).collect(
       * Collectors.joining(",")));
       */
      paths.add("{"
          + Arrays.stream(individualPath).collect(Collectors.joining(","))
          + "}");
      return;
    }

    backTrack(matrix, m, n, currentI + 1,
        currentJ, paths, individualPath, index + 1);

    backTrack(matrix, m, n, currentI,
        currentJ + 1, paths, individualPath, index + 1);
  }

  /**
   * @param args
   */
  public static void main(String[] args) {

    FindPathsInMatrixFromTopLeftToBottomDown<String> obj =
        new FindPathsInMatrixFromTopLeftToBottomDown<String>();
    String[][] matrix = { { "1", "2", "3" }, { "4", "5", "6" } };

    System.out.println("paths " + obj.findPaths(matrix, 2, 3));

    FindPathsInMatrixFromTopLeftToBottomDown<Integer> obj2 =
        new FindPathsInMatrixFromTopLeftToBottomDown<Integer>();
    Integer[][] matrix1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

    // paths [{1,4,7,8,9}, {1,4,5,8,9}, {1,4,5,6,9}, {1,2,5,8,9}, {1,2,5,6,9},
    // {1,2,3,6,9}]
    System.out.println("paths " + obj2.findPaths(matrix1, 3, 3));

  }

}
