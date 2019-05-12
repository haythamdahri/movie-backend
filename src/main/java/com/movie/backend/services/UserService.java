package com.movie.backend.services;

import com.movie.backend.entities.User;

import java.util.Collection;

public interface UserService {

    public User addUser(User user);
    public User getUser(Long id);
    public User getUser(String email);
    public boolean deleteUser(Long id);
    public Collection<User> getUsers();

}
