package com.example.startegysort.sorting.utils;

public interface MergeSort {

  public static void mergeSort(Integer[] dataArray, int start, int end) {

    int mid;
    if (start < end) {
      mid = start + ((end - start) / 2);
      mergeSort(dataArray, start, mid);
      mergeSort(dataArray, mid + 1, end);
      merge(dataArray, start, mid, end);
    }
  }

  public static void merge(Integer[] dataArray, int start, int mid, int end) {

    int[] temp = new int[end - start + 1];
    int firstIndex = start;
    int secondIndex = mid + 1;
    int storeIndex = 0;

    while (firstIndex <= mid && secondIndex <= end) {
      if (dataArray[firstIndex] <= dataArray[secondIndex]) {
        temp[storeIndex] = dataArray[firstIndex];
        firstIndex++;
      } else {
        temp[storeIndex] = dataArray[secondIndex];
        secondIndex++;
      }
      storeIndex++;
    }
    while (firstIndex <= mid) {
      temp[storeIndex] = dataArray[firstIndex];
      firstIndex++;
      storeIndex++;
    }

    while (secondIndex <= end) {
      temp[storeIndex] = dataArray[secondIndex];
      secondIndex++;
      storeIndex++;
    }

    for (int index = 0; index <= end - start; index++) {
      dataArray[start + index] = temp[index];
    }
  }

}
