package com.happiestminds.projectallocationsystem.service;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.projectallocationsystem.Dto.SkillSetDto;
import com.happiestminds.projectallocationsystem.Dto.batch.SkillSetListDto;
import com.happiestminds.projectallocationsystem.response.SkillSetOptionsResponse;

/**
 * @author rasool.shaik
 * 
 */
public interface SkillSetService {

	@Transactional(readOnly=false)
	void addSkillSet(SkillSetDto skillSetDto);

	@Transactional(readOnly=false)
	void updateSkillSet(SkillSetDto skillSetDto);

	@Transactional
	void deleteSkillSet(SkillSetDto skillSetDto);

	@Transactional(readOnly=true)
	SkillSetListDto fetchSkillSets(Map<String,String> skillGroups, int jtStartIndex, int jtPageSize, String sortVar);
	
	@Transactional(readOnly=true)
	SkillSetOptionsResponse getSkillSetOptions();

	@Transactional(readOnly=true)
	int getSkillSetCount();
}
