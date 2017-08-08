package org.rash.projectallocationsystem.service.impl;

import java.util.Collection;
import java.util.Map;

import org.rash.projectallocationsystem.Dto.SkillSetDto;
import org.rash.projectallocationsystem.Dto.SkillSetOptionDto;
import org.rash.projectallocationsystem.Dto.StatusDto;
import org.rash.projectallocationsystem.Dto.batch.SkillSetListDto;
import org.rash.projectallocationsystem.dao.SkillSetDAO;
import org.rash.projectallocationsystem.entity.SkillSetEntity;
import org.rash.projectallocationsystem.enumerator.StatusCodeEnum;
import org.rash.projectallocationsystem.response.SkillSetOptionsResponse;
import org.rash.projectallocationsystem.service.SkillSetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillSetServiceImpl extends AbstractServiceImpl implements SkillSetService {

	@Autowired
	private SkillSetDAO skillSetDAO;

	public SkillSetServiceImpl() {
	}

	@Override
	public void addSkillSet(SkillSetDto skillSetDto) {
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		SkillSetEntity skillSet = skillSetDAO.findById(skillSetDto.getSkillID());
		if (skillSet == null) {
			skillSetDto.setSkillID(skillSetDto.getSkillID().toUpperCase());
			skillSet = new SkillSetEntity();
			BeanUtils.copyProperties(skillSetDto, skillSet);
			skillSetDAO.save(skillSet);
			statusDto.setStatusCode(StatusCodeEnum.OK);
			statusDto.setStatusMessage("Skill Added Successfully");
		} else {
			statusDto.setStatusMessage("Skill with same ID Already Exit, Try Another?");
			// throwAlredyExistValidationError(SkillSetDto.class.getSimpleName(),
			// skillSetDto.getSkillID());
		}
		skillSetDto.setStatusDto(statusDto);
	}

	@Override
	public void updateSkillSet(SkillSetDto skillSetDto) {
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		SkillSetEntity skillSet = new SkillSetEntity();
		BeanUtils.copyProperties(skillSetDto, skillSet);
		skillSet = skillSetDAO.update(skillSet);
		if (skillSet != null) {
			statusDto.setStatusCode(StatusCodeEnum.OK);
			statusDto.setStatusMessage("Successfully updated Skill");
		} else {
			statusDto.setStatusMessage("Skill not updated properly");
		}
		skillSetDto.setStatusDto(statusDto);
	}

	@Override
	public void deleteSkillSet(SkillSetDto skillSetDto) {
		int noOfEntiesDeleted = 0;
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		noOfEntiesDeleted = skillSetDAO.deleteById("SkillSetEntity", skillSetDto.getSkillID());
		SkillSetDto skillTemp = new SkillSetDto();
		BeanUtils.copyProperties(skillTemp, skillSetDto);
		if (noOfEntiesDeleted > 0) {
			statusDto.setStatusCode(StatusCodeEnum.OK);
			statusDto.setStatusMessage("Successfully Deleted user");
		}
		skillSetDto.setStatusDto(statusDto);
	}

	@Override
	public SkillSetListDto fetchSkillSets(Map<String, String> skillGroups, int startIndex, int pageSize, String sortVar) {
		SkillSetListDto skills = new SkillSetListDto();
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		Collection<SkillSetEntity> skillSets = skillSetDAO.fetchAllSkillSets(startIndex, pageSize, sortVar);
		if (!skillSets.isEmpty()) {
			for (SkillSetEntity skillSet : skillSets) {
				SkillSetDto skillSetDto = new SkillSetDto();
				BeanUtils.copyProperties(skillSet, skillSetDto);
				skills.getSkills().add(skillSetDto);
				if (skillGroups != null && !skillGroups.containsKey(skillSet.getGroupInfo())) {
					skillGroups.put(skillSet.getGroupInfo(), skillSet.getGroupInfo());
				}
			}
			statusDto.setStatusCode(StatusCodeEnum.OK);
		} else {
			statusDto.setStatusMessage("Skills are not available, Contact Administrator");
		}
		skills.setStatusDto(statusDto);

		return skills;
	}

	@Override
	public int getSkillSetCount() {
		return skillSetDAO.getSkillSetCount();
	}

	@Override
	public SkillSetOptionsResponse getSkillSetOptions() {
		Collection<SkillSetEntity> skillSets = skillSetDAO.findAll();
		SkillSetOptionsResponse skillOptions = new SkillSetOptionsResponse();
		skillOptions.setResult("ERROR");
		if (!skillSets.isEmpty()) {
			for (SkillSetEntity skillSet : skillSets) {
				SkillSetOptionDto skillSetDto = new SkillSetOptionDto();
				skillSetDto.setValue(skillSet.getSkillID());
				skillSetDto.setDisplayText(skillSet.getDescription());
				skillOptions.getOptions().add(skillSetDto);
			}
			skillOptions.setResult("OK");
		} else {
			skillOptions.setMessage("Something went wrong on server side");
		}
		return skillOptions;
	}
}
