package org.rash.projectallocationsystem.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author rasool.shaik
 * 
 */
@Entity
@Table(name = "user_groups")
public class UserGroupEntity {
	
	@Id
	@Column(length = 10)
	private String groupId;

	@Column(length = 50)
	private String groupName;

	@OneToMany(mappedBy = "userGroup")
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	@Cascade({CascadeType.ALL})
	private List<UserEntity> users=new ArrayList<UserEntity>();

	/**
	 * 
	 */
	public UserGroupEntity() {
		super();
	}

	/**
	 * @param groupId
	 * @param groupName
	 */
	public UserGroupEntity(String groupId, String groupName) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}



}
