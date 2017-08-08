package org.rash.projectallocationsystem.dao.impl;

import org.rash.projectallocationsystem.dao.SubSkillSetDAO;
import org.rash.projectallocationsystem.entity.SubSkillSetEntity;
import org.springframework.stereotype.Repository;

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
