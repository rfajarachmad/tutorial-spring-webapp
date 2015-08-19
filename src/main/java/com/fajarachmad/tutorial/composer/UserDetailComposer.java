package com.fajarachmad.tutorial.composer;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.fajarachmad.tutorial.domain.User;
import com.fajarachmad.tutorial.service.UserService;

@VariableResolver(DelegatingVariableResolver.class)
public class UserDetailComposer extends SelectorComposer<Window> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private UserService userServiceImpl;
	@WireVariable("arg")
	Map<String, Object> arg;
	@Wire
	private Textbox txtUsername;
	@Wire
	private Textbox txtPassword;
	@Wire
	private Checkbox chkActive;
	
	private User user;
	
	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		if(arg.containsKey(User.class.getName())){
			user = (User) arg.get(User.class.getName());
			txtUsername.setValue(user.getUsername());
			txtPassword.setValue(user.getPassword());
			chkActive.setChecked(user.isActive());
		}
	}
	
	@Listen("onClick = #btnSave")
	public void save(){
		if(user == null)
			user = new User();
		
		user.setUsername(txtUsername.getValue());
		user.setPassword(txtPassword.getValue());
		user.setActive(chkActive.isChecked());
		
		userServiceImpl.save(user);
		
		Executions.createComponents("user_search.zul", null, null);
		getSelf().detach();
	}
	
	@Listen("onClick = #btnBack")
	public void back(){
		Executions.createComponents("user_search.zul", null, null);
		getSelf().detach();
	}
	
}
