/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package algoirthms;

import java.util.LinkedList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import algorithms.FindPathsInMatrixFromTopLeftToBottomDown;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public class FindPathsInMatrixFromTopLeftToBottomDownTest {

  /**
   * @throws java.lang.Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  /**
   * Test method for
   * {@link algorithms.FindPathsInMatrixFromTopLeftToBottomDown#findPaths(T[][], int, int)}
   * .
   */
  @Test
  public void testFindPathsOnStringMatrix() {
    final FindPathsInMatrixFromTopLeftToBottomDown<String> obj =
        new FindPathsInMatrixFromTopLeftToBottomDown<String>();
    String[][] matrix = { { "1", "2", "3" }, { "4", "5", "6" } };
    List<String> expectedResult = new LinkedList<>();
    expectedResult.add("{1,4,5,6}");
    expectedResult.add("{1,2,5,6}");
    expectedResult.add("{1,2,3,6}");
    Assert.assertEquals(true,
        expectedResult.containsAll(obj.findPaths(matrix, 2, 3)));
  }

  /**
   * Test method for
   * {@link algorithms.FindPathsInMatrixFromTopLeftToBottomDown#findPaths(T[][], int, int)}
   * .
   */
  @Test
  public void testFindPathsOnIntegerMatrix() {
    final FindPathsInMatrixFromTopLeftToBottomDown<Integer> obj =
        new FindPathsInMatrixFromTopLeftToBottomDown<Integer>();
    Integer[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    List<String> expectedResult = new LinkedList<>();
    expectedResult.add("{1,4,7,8,9}");
    expectedResult.add("{1,4,5,8,9}");
    expectedResult.add("{1,4,5,6,9}");
    expectedResult.add("{1,2,5,8,9}");
    expectedResult.add("{1,2,5,6,9}");
    expectedResult.add("{1,2,3,6,9}");
    Assert.assertEquals(true,
        expectedResult.containsAll(obj.findPaths(matrix, 3, 3)));
  }

}
