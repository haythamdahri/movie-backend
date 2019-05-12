package com.movie.backend.dao;

import com.movie.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(path="/users")
public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByEmail(@Param("email") String email);

    public List<User> findByUsername(@Param("username") String username);

}
