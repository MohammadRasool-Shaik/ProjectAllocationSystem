package org.rash.projectallocationsystem.controller;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author rasool.shaik
 * 
 */
public class UserDetails extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	
	private Collection<? extends GrantedAuthority> roles;
	

	public UserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, String userId) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userId = userId;
	}


	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


	/**
	 * @return the roles
	 */
	public Collection<? extends GrantedAuthority> getRoles() {
		return roles;
	}


	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Collection<? extends GrantedAuthority> roles) {
		this.roles = roles;
	}

}
