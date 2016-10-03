package com.happiestminds.projectallocationsystem.dao.impl;

import com.happiestminds.projectallocationsystem.dao.SkillSetDAO;
import com.happiestminds.projectallocationsystem.entity.SkillSetEntity;

/**
 * @author rasool.shaik
 * 
 */
public class SkillSetDaoImpl extends AbstractDaoImpl<SkillSetEntity, String> implements SkillSetDAO {

	protected SkillSetDaoImpl() {
		super(SkillSetEntity.class);
	}

}
