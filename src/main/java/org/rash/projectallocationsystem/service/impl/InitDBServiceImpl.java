package org.rash.projectallocationsystem.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.rash.projectallocationsystem.dao.CustomerDAO;
import org.rash.projectallocationsystem.dao.ModuleDao;
import org.rash.projectallocationsystem.dao.OperationDAO;
import org.rash.projectallocationsystem.dao.ProjectDAO;
import org.rash.projectallocationsystem.dao.SkillSetDAO;
import org.rash.projectallocationsystem.entity.CustomerEntity;
import org.rash.projectallocationsystem.entity.ModuleEntity;
import org.rash.projectallocationsystem.entity.OperationEntity;
import org.rash.projectallocationsystem.entity.ProjectEntity;
import org.rash.projectallocationsystem.entity.SkillSetEntity;
import org.rash.projectallocationsystem.service.InitDBService;
import org.rash.projectallocationsystem.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rasool.shaik
 * 
 */
@Service("initDBService")
public class InitDBServiceImpl implements InitDBService {

	private static Logger logger = LoggerFactory.getLogger(InitDBServiceImpl.class.getSimpleName());

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ModuleDao moduleDao;

	@Autowired
	private OperationDAO operationDAO;

	@Autowired
	private SkillSetDAO skillSetDAO;

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private ProjectDAO projectDAO;

	@Value("${init.data.skillsetData}")
	private String skillSetInitData;

	@Value("${init.data.customerData}")
	private String customerData;

	@Value("${init.data.projectData}")
	private String projectData;

	@Value("${init.data.operationData}")
	private String operationData;

	@Value("${init.data.moduleData}")
	private String moduleData;

	/**
	 * 
	 */
	public InitDBServiceImpl() {
		super();
	}

	@Override
	@Transactional
	public void loadInitialData() {

		try {
			moduleInitialData();
			operationsInitialData();
			skillSetIntialData();
			customerIntialData();
			projectsIntialData();
		} catch (IOException iex) {
			logger.error("Exception OCCURED WHILE READING DATA FROM INITDB TEXT FILES IN" + iex);
		} catch (ArrayIndexOutOfBoundsException aibx) {
			logger.error("ArrayIndexOutOfBoundsException OCCURED WHILE READING DATA FROM INITDB TEXT FILES IN" + aibx);
		} catch (NumberFormatException nfex) {
			logger.error("Exception OCCURED WHILE PARSE STRING " + nfex);
		} catch (ParseException pex) {
			logger.error("Exception OCCURED WHILE PARSE DATE " + pex);
		} catch (HibernateException hex) {
			logger.error("Exception OCCURED WHILE INSERTING DATO INTO DATABASE FILES" + hex);
		} catch (Exception ex) {
			logger.error("Exception OCCURED WHILE WORKING TEXT FILES" + ex);
		}
	}

	private void projectsIntialData() throws IOException, ParseException {
		List<String> persistedPIDs = projectDAO.fetchAllProjectIds();
		List<String> projectIdList = new ArrayList<String>();
		saveOrUpdateProjects(projectIdList, persistedPIDs);
		for (String projectId : persistedPIDs) {
			if (!projectIdList.contains(projectId)) {
				projectDAO.deleteById("ProjectEntity", projectId);
			}
		}
	}

