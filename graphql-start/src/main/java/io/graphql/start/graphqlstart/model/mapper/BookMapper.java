package io.graphql.start.graphqlstart.model.mapper;

import org.springframework.stereotype.Component;
import io.graphql.start.graphqlstart.domain.Book;
import io.graphql.start.graphqlstart.entity.BookEntity;

@Component
public class BookMapper {

  public BookEntity toBookEntity(Book book) {
    BookEntity bookEntity = new BookEntity();
    if (book == null) {
      return bookEntity;
    }
    bookEntity.setId(book.getId());
    bookEntity.setName(book.getName());
    bookEntity.setAuthorEntity(book.getAuthorEntity());
    return bookEntity;
  }
}
