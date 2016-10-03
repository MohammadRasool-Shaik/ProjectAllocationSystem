package com.happiestminds.projectallocationsystem.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happiestminds.projectallocationsystem.dao.EmployeeDAO;
import com.happiestminds.projectallocationsystem.entity.EmployeeEntity;
import com.happiestminds.projectallocationsystem.entity.UserEntity;

/**
 * @author rasool.shaik
 * 
 */
@Repository
public class EmployeeDaoImpl extends AbstractDaoImpl<EmployeeEntity, String> implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected EmployeeDaoImpl() {
		super(EmployeeEntity.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeEntity> fetchAllEmployeesWithAllInfo() {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(EmployeeEntity.class, "emp");
		criteria.createAlias("emp.skillSetEntity", "skill", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias("emp.projectEntity", "project", CriteriaSpecification.INNER_JOIN);

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeEntity> fetchAllEmployees(int startIndex, int pageSize, String sortVar) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(EmployeeEntity.class, "emp");
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		if (sortVar != null && sortVar != "") {
			String[] sortInfo = sortVar.split(" ");
			String sortOrder = sortInfo[1];
			if (sortOrder.equalsIgnoreCase("ASC")) {
				criteria.addOrder(Order.asc(sortInfo[0]));
			} else {
				criteria.addOrder(Order.desc(sortInfo[0]));
			}
		}
		return criteria.list();
	}

	@Override
	public int getAllEmployeeCount() {
		int count = ((Long) sessionFactory.getCurrentSession().createQuery("select count(*) from EmployeeEntity").uniqueResult()).intValue();
		return count;
	}

	@Override
	public EmployeeEntity findEmployeeByName(String eName) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(EmployeeEntity.class);
		criteria.add(Restrictions.eq("employeeName", eName));
		EmployeeEntity employeeEntity = (EmployeeEntity) criteria.uniqueResult();
		return employeeEntity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeEntity> fetchEmployeesByKeyWords(String keyword) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(EmployeeEntity.class,"e");
		criteria.add(Restrictions.or(Restrictions.ilike("e.employeeId", keyword, MatchMode.ANYWHERE), Restrictions.ilike("e.employeeName", keyword, MatchMode.START)));
		criteria.addOrder(Order.asc("e.employeeName"));
		criteria.setMaxResults(10);
		List<EmployeeEntity> employees = (List<EmployeeEntity>) criteria.list();
		return employees;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> fetchPracticeHeadsBySkill(String skillId) {
		List<UserEntity> users = null;
		Session session = sessionFactory.getCurrentSession();
		Query namedQuery = session.getNamedQuery("employeeEntity.fetchPracticeHeadsBySkill");
		namedQuery.setParameter("skillId", skillId);
		users = (List<UserEntity>) namedQuery.list();
		return users;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeEntity> fetchAllPracticeHeads(String keyword){
		List<EmployeeEntity> employees = null;
		Session session = sessionFactory.getCurrentSession();
		Query namedQuery = session.getNamedQuery("employeeEntity.fetchAllPracticeHeads");
		namedQuery.setParameter("employeeName", keyword+"%");
		namedQuery.setParameter("employeeId", "%"+keyword+"%");
		employees = (List<EmployeeEntity>) namedQuery.list();
		return employees;
	}
}
