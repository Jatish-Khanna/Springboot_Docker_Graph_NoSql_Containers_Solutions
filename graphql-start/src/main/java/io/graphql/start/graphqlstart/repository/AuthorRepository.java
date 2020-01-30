package io.graphql.start.graphqlstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.graphql.start.graphqlstart.entity.AuthorEntity;

/**
 * 
 * @author Jatish_Khanna
 *
 */

@Repository
public interface  AuthorRepository extends JpaRepository<AuthorEntity, Long>{
  
}
