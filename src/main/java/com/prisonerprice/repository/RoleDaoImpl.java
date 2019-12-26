package com.prisonerprice.repository;

import com.prisonerprice.model.Role;

public class RoleDaoImpl implements RoleDao{

    private Connection<Role> roleConnection = new Connection<>();

    @Override
    public Role getRoleByName(String name) {
        String hql = "FROM Role as r where lower(r.name) = :name";
        return roleConnection.getObjectByName(hql, name);
    }
}
