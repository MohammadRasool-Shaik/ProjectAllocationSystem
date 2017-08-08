package org.rash.projectallocationsystem.controller;

import java.util.HashMap;
import java.util.Map;

import org.rash.projectallocationsystem.Dto.SkillSetDto;
import org.rash.projectallocationsystem.Dto.batch.SkillSetListDto;
import org.rash.projectallocationsystem.response.SkillSetListResponse;
import org.rash.projectallocationsystem.response.SkillSetOptionsResponse;
import org.rash.projectallocationsystem.response.SkillSetResponse;
import org.rash.projectallocationsystem.service.SkillSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author rasool.shaik
 * 
 */
@Controller
public class SkillSetController extends BaseController {

	@Autowired
	private SkillSetService skillSetService;

	/**
	 * 
	 */
	public SkillSetController() {
	}

	@RequestMapping(value = "/skillm", method = RequestMethod.GET)
	public ModelAndView skillManagement(Model model) {
		fetchOperationsByUserGroupForMenu(model);
		return new ModelAndView("skillsetmang");
	}

	@RequestMapping(value = "/skillm/getAllSkills", method = RequestMethod.POST)
	public @ResponseBody
	SkillSetListResponse skillSetMaintainance(@RequestParam int jtStartIndex, @RequestParam int jtPageSize, @RequestParam(required = false) String jtSorting) {
		SkillSetListResponse skillSetListResponse = new SkillSetListResponse();

		Map<String, String> skillGroups = new HashMap<String, String>();
		SkillSetListDto skillSets = skillSetService.fetchSkillSets(skillGroups, jtStartIndex, jtPageSize, jtSorting);
		int skillSetCount = skillSetService.getSkillSetCount();

		skillSetListResponse.setSkills(skillSets.getSkills());
		skillSetListResponse.setTotalRecordCount(skillSetCount);
		skillSetListResponse.setSkillGroups(skillGroups);

		skillSetListResponse.setResult(skillSets.getStatusDto().getStatusCode().getMsg());
		skillSetListResponse.setMessage(skillSets.getStatusDto().getStatusMessage());

		return skillSetListResponse;
	}

	@RequestMapping(value = "/skillm/skillsetactions", method = RequestMethod.POST)
	public @ResponseBody
	SkillSetResponse skillSetActions(@RequestParam String action, @ModelAttribute SkillSetDto skillSet) {
		SkillSetDto skillsetResult = new SkillSetDto();
		SkillSetResponse skillSetResponse = new SkillSetResponse();
		switch (action.toLowerCase()) {
		case "save":
			skillSetService.addSkillSet(skillSet);
			skillsetResult = skillSet;
			break;
		case "update":
			skillSetService.updateSkillSet(skillSet);
			skillsetResult = skillSet;
			break;
		case "delete":
			skillSetService.deleteSkillSet(skillSet);
			skillsetResult = skillSet;
			break;
		}
		skillSetResponse.setSkillSet(skillsetResult);

		skillSetResponse.setResult(skillsetResult.getStatusDto().getStatusCode().getMsg());
		skillSetResponse.setMessage(skillsetResult.getStatusDto().getStatusMessage());

		return skillSetResponse;
	}

	@RequestMapping(value = "/skillm/getSkillOptions", method = RequestMethod.POST)
	public @ResponseBody
	SkillSetOptionsResponse getSkillOptions() {
		return skillSetService.getSkillSetOptions();
	}

}
