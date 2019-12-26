package com.prisonerprice.service;

import com.prisonerprice.model.Role;
import com.prisonerprice.repository.RoleDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

public class RoleService {

    private Logger logger;
    private RoleDaoImpl roleDao;

    @Autowired
    public RoleService(Logger logger, RoleDaoImpl roleDao){
        this.logger = logger;
        this.roleDao = roleDao;
    }

    public Role getRoleByName(String name){
        return roleDao.getRoleByName(name);
    }
}
