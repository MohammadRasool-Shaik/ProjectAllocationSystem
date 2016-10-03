package com.happiestminds.projectallocationsystem.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.projectallocationsystem.dao.CustomerDAO;
import com.happiestminds.projectallocationsystem.dao.ModuleDao;
import com.happiestminds.projectallocationsystem.dao.OperationDAO;
import com.happiestminds.projectallocationsystem.dao.SkillSetDAO;
import com.happiestminds.projectallocationsystem.entity.CustomerEntity;
import com.happiestminds.projectallocationsystem.entity.ModuleEntity;
import com.happiestminds.projectallocationsystem.entity.OperationEntity;
import com.happiestminds.projectallocationsystem.entity.SkillSetEntity;
import com.happiestminds.projectallocationsystem.service.InitDBService;

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
		} catch (IOException iex) {
			logger.error("Exception OCCURED WHILE READING DATA FROM INITDB TEXT FILES IN" + iex);
		} catch (ArrayIndexOutOfBoundsException aibx) {
			logger.error("ArrayIndexOutOfBoundsException OCCURED WHILE READING DATA FROM INITDB TEXT FILES IN" + aibx);
		} catch (NumberFormatException nfex) {
			logger.error("Exception OCCURED WHILE PARSE STRING " + nfex);
		} catch (HibernateException hex) {
			logger.error("Exception OCCURED WHILE INSERTING DATO INTO DATABASE FILES" + hex);
		} catch (Exception ex) {
			logger.error("Exception OCCURED WHILE WORKING TEXT FILES" + ex);
		}
	}

	private void customerIntialData() throws IOException {
		Resource resource = applicationContext.getResource("classpath:/config/initDB/customerData.txt");
		List<String> persistedCIDs = customerDAO.fetchAllCustomerIds();
		List<String> customerIdList = new ArrayList<String>();
		saveOrUpdateCustomers(resource, customerIdList, persistedCIDs);
		for (String customerId : persistedCIDs) {
			if (!customerIdList.contains(customerId)) {
				skillSetDAO.deleteById("CustomerEntity", customerId);
			}
		}
	}

	private void saveOrUpdateCustomers(Resource resource, List<String> customerIdList, List<String> persistedCIDs) throws IOException {
		InputStream is = resource.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		CustomerEntity customer = new CustomerEntity();
		String line;
		while ((line = br.readLine()) != null) {
			String[] customerEachEntry = line.split(",");
			if (customerEachEntry[0] != null && !persistedCIDs.contains(customerEachEntry[0])) {
				customer.setCustomerID(customerEachEntry[0]);
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
		is.close();
	}

	private void skillSetIntialData() throws IOException {
		Resource resource = applicationContext.getResource("classpath:/config/initDB/skillSetData.txt");
		List<String> persistedSSIds = skillSetDAO.fetchAllSkillSetIds();
		List<String> skillIdList = new ArrayList<String>();
		saveOrUpdateSkillSets(resource, skillIdList, persistedSSIds);

		for (String skillSetId : persistedSSIds) {
			if (!skillIdList.contains(skillSetId)) {
				skillSetDAO.deleteById("SkillSetEntity", skillSetId);
			}
		}

	}

	private void saveOrUpdateSkillSets(Resource resource, List<String> skillIdList, List<String> peersistedSSIds) throws IOException {
		InputStream is = resource.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		SkillSetEntity skillSet = new SkillSetEntity();
		String line;
		while ((line = br.readLine()) != null) {
			String[] skillsetEachEntry = line.split(",");
			if (skillsetEachEntry[0] != null && !peersistedSSIds.contains(skillsetEachEntry[0])) {
				skillSet.setSkillID(skillsetEachEntry[0]);
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
		is.close();

	}

	/**
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws HibernateException
	 */
	private void moduleInitialData() throws IOException, NumberFormatException, HibernateException, ArrayIndexOutOfBoundsException {
		Resource resource = applicationContext.getResource("classpath:/config/initDB/moduleData.txt");
		List<String> moduleIdList = new ArrayList<String>();
		List<String> persistedModuleIds = moduleDao.fetchAllModuleIds();

		saveOrUpdateModules(resource, moduleIdList, persistedModuleIds);

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
	public void saveOrUpdateModules(Resource resource, List<String> moduleIdList, List<String> persistedModuleIds) throws IOException {
		InputStream is = resource.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		ModuleEntity module = new ModuleEntity();
		String line;
		while ((line = br.readLine()) != null) {
			String[] moduleEachEntry = line.split(",");
			if (moduleEachEntry[0] != null && !persistedModuleIds.contains(moduleEachEntry[0])) {
				module.setModuleId(moduleEachEntry[0]);
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
		is.close();
	}

	/**
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws HibernateException
	 */
	private void operationsInitialData() throws IOException, NumberFormatException, HibernateException, ArrayIndexOutOfBoundsException {
		Resource resource = applicationContext.getResource("classpath:/config/initDB/OperationData.txt");
		List<String> operationIdList = new ArrayList<String>();
		List<String> persistedOpsIds = operationDAO.fetchAllOperationIds();

		saveOrUpdateOperations(resource, operationIdList, persistedOpsIds);
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
	public void saveOrUpdateOperations(Resource resource, List<String> operationIdList, List<String> persistedOpsIds) throws IOException {
		InputStream is = resource.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		OperationEntity operation = new OperationEntity();
		ModuleEntity module = new ModuleEntity();
		String line;
		while ((line = br.readLine()) != null) {
			String[] operationEachEntry = line.split(",");
			if (operationEachEntry[0] != null && !persistedOpsIds.contains(operationEachEntry[0])) {
				operation.setOperationId(operationEachEntry[0]);
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
		is.close();
	}
}
