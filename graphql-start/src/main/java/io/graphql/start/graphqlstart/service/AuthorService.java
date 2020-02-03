package io.graphql.start.graphqlstart.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.graphql.start.graphqlstart.entity.AuthorEntity;
import io.graphql.start.graphqlstart.repository.AuthorRepository;
import lombok.AllArgsConstructor;

/**
 * 
 * @author Jatish_Khanna
 *
 */

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorService {

  AuthorRepository authorRepository;

  public List<AuthorEntity> getAllAuthors() {
    return authorRepository.findAll();
  }

  public Optional<AuthorEntity> getAuthorById(Long id) {
    return authorRepository.findById(id);
  }

  public AuthorEntity saveAuthor(AuthorEntity author) {
    return authorRepository.save(author);
  }

  public void deleteAuthor(Long id) {
    authorRepository.deleteById(id);
  }

}
