package com.Lazaro.Lazaro_servicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Lazaro.Lazaro_servicios.dao.UserDao;
import com.Lazaro.Lazaro_servicios.entity.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDAO;
	
	@Override
	public List<User> findAll() {
		List<User> listUsers= userDAO.findAll();
		return listUsers;
	}

	@Override
	public User findById(int id) {
		User user = userDAO.findById(id);
		return user;
	}

	@Override
	public void save(User user) {
		userDAO.save(user);

	}

	@Override
	public void deleteById(int id) {
		userDAO.deleteById(id);
	}

}