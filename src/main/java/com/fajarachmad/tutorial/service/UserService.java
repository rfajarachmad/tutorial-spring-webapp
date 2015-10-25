package com.fajarachmad.tutorial.service;

import java.util.List;

import com.fajarachmad.tutorial.domain.User;
import com.fajarachmad.tutorial.domain.UserExample;

public interface UserService {
	
	public void save(User user);
	
	public void delete(User user);
	
	public List<User> search(UserExample example);
}
