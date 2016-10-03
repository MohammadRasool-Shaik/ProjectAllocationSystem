package com.happiestminds.projectallocationsystem.enumerator;

/**
 * @author rasool.shaik
 * 
 */
public enum AllocationStatus {
	A("A", "Allocation"), D("D", "De Allocation"), B("B", "Billable To Non-Billable"), N("N", "Non-Billable To Billable");

	private String key;
	private String description;

	AllocationStatus(String key, String description) {
		this.key = key;
		this.description = description;
	}

	public static AllocationStatus value(String key) {
		for (AllocationStatus employeeStateEnum : AllocationStatus.values()) {
			if (employeeStateEnum.key.equals(key)) {
				return employeeStateEnum;
			}
		}
		throw new IllegalArgumentException();
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

}
