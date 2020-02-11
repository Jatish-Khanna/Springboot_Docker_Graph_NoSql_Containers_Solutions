package com.example.startegysort.sorting.service;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Jatish_Khanna
 *
 * @param <T>
 */

@Service
public interface SortingService<T> {
 
   List<T> sort(List<T> data);     
}
