package io.graphql.start.graphqlstart.domain;

import io.graphql.start.graphqlstart.entity.AuthorEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {

  private Long id;
  private String name;
  private AuthorEntity authorEntity;
}
