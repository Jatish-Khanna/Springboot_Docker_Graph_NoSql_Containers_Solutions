package io.graphql.start.graphqlstart.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Jatish_Khanna
 *
 */

@Data
@NoArgsConstructor
@Entity
public class AuthorEntity {

  @Id
  @GeneratedValue
  @Column(name = "author_id")
  private Long id;
  private String name;
  private Integer age;
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name="authors", nullable=false  )
  private Set<BookEntity> books;
  
}
