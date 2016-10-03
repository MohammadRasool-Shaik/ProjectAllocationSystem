package com.happiestminds.projectallocationsystem.service.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happiestminds.projectallocationsystem.dao.SkillSetDAO;
import com.happiestminds.projectallocationsystem.entity.SkillSetEntity;
import com.happiestminds.projectallocationsystem.service.SkillSetService;

@Service
public class SkillSetServiceImpl implements SkillSetService {

	private static Logger logger = LoggerFactory.getLogger(SkillSetServiceImpl.class.getSimpleName());

	@Autowired
	private SkillSetDAO skillSetDAO;

	public SkillSetServiceImpl() {
	}

	@Override
	public boolean addSkillSet(SkillSetEntity skillSetEntity) {
		boolean isAddedSkill = false;
		SkillSetEntity skillSet = null;
		try {
			skillSet = skillSetDAO.findById(skillSetEntity.getSkillID());
			if (skillSet == null) {
				skillSetEntity.setSkillID(skillSetEntity.getSkillID().toUpperCase());
				skillSetDAO.save(skillSetEntity);
				isAddedSkill = true;
			}
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED  WHILE SAVING SKILL INTO SKILLENTITY" + hex.getMessage(), hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED  WHILE SAVING SKILL INTO SKILLENTITY" + ex.getMessage(), ex);
		}

		return isAddedSkill;
	}

	@Override
	public boolean updateSkillSet(SkillSetEntity skillSetEntity) {
		boolean isSkillSetUpdated = false;
		try {
			skillSetDAO.update(skillSetEntity);
			isSkillSetUpdated = true;
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED  WHILE UPDATING SKILL INTO SKILLENTITY" + hex.getMessage(), hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED  WHILE UPDATING SKILL INTO SKILLENTITY" + ex.getMessage(), ex);
		}

		return isSkillSetUpdated;
	}

	@Override
	public boolean deleteSkillSet(SkillSetEntity skillSetEntity) {
		boolean isSkillSetDeleted = false;
		try {
			skillSetDAO.deleteById("SkillSetEntity", skillSetEntity.getSkillID());
			isSkillSetDeleted = true;
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED WHILE DELTEING SKILL SKILLENTITY" + hex.getMessage(), hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED  WHILE DELTEING SKILL  SKILLENTITY" + ex.getMessage(), ex);
		}
		return isSkillSetDeleted;
	}

	@Override
	public List<SkillSetEntity> fetchSkillSets(Set<String> skillGroups) {
		List<SkillSetEntity> skills = null;
		try {
			skills = (List<SkillSetEntity>) skillSetDAO.findAll();

			for (SkillSetEntity skillSetEntity : skills) {
				skillGroups.add(skillSetEntity.getGroupInfo());
			}

		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED WHILE FETCHING SKILLS FROM SKILLENTITY" + hex.getMessage(), hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED  WHILE FETCHING SKILLS FROM SKILLENTITY" + ex.getMessage(), ex);
		}
		return skills;
	}

}
