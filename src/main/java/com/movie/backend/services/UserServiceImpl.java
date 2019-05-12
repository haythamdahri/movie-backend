package com.movie.backend.services;

import com.movie.backend.dao.UserRepository;
import com.movie.backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if( optionalUser.isPresent() ){
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public User getUser(String email) {
        return this.userRepository.findUserByEmail(email);
    }

    @Override
    public boolean deleteUser(Long id) {
        this.userRepository.delete(this.getUser(id));
        return true;
    }

    @Override
    public Collection<User> getUsers() {
        return this.userRepository.findAll();
    }
}
