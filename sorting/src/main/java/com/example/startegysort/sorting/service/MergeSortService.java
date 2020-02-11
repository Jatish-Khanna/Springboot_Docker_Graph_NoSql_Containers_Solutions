package com.example.startegysort.sorting.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.example.startegysort.sorting.utils.MergeSort;

/**
 * 
 * @author Jatish_Khanna
 *
 */

@Component("mergeSort")
public class MergeSortService implements SortingService<Integer> {

  @Override
  public List<Integer> sort(List<Integer> data) {
    Integer[] dataArray = data.toArray(new Integer[data.size()]);
    MergeSort.mergeSort(dataArray, 0, dataArray.length - 1);
    List<Integer> sortedList =
        Arrays.stream(dataArray)
            .collect(Collectors.toList());
    return sortedList;
  }

}
