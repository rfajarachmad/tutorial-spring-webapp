package com.fajarachmad.tutorial.viewmodel;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.fajarachmad.tutorial.domain.User;
import com.fajarachmad.tutorial.domain.UserExample;
import com.fajarachmad.tutorial.service.UserService;

@VariableResolver(DelegatingVariableResolver.class)
public class UserViewModel {
	
	@WireVariable
	private UserService userServiceImpl;
	
	private Integer userId;
	private String userName;
	private String password;
	private boolean active;
	
	private User selectedUser;
	private String key;
	private List<User> userList;
	
	@Init
	public void init(@ExecutionParam("userList") List<User> userList) {
		
		if (selectedUser != null) {
			userId = selectedUser.getId();
			userName = selectedUser.getUserName();
			password = selectedUser.getPassword();
			active = selectedUser.isActive();
		}
		
		this.userList = userList;
	}
	
	@Command
    @NotifyChange("userList")
    public void search(){
		UserExample example = new UserExample();
		example.createCriteria();
		userList = userServiceImpl.search(example);
    }
	
	@Command
	public void detail(){ 
		Executions.sendRedirect("/user/"+selectedUser.getId());
	}
	
	@Command
	public void save() {
		
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setActive(active);
		
		userServiceImpl.save(user);
		
		Clients.showNotification("User is already saved", Clients.NOTIFICATION_TYPE_INFO, null, "top_center", 4100);
		
		Executions.sendRedirect("/user/");
		
	}
	
	@Command
    public void addNew(){
		Executions.sendRedirect("/user/new");
    }
	
	public List<User> getUserList() {
		return userList;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public UserService getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserService userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
	
	
	
}
