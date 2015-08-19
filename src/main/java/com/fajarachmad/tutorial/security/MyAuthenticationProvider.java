package com.fajarachmad.tutorial.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fajarachmad.tutorial.domain.User;
import com.fajarachmad.tutorial.service.UserService;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UserService userServiceImpl;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
	    String password = authentication.getCredentials().toString();
		
	    User user = userServiceImpl.getUserByUsernama(name);
	    
	    //Inplement the authentication here
	    if(user != null){
	    	if(user.getPassword().equals(password)){
	    		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
	    		return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
	    	}else{
	    		throw new BadCredentialsException("Invalid password");
	    	}
	    }else
	    	throw new BadCredentialsException("User is not registered");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
