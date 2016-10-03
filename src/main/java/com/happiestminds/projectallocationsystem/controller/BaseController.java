package com.happiestminds.projectallocationsystem.controller;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.happiestminds.projectallocationsystem.Dto.ModuleDto;
import com.happiestminds.projectallocationsystem.exception.CustomGenericException;
import com.happiestminds.projectallocationsystem.exception.DataException;
import com.happiestminds.projectallocationsystem.service.UserGroupService;

/**
 * 
 * @author rasool.shaik
 * 
 */
public abstract class BaseController {
	@Autowired
	UserGroupService userGroupService;

	private static Logger logger = LoggerFactory.getLogger(BaseController.class.getSimpleName());

	protected UserDetails getCurrentUser() {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		return (UserDetails) a.getPrincipal();
	}

	@ExceptionHandler({ DataException.class })
	public Map<String, String> handleConstraintVoilationException(Exception ex) {
		logger.warn("Client SIDE EXCEPTION: {}", ex);
		Map<String, String> errors = new HashMap<String, String>();
		errors.put("Test", "test");
		return errors;

	}

	@ExceptionHandler({ CustomGenericException.class })
	public ModelAndView handleCustomGenericException(CustomGenericException cgx) {
		ModelAndView model = new ModelAndView("exception");
		model.addObject("errCode", cgx.getStatusCode());
		model.addObject("errMsg", cgx.getStatusMsg());
		return model;
	}

	@ExceptionHandler({ HibernateException.class, PersistenceException.class, SQLException.class })
	public ModelAndView handleDataRelatedExceptions(Throwable tx) {
		ModelAndView model = new ModelAndView("exception");
		model.addObject("name", tx.getClass().getSimpleName());
		model.addObject("errMsg", tx.getMessage());
		return model;
	}

	@ExceptionHandler({ MessagingException.class })
	public ModelAndView handleMailRelatedExceptions(Throwable tx) {
		ModelAndView model = new ModelAndView("exception");
		model.addObject("name", tx.getClass().getSimpleName());
		model.addObject("errMsg", tx.getMessage());
		return model;
	}

	@ExceptionHandler({ Exception.class })
	public ModelAndView handleAllTypeOfExceptions(Exception ex) {
		ModelAndView model = new ModelAndView("exception");
		model.addObject("name", ex.getClass().getSimpleName());
		model.addObject("errMsg", ex.getMessage());
		return model;
	}

	public String fetchOperationsByUserGroupForMenu(Model model) {
		try {
			UserDetails currentUser = getCurrentUser();
			Collection<GrantedAuthority> authorities = currentUser.getAuthorities();
			Set<ModuleDto> modules = userGroupService.fetchModulesByRights(authorities);
			model.addAttribute("modules", modules);
		} catch (Exception e) {
			logger.error("EXCPTION OCCURED WHILE PROCESSING REQUEST" + e.toString());
		}
		return "home";
	}
}
