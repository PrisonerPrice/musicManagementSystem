package com.prisonerprice.repository;

import com.prisonerprice.model.Role;

public interface RoleDao {

    Role getRoleByName(String name);

}
