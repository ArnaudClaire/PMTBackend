package com.mooc.formulaone.services.impl;

import com.mooc.formulaone.dao.UserRepository;
import com.mooc.formulaone.exceptions.EntityDontExistException;
import com.mooc.formulaone.models.User;
import com.mooc.formulaone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new EntityDontExistException();
    }

    @Override
    public Long create(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
