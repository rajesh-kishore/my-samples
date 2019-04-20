/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package algoirthms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import algorithms.MergeKSortedArrayGenericsMethod;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public class MergeKSortedArrayGenericsMethodTestCase {

  /**
   * Tests the integer k sorted array
   */
  @Test
  public void testIntegerSorting() {
    final Integer array[][] =
    { { 5, 6, 8, 16 }, { 3, 7, 12, 13 }, { 1, 10, 11, 15 }, { 2, 4, 9, 14 } };

    int arrayResultExpected[] =
    { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
    List<Integer> expectedResult =
        Arrays.stream(arrayResultExpected).boxed().collect(Collectors.toList());
    List<Integer> result =
        MergeKSortedArrayGenericsMethod.<Integer> sort(array);
    Assert.assertEquals(true, expectedResult.containsAll(result));
  }

  /**
   * Tests the long k sorted array
   */
  @Test
  public void testLongSorting() {
    final Long array[][] =
    { { 5L, 6L, 8L, 16L }, { 3L, 7L, 12L, 13L }, { 1L, 10L, 11L, 15L },
        { 2L, 4L, 9L, 14L } };

    long arrayResultExpected[] =
    { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
    List<Long> expectedResult =
        Arrays.stream(arrayResultExpected).boxed().collect(Collectors.toList());
    List<Long> result = MergeKSortedArrayGenericsMethod.<Long> sort(array);
    Assert.assertEquals(true, expectedResult.containsAll(result));
  }

  /**
   * Tests the String k sorted array
   */
  @Test
  public void testStringSorting() {
    final String array[][] =
    { { "A", "D", "K" }, { "B", "M", "X", "Z" }, { "E", "F" },
        { "H" } };

    String arrayResultExpected[] =
    { "A", "B", "D", "E", "F", "H", "K", "M", "X", "Z" };
    List<String> expectedResult =
        Arrays.stream(arrayResultExpected).collect(Collectors.toList());
    List<String> result = MergeKSortedArrayGenericsMethod.<String> sort(array);
    Assert.assertEquals(true, expectedResult.containsAll(result));
  }

}
