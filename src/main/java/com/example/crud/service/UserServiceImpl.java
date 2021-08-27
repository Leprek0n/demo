package com.example.crud.service;

import com.example.crud.model.User;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getCustomerList() {
        return userRepository.findAll();
    }


    public boolean save(User user) {
        userRepository.save(user);
        return true;
    }


    public User showById(Long id) {
        return userRepository.getById(id);
    }


    public void update(User user, Long id) {
        userRepository.save(user);
    }


    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    public User getUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByUsername(name);
    }
}
