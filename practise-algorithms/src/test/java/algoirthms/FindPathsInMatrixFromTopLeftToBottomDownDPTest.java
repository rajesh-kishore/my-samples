/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package algoirthms;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import algorithms.FindPathsInMatrixFromTopLeftToBottomDownDP;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public class FindPathsInMatrixFromTopLeftToBottomDownDPTest {

  @Test
  public void testFindPathsOnStringMatrix() {
    final FindPathsInMatrixFromTopLeftToBottomDownDP<String> obj =
        new FindPathsInMatrixFromTopLeftToBottomDownDP<String>();
    String[][] matrix = { { "1", "2", "3" }, { "4", "5", "6" } };
    Set<String> expectedResult = new LinkedHashSet<>();
    expectedResult.add("1:2:3:6");
    expectedResult.add("1:2:5:6");
    expectedResult.add("1:4:5:6");

    Assert.assertEquals(true,
        obj.findPaths(matrix, 2, 3).containsAll(expectedResult));
  }

  /**
   * Test method for
   * {@link algorithms.FindPathsInMatrixFromTopLeftToBottomDownDP#findPaths(T[][], int, int)}
   * .
   */
  @Test
  public void testFindPathsOnIntegerMatrix() {
    final FindPathsInMatrixFromTopLeftToBottomDownDP<Integer> obj =
        new FindPathsInMatrixFromTopLeftToBottomDownDP<Integer>();
    Integer[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    List<String> expectedResult = new LinkedList<>();
    expectedResult.add("1:4:7:8:9");
    expectedResult.add("1:4:5:8:9");
    expectedResult.add("1:4:5:6:9");
    expectedResult.add("1:2:5:8:9");
    expectedResult.add("1:2:5:6:9");
    expectedResult.add("1:2:3:6:9");
    Assert.assertEquals(true,
        obj.findPaths(matrix, 3, 3).containsAll(expectedResult));
  }
}
