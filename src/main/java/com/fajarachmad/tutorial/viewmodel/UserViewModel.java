package com.fajarachmad.tutorial.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.fajarachmad.tutorial.domain.User;
import com.fajarachmad.tutorial.domain.UserExample;
import com.fajarachmad.tutorial.service.UserService;

@VariableResolver(DelegatingVariableResolver.class)
public class UserViewModel {
	
	@WireVariable
	private UserService userServiceImpl;
	
	private String txtUserName;
	private List<User> userList;
	
	public void init(@ExecutionParam("userList") List<User> userList) {
		this.userList = userList;
	}
	
	@Command
    @NotifyChange("userList")
    public void search(){
		UserExample example = new UserExample();
		example.createCriteria();
		userList = userServiceImpl.search(example);
    }
	
	public List<User> getUserList() {
		return userList;
	}

	public String getTxtUserName() {
		return txtUserName;
	}

	public void setTxtUserName(String txtUserName) {
		this.txtUserName = txtUserName;
	}
	
}
