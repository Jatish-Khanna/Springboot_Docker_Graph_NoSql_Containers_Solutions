package com.example.startegysort.sorting.web.rest;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.startegysort.sorting.model.UserRequest;
import com.example.startegysort.sorting.service.SortingService;

/**
 * Request controller to sort provided list of numbers
 * 
 * @author Jatish_Khanna
 *
 */

@RestController
@RequestMapping("/sort")
public class SortingResource extends BaseResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(SortingResource.class);

  // Instead of creating an instance, the below snippet can be replaced with Map<String, SortingService>
  @Resource(name = "quickSort")
  SortingService<Integer> quickSort;
  @Resource(name = "mergeSort")
  SortingService<Integer> mergeSort;

  public SortingResource(SortingService<Integer> quickSort, SortingService<Integer> mergeSort) {
    super();
    this.quickSort = quickSort;
    this.mergeSort = mergeSort;
  }

  /**
   * sort the elements received in the request body
   * 
   * @param numbersList list of numbers received
   * @return {@link ResponseEntity<?>} sorted list of numbers
   */
  @PostMapping("/data")
  public ResponseEntity<?> sortElement(@RequestBody UserRequest userRequest) {
    List<Integer> numbersList = userRequest.getNumbersList();

    if (numbersList.isEmpty()) {
      return toResponse(numbersList);
    }
    
    List<Integer> sortedList = null;

    if (numbersList.size() < 10) {
      LOGGER.info("sorting using quick sort");
      sortedList = quickSort.sort(numbersList);
    } else {
      LOGGER.info("sorting using merge sort");
      sortedList = mergeSort.sort(numbersList);
    }
    LOGGER.debug("sorted list: {}", numbersList);
    return toResponse(sortedList);
  }

}
