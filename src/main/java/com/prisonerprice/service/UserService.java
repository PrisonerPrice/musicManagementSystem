package com.prisonerprice.service;

import com.prisonerprice.model.User;
import com.prisonerprice.repository.UserDaoImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;



@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserService {

    private Logger logger;
    private UserDaoImpl userDao;

    @Autowired
    public UserService(Logger logger, UserDaoImpl userDao) {
        this.logger = logger;
        this.userDao = userDao;
    }

    public boolean save(User user){
        return userDao.save(user);
    }

    public User getUserByEmail(String email){
        return userDao.getUserByEmail(email);
    }

    public User getUserByCredentials(String email, String password){
        return userDao.getUserByCredentials(email, password);
    }
}
