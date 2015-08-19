package com.fajarachmad.tutorial.composer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.fajarachmad.tutorial.domain.User;
import com.fajarachmad.tutorial.service.UserService;

@VariableResolver(DelegatingVariableResolver.class)
public class UserSearchComposer extends SelectorComposer<Window>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private UserService userServiceImpl;
	
	@Wire
	private Listbox lstResult;
	@Wire
	private Textbox txtUsername;
	
	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
	}
	
	@Listen("onClick = #btnSearch")
	public void search(){
		String username = txtUsername.getValue();
		User example = new User();
		example.setUsername(username);
		List<User> users = userServiceImpl.search(example);
		ListModelList<User> model = new ListModelList<User>(users);
		lstResult.setModel(model);
	}
	
	@Listen("onClick = #btnNew")
	public void addNew(){
		Executions.createComponents("user_detail.zul", null, null);
		getSelf().detach();
	}
	
	@Listen("onDetail = #userSearch")
	public void showDetail(ForwardEvent event){
		User data = (User) event.getData();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(User.class.getName(), data);
		Executions.createComponents("user_detail.zul", null, param);
		getSelf().detach();
	}
	
	@Listen("onDelete = #userSearch")
	public void delete(ForwardEvent event){
		User data = (User) event.getData();
		userServiceImpl.delete(data);
		search();
	}

}
