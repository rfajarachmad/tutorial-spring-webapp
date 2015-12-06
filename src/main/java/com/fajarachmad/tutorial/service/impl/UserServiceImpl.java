package com.fajarachmad.tutorial.service.impl;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fajarachmad.tutorial.dao.UserMapper;
import com.fajarachmad.tutorial.domain.User;
import com.fajarachmad.tutorial.domain.UserExample;
import com.fajarachmad.tutorial.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	final static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	@Transactional
	public void save(User user) {
		if(user.getId() == null){
			Random random = new Random();
			user.setId(random.nextInt());
			userMapper.insert(user);
		}else{
			userMapper.updateByPrimaryKey(user);
		}
		
	}

	@Override
	@Transactional
	public void delete(User user) {
		userMapper.deleteByPrimaryKey(user.getId());
	}

	@Override
	@Transactional(readOnly=true)
	public List<User> search(UserExample example) {
		logger.debug("Getting user by example");
		return userMapper.selectByExample(example);
	}
	
	@Override
	@Transactional(readOnly=true)
	public User findById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

}
