package com.example.crud.service;

import com.example.crud.model.RoleName;
import com.example.crud.model.User;

import java.util.List;

public interface UserService {
    public List<User> getCustomerList();

    public boolean save(User user, RoleName roleName);

    public User showById(Long id);

    public void update(User user, Long id);

    public void delete(Long id);
}
