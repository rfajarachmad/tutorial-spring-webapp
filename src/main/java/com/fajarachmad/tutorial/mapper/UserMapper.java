package com.fajarachmad.tutorial.mapper;

import java.util.List;

import com.fajarachmad.tutorial.domain.User;

public interface UserMapper {
	
	public void insert(User user);
	
	public void deleteByPrimaryKey(int id);
	
	public List<User> findByExample(User example);
	
	public void update(User user);
	
	public User findByUsername(String username);
}
