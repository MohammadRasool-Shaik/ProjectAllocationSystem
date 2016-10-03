package com.happiestminds.projectallocationsystem.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {

	protected UserDetails getCurrentUser() {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		return (UserDetails) a.getPrincipal();
	}
}
