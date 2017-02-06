/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytreefx.model;

/**
 *
 * @author Developer
 */
public interface UserRole {
    public User findUser(String username, String password);
    public User storeUser(User user);
    public String[] getRoles();
    public boolean storeRole(String role);
}
