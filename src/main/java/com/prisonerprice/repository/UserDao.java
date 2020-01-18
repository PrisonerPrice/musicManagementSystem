package com.prisonerprice.repository;

import com.prisonerprice.model.User;

public interface UserDao {

    boolean save(User user);
    User getUserById(String id);
    User getUserByEmail(String email);
    User getUserByCredentials(String email, String password);
}
