package com.happiestminds.projectallocationsystem.dao.impl;

import org.springframework.stereotype.Repository;

import com.happiestminds.projectallocationsystem.dao.SubSkillSetDAO;
import com.happiestminds.projectallocationsystem.entity.SubSkillSetEntity;

/**
 * @author rasool.shaik
 * 
 */
@Repository
public class SubSkillSetDaoImpl extends AbstractDaoImpl<SubSkillSetEntity, String> implements SubSkillSetDAO {

	protected SubSkillSetDaoImpl() {
		super(SubSkillSetEntity.class);
	}

}
