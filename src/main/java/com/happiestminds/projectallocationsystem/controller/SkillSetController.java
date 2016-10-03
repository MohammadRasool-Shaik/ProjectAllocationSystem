package com.happiestminds.projectallocationsystem.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.happiestminds.projectallocationsystem.entity.SkillSetEntity;
import com.happiestminds.projectallocationsystem.service.SkillSetService;

/**
 * @author rasool.shaik
 * 
 */
@Controller
public class SkillSetController {

	@Autowired
	private SkillSetService skillSetService;


	/**
	 * 
	 */
	public SkillSetController() {
	}

	@RequestMapping(value = "skillm", method = RequestMethod.GET)
	public String skillSetMaintainance(Model model) {
		Set<String> skillGroups = new HashSet<String>();
		List<SkillSetEntity> skillSets = skillSetService.fetchSkillSets(skillGroups);
		if (skillSets.isEmpty()) {
			model.addAttribute("skillsEmptyMsg", "Skills are not available, Contact Administrator");
		} else {
			model.addAttribute("skillSetList", skillSets);
		}
		model.addAttribute("skillGroups", skillGroups);
		return "skillset";
	}

	@RequestMapping(value = "skillsetactions", method = RequestMethod.POST)
	public String skillSetActions(@ModelAttribute SkillSetEntity skillSet, @RequestParam String action, BindingResult bindingResult, Model model) {
		SkillSetEntity skillsetResult = new SkillSetEntity();
		switch (action.toLowerCase()) {
		case "add":
			boolean isaddSkillSet = skillSetService.addSkillSet(skillSet);
			if (!isaddSkillSet) {
				model.addAttribute("message", "SkillSet already Exist, With Same Id " + skillSet.getSkillID());
			}
			skillsetResult = skillSet;
			break;
		case "edit":
			skillSetService.updateSkillSet(skillSet);
			skillsetResult = skillSet;
			break;
		case "delete":
			skillSetService.deleteSkillSet(skillSet);
			skillsetResult = new SkillSetEntity();
			break;
		}
		model.addAttribute("skillSet", skillsetResult);
		Set<String> skillGroups = new HashSet<String>();
		List<SkillSetEntity> skillSets = skillSetService.fetchSkillSets(skillGroups);
		if (skillSets.isEmpty()) {
			model.addAttribute("skillsEmptyMsg", "Skills are not available, Contact Administrator");
		} else {
			model.addAttribute("skillSetList", skillSets);
			model.addAttribute("skillGroups", skillGroups);
		}
		return "skillset";
	}

}
