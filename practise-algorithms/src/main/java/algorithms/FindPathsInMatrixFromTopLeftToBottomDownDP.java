/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package algorithms;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The algorithm for finding different paths from top most left [0,0] to right
 * most bottom [m-1,n-1]. <code>
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
public final class FindPathsInMatrixFromTopLeftToBottomDownDP<T> {

  private void setCurrentPath(Set<String> paths[][], final int prevI,
      final int prevJ, final int currentI, final int currentJ,
      T currentVal) {
    paths[prevI][prevJ].forEach(e -> {
      final StringBuilder sb = new StringBuilder();
      sb.append(e + ":" + currentVal.toString());
      paths[currentI][currentJ].add(sb.toString());
    });
  }

  public Set<String> findPaths(final T[][] matrix, int m, int n) {
    final Set<String> paths[][] = new LinkedHashSet[m][n];
    paths[0][0] = new LinkedHashSet<>();
    paths[0][0].add(matrix[0][0].toString());

    for ( int i = 0; i < m; i++ ) {
      for ( int j = 0; j < n; j++ ) {
        if ( i == 0 && j == 0 ) {
          continue;
        }
        paths[i][j] = new LinkedHashSet<>();
        if ( i - 1 >= 0 ) {
          setCurrentPath(paths, i - 1, j, i, j, matrix[i][j]);
        }
        if ( j - 1 >= 0 ) {
          setCurrentPath(paths, i, j - 1, i, j, matrix[i][j]);
        }
      }
    }
    return paths[m - 1][n - 1];
  }

  /**
   * @param args
   */
  public static void main(String[] args) {

    FindPathsInMatrixFromTopLeftToBottomDownDP<String> obj =
        new FindPathsInMatrixFromTopLeftToBottomDownDP<String>();
    final String[][] matrix = {
        { "1", "2", "3" },
        { "4", "5", "6" }
    };

    // paths are [1:2:3:6, 1:2:5:6, 1:4:5:6]

    System.out.println(" paths are " + obj.findPaths(matrix, 2, 3));

  }

}
