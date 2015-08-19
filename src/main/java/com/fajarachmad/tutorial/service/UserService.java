package com.fajarachmad.tutorial.service;

import java.util.List;

import com.fajarachmad.tutorial.domain.User;

public interface UserService {
	
	public void save(User user);
	
	public void delete(User user);
	
	public List<User> search(User example);
	
	public User getUserByUsernama(String username);
	
}
