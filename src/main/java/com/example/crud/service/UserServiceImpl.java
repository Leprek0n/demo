package com.example.crud.service;

import com.example.crud.model.Role;
import com.example.crud.model.User;
import com.example.crud.repository.RoleRepository;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository repository;


    public List<User> getCustomerList() {
        return userRepository.findAll();
    }


    public boolean save(User user) {

//            user1.setUsername(user.getUsername());
//            user1.setEmail(user.getEmail());
//            user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//            Role roleUser = repository.findByName(user.getUsername());
//            user1.addRole(roleUser);
            userRepository.save(user);
            return true;


    }


    public User showById(Long id) {
        return userRepository.getById(id);
    }


    public void update(User user, Long id) {
        User user1 = userRepository.findByUsername(user.getUsername());

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
    public List<Role> getRoles() {
        return repository.findAll();
    }
    public User get(Long id) {
        return userRepository.getById(id);
    }
    public Optional<User> getOne(Long id) {
        return userRepository.findById(id);
    }
}
