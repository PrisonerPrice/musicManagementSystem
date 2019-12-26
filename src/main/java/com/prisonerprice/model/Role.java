package com.prisonerprice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "allowed_resource")
    private String allowedResource;

    @Column(name = "allowed_read")
    private boolean isAllowedRead;

    @Column(name = "allowed_create")
    private boolean isAllowedCreate;

    @Column(name = "allowed_update")
    private boolean isAllowedUpdate;

    @Column(name = "allowed_delete")
    private boolean isAllowedDelete;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAllowedResource() {
        return allowedResource;
    }

    public void setAllowedResource(String allowedResource) {
        this.allowedResource = allowedResource;
    }

    public boolean isAllowedRead() {
        return isAllowedRead;
    }

    public void setAllowedRead(boolean allowedRead) {
        isAllowedRead = allowedRead;
    }

    public boolean isAllowedCreate() {
        return isAllowedCreate;
    }

    public void setAllowedCreate(boolean allowedCreate) {
        isAllowedCreate = allowedCreate;
    }

    public boolean isAllowedUpdate() {
        return isAllowedUpdate;
    }

    public void setAllowedUpdate(boolean allowedUpdate) {
        isAllowedUpdate = allowedUpdate;
    }

    public boolean isAllowedDelete() {
        return isAllowedDelete;
    }

    public void setAllowedDelete(boolean allowedDelete) {
        isAllowedDelete = allowedDelete;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                isAllowedRead == role.isAllowedRead &&
                isAllowedCreate == role.isAllowedCreate &&
                isAllowedUpdate == role.isAllowedUpdate &&
                isAllowedDelete == role.isAllowedDelete &&
                Objects.equals(name, role.name) &&
                Objects.equals(allowedResource, role.allowedResource) &&
                Objects.equals(users, role.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, allowedResource, isAllowedRead, isAllowedCreate, isAllowedUpdate, isAllowedDelete, users);
    }
}
