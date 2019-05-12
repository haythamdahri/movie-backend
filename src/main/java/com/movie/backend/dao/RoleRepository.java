package com.movie.backend.dao;

import com.movie.backend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "/roles")
public interface RoleRepository extends JpaRepository<Role, Long> {
}