	private void saveOrUpdateProjects(List<String> projectIdList, List<String> persistedPIDs) throws IOException, ParseException {
		BufferedReader br = new BufferedReader(new FileReader(projectData));
		ProjectEntity project = new ProjectEntity();
		CustomerEntity customer = new CustomerEntity();
		String line;

		while ((line = br.readLine()) != null) {
			String[] projectEachEntry = line.split(",");
			if (projectEachEntry[0] != null && !persistedPIDs.contains(projectEachEntry[0].toUpperCase())) {
				project.setProjectID(projectEachEntry[0].toUpperCase());
				projectIdList.add(projectEachEntry[0]);
				project.setProjectName(projectEachEntry[1]);
				project.setAdminSPOC(projectEachEntry[2]);
				project.setTechSPOC(projectEachEntry[3]);
				project.setStartDate(DateUtil.getDate(projectEachEntry[4]));
				project.setExpectedEndDate(DateUtil.getDate(projectEachEntry[5]));
				customer.setCustomerID(projectEachEntry[6]);
				project.setCustomer(customer);
				projectDAO.save(project);
			} else {
				project.setProjectID(projectEachEntry[0].toUpperCase());
				projectIdList.add(projectEachEntry[0]);
				project.setProjectName(projectEachEntry[1]);
				project.setAdminSPOC(projectEachEntry[2]);
				project.setTechSPOC(projectEachEntry[3]);
				project.setStartDate(DateUtil.getDate(projectEachEntry[4]));
				project.setExpectedEndDate(DateUtil.getDate(projectEachEntry[5]));
				customer.setCustomerID(projectEachEntry[6]);
				project.setCustomer(customer);
				projectDAO.update(project);
			}
		}
		br.close();
	}

	private void customerIntialData() throws IOException {
		List<String> persistedCIDs = customerDAO.fetchAllCustomerIds();
		List<String> customerIdList = new ArrayList<String>();
		saveOrUpdateCustomers(customerIdList, persistedCIDs);
		for (String customerId : persistedCIDs) {
			if (!customerIdList.contains(customerId)) {
				skillSetDAO.deleteById("CustomerEntity", customerId);
			}
		}
	}

