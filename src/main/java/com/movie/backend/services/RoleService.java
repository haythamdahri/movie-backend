package com.movie.backend.services;

import com.movie.backend.entities.Role;

import java.util.Collection;

public interface RoleService {

    public Role addRole(Role role);
    public Role getRole(Long id);
    public boolean deleteRole(Long id);
    public Collection<Role> getRoles();

}
