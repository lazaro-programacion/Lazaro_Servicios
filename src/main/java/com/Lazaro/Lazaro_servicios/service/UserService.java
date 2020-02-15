package com.Lazaro.Lazaro_servicios.service;


import java.util.List;

import com.Lazaro.Lazaro_servicios.entity.User;

public interface UserService {
	
	public List<User> findAll();
	
	public User findById(int id);
	
	public void save(User user);
	
	public void deleteById(int id);
}