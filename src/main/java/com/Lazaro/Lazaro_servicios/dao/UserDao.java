package com.Lazaro.Lazaro_servicios.dao;

import java.util.List;

import com.Lazaro.Lazaro_servicios.entity.User;



/**
 * UserDao
 */
public interface UserDao {

    public List<User> findAll();
	
	public User findById(int id);
	
	public void save(User user);
	
	public void deleteById(int id);
}