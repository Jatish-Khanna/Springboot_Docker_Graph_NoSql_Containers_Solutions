package io.graphql.start.graphqlstart.web.rest;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import io.graphql.start.graphqlstart.domain.Book;
import io.graphql.start.graphqlstart.entity.AuthorEntity;
import io.graphql.start.graphqlstart.entity.BookEntity;
import io.graphql.start.graphqlstart.model.mapper.BookMapper;
import io.graphql.start.graphqlstart.service.AuthorService;

@RestController
public class Mutation implements GraphQLMutationResolver {

  @Autowired
  private AuthorService authorService;
//  @Autowired
//  private AuthorMapper authorMapper;
  @Autowired
  private BookMapper bookMapper;

  public AuthorEntity saveAuthor(String name, Integer age, Set<Book> books) {
    AuthorEntity author = new AuthorEntity();
    author.setName(name);
    author.setAge(age);

    Set<BookEntity> bookEntities = new HashSet<>();
    BookEntity bookEntity;
    for (Book book : books) {
      bookEntity = bookMapper.toBookEntity(book);
      bookEntities.add(bookEntity);
    }
    author.setBooks(bookEntities);
    
//    return authorMapper.toAuthor(authorService.saveAuthor(author));
    return authorService.saveAuthor(author);
  }

}
