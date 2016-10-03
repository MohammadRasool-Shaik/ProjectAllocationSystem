package com.happiestminds.projectallocationsystem.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.happiestminds.projectallocationsystem.entity.SkillSetEntity;

/**
 * @author rasool.shaik
 * 
 */
public interface SkillSetDAO extends AbstractDAO<SkillSetEntity, String> {
	
	List<String> fetchAllSkillSetIds()throws HibernateException;

}
