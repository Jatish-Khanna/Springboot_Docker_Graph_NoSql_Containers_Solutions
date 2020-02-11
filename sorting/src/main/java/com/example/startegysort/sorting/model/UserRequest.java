package com.example.startegysort.sorting.model;

import java.util.List;
import org.springframework.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {

  @NonNull
  private List<Integer> numbersList;
}