	private void saveOrUpdateCustomers(List<String> customerIdList, List<String> persistedCIDs) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(customerData));
		CustomerEntity customer = new CustomerEntity();
		String line;
		while ((line = br.readLine()) != null) {
			String[] customerEachEntry = line.split(",");
			if (customerEachEntry[0] != null && !persistedCIDs.contains(customerEachEntry[0].toUpperCase())) {
				customer.setCustomerID(customerEachEntry[0].toUpperCase());
				customerIdList.add(customerEachEntry[0]);
				customer.setCustomerName(customerEachEntry[1]);
				customerDAO.save(customer);
			} else {
				customer.setCustomerID(customerEachEntry[0]);
				customerIdList.add(customerEachEntry[0]);
				customer.setCustomerName(customerEachEntry[1]);
				customerDAO.update(customer);
			}
		}
		br.close();
	}

	private void skillSetIntialData() throws IOException {
		List<String> persistedSSIds = skillSetDAO.fetchAllSkillSetIds();
		List<String> skillIdList = new ArrayList<String>();
		saveOrUpdateSkillSets(skillIdList, persistedSSIds);

		for (String skillSetId : persistedSSIds) {
			if (!skillIdList.contains(skillSetId)) {
				skillSetDAO.deleteById("SkillSetEntity", skillSetId);
			}
		}

	}

	private void saveOrUpdateSkillSets(List<String> skillIdList, List<String> peersistedSSIds) throws IOException {

		// Properties props = PropertiesLoaderUtils.loadProperties(resource);
		// String property = props.getProperty("init.data.skillsetData");

		BufferedReader br = new BufferedReader(new FileReader(skillSetInitData));

		SkillSetEntity skillSet = new SkillSetEntity();
		String line;
		while ((line = br.readLine()) != null) {
			String[] skillsetEachEntry = line.split(",");
			if (skillsetEachEntry[0] != null && !peersistedSSIds.contains(skillsetEachEntry[0].toUpperCase())) {
				skillSet.setSkillID(skillsetEachEntry[0].toUpperCase());
				skillIdList.add(skillsetEachEntry[0]);
				skillSet.setDescription(skillsetEachEntry[1]);
				skillSet.setGroupInfo(skillsetEachEntry[2]);
				skillSetDAO.save(skillSet);
			} else {
				skillSet.setSkillID(skillsetEachEntry[0]);
				skillIdList.add(skillsetEachEntry[0]);
				skillSet.setDescription(skillsetEachEntry[1]);
				skillSet.setGroupInfo(skillsetEachEntry[2]);
				skillSetDAO.update(skillSet);
			}
		}
		br.close();
	}

	/**
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws HibernateException
	 */
	private void moduleInitialData() throws IOException, NumberFormatException, HibernateException, ArrayIndexOutOfBoundsException {
		List<String> moduleIdList = new ArrayList<String>();
		List<String> persistedModuleIds = moduleDao.fetchAllModuleIds();

		saveOrUpdateModules(moduleIdList, persistedModuleIds);

		for (String moduleId : persistedModuleIds) {
			if (!moduleIdList.contains(moduleId)) {
				moduleDao.deleteById("ModuleEntity", moduleId);
			}
		}
	}

	/**
	 * @param resource
	 * @param moduleIdList
	 * @param persistedModuleIds
	 * @throws IOException
	 */
	public void saveOrUpdateModules(List<String> moduleIdList, List<String> persistedModuleIds) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(moduleData));
		ModuleEntity module = new ModuleEntity();
		String line;
		while ((line = br.readLine()) != null) {
			String[] moduleEachEntry = line.split(",");
			if (moduleEachEntry[0] != null && !persistedModuleIds.contains(moduleEachEntry[0].toUpperCase())) {
				module.setModuleId(moduleEachEntry[0].toUpperCase());
				moduleIdList.add(moduleEachEntry[0]);
				module.setModuleName(moduleEachEntry[1]);
				module.setViewOrder(Integer.valueOf(moduleEachEntry[2]));
				moduleDao.save(module);
			} else {
				module.setModuleId(moduleEachEntry[0]);
				moduleIdList.add(moduleEachEntry[0]);
				module.setModuleName(moduleEachEntry[1]);
				module.setViewOrder(Integer.valueOf(moduleEachEntry[2]));
				moduleDao.update(module);
			}
		}
		br.close();
	}

	/**
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws HibernateException
	 */
	private void operationsInitialData() throws IOException, NumberFormatException, HibernateException, ArrayIndexOutOfBoundsException {
		List<String> operationIdList = new ArrayList<String>();
		List<String> persistedOpsIds = operationDAO.fetchAllOperationIds();

		saveOrUpdateOperations(operationIdList, persistedOpsIds);
		for (String operationId : persistedOpsIds) {
			if (!operationIdList.contains(operationId)) {
				operationDAO.deleteById("OperationEntity", operationId);
			}
		}
	}

	/**
	 * @param resource
	 * @param operationIdList
	 * @param persistedOpsIds
	 * @throws IOException
	 */
	public void saveOrUpdateOperations(List<String> operationIdList, List<String> persistedOpsIds) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(operationData));
		OperationEntity operation = new OperationEntity();
		ModuleEntity module = new ModuleEntity();
		String line;
		while ((line = br.readLine()) != null) {
			String[] operationEachEntry = line.split(",");
			if (operationEachEntry[0] != null && !persistedOpsIds.contains(operationEachEntry[0].toUpperCase())) {
				operation.setOperationId(operationEachEntry[0].toUpperCase());
				operationIdList.add(operationEachEntry[0]);
				operation.setDescription(operationEachEntry[1]);
				module.setModuleId(operationEachEntry[2]);
				operation.setViewOrder(Integer.valueOf(operationEachEntry[3]));
				operation.setModule(module);
				operationDAO.save(operation);
			} else {
				operation.setOperationId(operationEachEntry[0]);
				operationIdList.add(operationEachEntry[0]);
				operation.setDescription(operationEachEntry[1]);
				module.setModuleId(operationEachEntry[2]);
				operation.setViewOrder(Integer.valueOf(operationEachEntry[3]));
				operation.setModule(module);
				operationDAO.update(operation);
			}
		}
		br.close();
	}
}
