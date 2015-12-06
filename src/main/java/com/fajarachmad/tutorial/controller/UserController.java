package com.fajarachmad.tutorial.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fajarachmad.tutorial.domain.User;
import com.fajarachmad.tutorial.domain.UserExample;
import com.fajarachmad.tutorial.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userServiceImpl;
	
	@RequestMapping(value = {"", "/", "index"}, method = RequestMethod.GET)
	public String showUserPage() {
		return "zul/user/user_search.zul";
	}
	
	@RequestMapping(value = {"/{userId}"}, method = RequestMethod.GET)
	public String detail(@PathVariable Integer userId, ModelMap model, HttpSession session) {
		UserExample example = new UserExample();
		example.createCriteria();
		User user = userServiceImpl.findById(userId);
		model.addAttribute("selectedUser", user);
		return "zul/user/user_detail.zul";
	}
	
	@RequestMapping(value = {"new"}, method = RequestMethod.GET)
	public String showUserDetail() {
		return "zul/user/user_detail.zul";
	}
}
