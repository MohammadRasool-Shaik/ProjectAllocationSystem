package com.happiestminds.projectallocationsystem.service;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.projectallocationsystem.entity.SkillSetEntity;

/**
 * @author rasool.shaik
 * 
 */
public interface SkillSetService {

	@Transactional(readOnly=false)
	boolean addSkillSet(SkillSetEntity skillSetEntity);

	@Transactional(readOnly=false)
	boolean updateSkillSet(SkillSetEntity skillSetEntity);

	@Transactional
	boolean deleteSkillSet(SkillSetEntity skillSetEntity);

	@Transactional(readOnly=true)
	List<SkillSetEntity> fetchSkillSets(Set<String> skillGroups);
}
