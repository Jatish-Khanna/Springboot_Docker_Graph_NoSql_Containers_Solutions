package io.graphql.start.graphqlstart.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.graphql.start.graphqlstart.entity.BookEntity;
import io.graphql.start.graphqlstart.repository.BookRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {

  BookRepository bookRepository;

  public List<BookEntity> getAllBooks() {
    return bookRepository.findAll();
  }

  public Optional<BookEntity> getBookById(Long id) {
    return bookRepository.findById(id);
  }

  public BookEntity saveAuthor(BookEntity bookEntity) {
    return bookRepository.save(bookEntity);
  }

  public void deleteBook(Long id) {
    bookRepository.deleteById(id);
  }

}
