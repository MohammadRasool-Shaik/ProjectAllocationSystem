/**
 * 
 */
package org.rash.projectallocationsystem.Dto.batch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.rash.projectallocationsystem.Dto.StatusDto;
import org.rash.projectallocationsystem.Dto.UserDto;

/**
 * @author Ammi
 * 
 */
public class UserListDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<UserDto> users = new ArrayList<UserDto>();

	private StatusDto statusDto;

	/**
	 * 
	 */
	public UserListDto() {
	}

	/**
	 * @return the statusDto
	 */
	public StatusDto getStatusDto() {
		return statusDto;
	}

	/**
	 * @param statusDto
	 *            the statusDto to set
	 */
	public void setStatusDto(StatusDto statusDto) {
		this.statusDto = statusDto;
	}

	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}

}
