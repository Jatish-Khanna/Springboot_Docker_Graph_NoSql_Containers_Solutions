package com.example.startegysort.sorting.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.example.startegysort.sorting.utils.QuickSort;

/**
 * 
 * @author Jatish_Khanna
 *
 */

@Component("quickSort")
public class  QuickSortService implements SortingService<Integer>, QuickSort {

  @Override
  public List<Integer> sort(List<Integer> data) {
    List<Integer> sortedList = new ArrayList<>(data);
    
    QuickSort.quickSort(sortedList, 0, sortedList.size() - 1);
    return sortedList;
  }

}
