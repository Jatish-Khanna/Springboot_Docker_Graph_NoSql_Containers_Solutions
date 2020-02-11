package com.example.startegysort.sorting.utils;

import java.util.Collections;
import java.util.List;

public interface QuickSort {

  public static void quickSort(List<Integer> list, int left, int right) {
    int pivot;
    if (left < right) {
      // find pivot element
      pivot = partition(list, left, right);
      // recurse
      quickSort(list, left, pivot - 1);
      quickSort(list, pivot + 1, right);
    }
  }

  static int partition(List<Integer> list, int left, int right) {
    int pivotElement = list.get(left);
    int start = left;
    int end = right + 1;
    while (start < end) {

      while (list.get(++start) < pivotElement) {
        if (start >= right) {
          break;
        }
      }

      while (list.get(--end) > pivotElement) {
        if (end <= left) {
          break;
        }
      }

      if (start >= end) {
        break;
      } else {
        // swap
        Collections.swap(list, start, end);
      }
    }
    if (end == left)
      return end;
    // swap pivot element
    Collections.swap(list, left, end);
    return end;
  }
}
