package com.prisonerprice.repository;

import com.prisonerprice.model.User;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    private Logger logger;
    private Connection<User> userConnection = new Connection<>();

    @Autowired
    public UserDaoImpl(Logger logger){
        this.logger = logger;
    }

    @Override
    public boolean save(User user) {
        return userConnection.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        String hql = "FROM User as u where lower(u.email) = :param";
        return userConnection.getObjectByName(hql, email);
    }

    @Override
    public User getUserByCredentials(String email, String password) {
        String hql = "FROM User as u where lower(u.email) = :param1 and u.password = :param2";
        return userConnection.getObjectByName(hql, email, password);
    }

    public boolean delete(User user){
        return userConnection.delete(user);
    }

    public List<User> getUserList(){
        return userConnection.getObjectList("User");
    }
}
