package com.fajarachmad.tutorial.service.impl;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fajarachmad.tutorial.domain.User;
import com.fajarachmad.tutorial.mapper.UserMapper;
import com.fajarachmad.tutorial.service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	final static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void save(User user) {
		if(user.getId() == 0){
			Random random = new Random();
			user.setId(random.nextInt());
			userMapper.insert(user);
		}else{
			userMapper.update(user);
		}
		
	}

	@Override
	public void delete(User user) {
		userMapper.deleteByPrimaryKey(user.getId());
	}

	@Override
	public List<User> search(User example) {
		return userMapper.findByExample(example);
	}

	@Override
	public User getUserByUsernama(String username) {
		logger.debug("Getting user by username: "+ username);
		return userMapper.findByUsername(username);
	}

}
