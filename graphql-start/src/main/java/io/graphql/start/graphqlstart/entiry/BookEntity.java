package io.graphql.start.graphqlstart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class BookEntity {

  @Id
  @GeneratedValue
  @Column(name = "book_id")
  private Long id;
  private String name;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "authors", nullable = false, insertable = false, updatable = false)
  private AuthorEntity authorEntity;

  public BookEntity(String name, AuthorEntity authorEntity) {
    super();
    this.name = name;
    this.authorEntity = authorEntity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AuthorEntity getAuthorEntity() {
    return authorEntity;
  }

  public void setAuthorEntity(AuthorEntity authorEntity) {
    this.authorEntity = authorEntity;
  }
}
