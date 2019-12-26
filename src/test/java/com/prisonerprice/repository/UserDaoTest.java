package com.prisonerprice.repository;

import com.prisonerprice.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTest {

    private User user1;
    private User user2;
    private UserDaoImpl userDao;

    @Autowired
    private Logger logger;

    @Before
    public void init(){
        userDao = new UserDaoImpl(logger);
        user1 = new User("AlexPrice",
                "debug123",
                "dasjh90dkjahgu",
                "Alex",
                "Price",
                "abc@gwu.edu");
        user2 = new User("JasonZhang",
                "apk123",
                "dasdasdasdasdas",
                "Jason",
                "Zhang",
                "jasonzhang@hotmail.com");
        userDao.save(user1);
    }

    @After
    public void tearDown(){
        userDao.delete(userDao.getUserByEmail(user1.getEmail()));
        userDao.delete(userDao.getUserByEmail(user2.getEmail()));
    }

    @Test
    public void saveTest(){
        userDao.save(user2);
        int expectedNumberOfUsers = 2;
        Assert.assertEquals(expectedNumberOfUsers, userDao.getUserList().size());
    }

    @Test
    public void getUserByEmailTest(){
        User user = userDao.getUserByEmail(user1.getEmail());
        Assert.assertTrue(user1.getEmail().equals(user.getEmail()));
    }

    @Test
    public void getUserByCredentials(){
        User user = userDao.getUserByCredentials(user1.getEmail(), user1.getPassword());
        Assert.assertTrue(user1.getEmail().equals(user.getEmail()));
    }
}
