package io.graphql.start.graphqlstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.graphql.start.graphqlstart.entity.BookEntity;

/**
 * 
 * @author Jatish_Khanna
 *
 */

@Repository
public interface  BookRepository extends JpaRepository<BookEntity, Long>{
  
}
