package com.happiestminds.projectallocationsystem.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.happiestminds.projectallocationsystem.dao.ModuleDao;
import com.happiestminds.projectallocationsystem.dao.OperationDAO;
import com.happiestminds.projectallocationsystem.entity.ModuleEntity;
import com.happiestminds.projectallocationsystem.entity.OperationEntity;
import com.happiestminds.projectallocationsystem.service.InitDBService;

/**
 * @author rasool.shaik
 * 
 */
@Service("InitDBService")
public class InitDBServiceImpl implements InitDBService {

	private static Logger logger = LoggerFactory.getLogger(InitDBServiceImpl.class.getSimpleName());

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ModuleDao moduleDao;

	@Autowired
	private OperationDAO operationDAO;

	/**
	 * 
	 */
	public InitDBServiceImpl() {
		super();
	}

	@PostConstruct
	@Override
	public void initTables() {

		try {
			moduleInitialData();
			operationsInitialData();
		} catch (IOException iex) {
			logger.error("Exception OCCURED WHILE READING DATA FROM INITDB TEXT FILES IN" + iex);
		} catch (ArrayIndexOutOfBoundsException aibx) {
			logger.error("ArrayIndexOutOfBoundsException OCCURED WHILE READING DATA FROM INITDB TEXT FILES IN" + aibx);
		} catch (NumberFormatException nfex) {
			logger.error("Exception OCCURED WHILE PARSE STRING " + nfex);
		} catch (HibernateException hex) {
			logger.error("Exception OCCURED WHILE INSERTING DATO INTO DATABASE FILES" +hex );
		} catch (Exception ex) {
			logger.error("Exception OCCURED WHILE WORKING TEXT FILES" + ex);
		}

	}

	/**
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws HibernateException
	 */
	private void moduleInitialData() throws IOException, NumberFormatException, HibernateException, ArrayIndexOutOfBoundsException {
		Resource resource = applicationContext.getResource("classpath:/config/initDB/moduleData.txt");
		InputStream is = resource.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		ModuleEntity module = new ModuleEntity();
		String line;
		while ((line = br.readLine()) != null) {
			String[] moduleEachEntry = line.split(",");
			module.setModuleId(moduleEachEntry[0]);
			module.setModuleName(moduleEachEntry[1]);
			module.setViewOrder(Integer.valueOf(moduleEachEntry[2]));
			moduleDao.saveOrUpdate(module);
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
		InputStream is = resource.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		OperationEntity operation = new OperationEntity();
		ModuleEntity module = new ModuleEntity();
		String line;
		while ((line = br.readLine()) != null) {
			String[] operationEachEntry = line.split(",");
			operation.setOperationId(operationEachEntry[0]);
			operation.setDescription(operationEachEntry[1]);
			module.setModuleId(operationEachEntry[2]);
			operation.setModule(module);
			operation.setViewOrder(Integer.valueOf(operationEachEntry[3]));
			operationDAO.saveOrUpdate(operation);
		}
		br.close();
		is.close();
	}

}
