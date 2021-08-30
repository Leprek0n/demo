package com.example.crud.service;

import com.example.crud.model.Role;
import com.example.crud.model.RoleName;
import com.example.crud.model.User;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public List<User> getCustomerList() {
        return userRepository.findAll();
    }


    public boolean save(User user, RoleName roleName) {
        User user1 = userRepository.findByUsername(user.getUsername());
        if(user1 != null) {
            return false;
        }
        user1.setRoles(Collections.singleton(new Role(1L, roleName.getRoleName())));
        user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user1);
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
