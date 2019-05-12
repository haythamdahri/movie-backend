package com.movie.backend.services;

import com.movie.backend.dao.RoleRepository;
import com.movie.backend.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role addRole(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public Role getRole(Long id) {
        Optional<Role> roleOptional = this.roleRepository.findById(id);
        if( roleOptional.isPresent() ) {
            return roleOptional.get();
        }
        return null;
    }

    @Override
    public boolean deleteRole(Long id) {
        this.roleRepository.delete(this.getRole(id));
        return true;
    }

    @Override
    public Collection<Role> getRoles() {
        return this.roleRepository.findAll();
    }
}
