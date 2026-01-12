package com.example.crud.service;

import com.example.crud.entity.User;
import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        log.debug("Creating user: {}", user);
        return repository.save(user);
    }

    public List<User> findAll() {
        log.debug("Finding all users");
        return repository.findAll();
    }

    public User findById(Long id) {
        log.debug("Finding user by id: {}", id);
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    public User update(Long id, User user) {
        log.debug("Updating user with id: {}", id);
        User existing = findById(id);
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        return repository.save(existing);
    }

    public void delete(Long id) {
        log.debug("Deleting user with id: {}", id);
        repository.deleteById(id);
    }
}
