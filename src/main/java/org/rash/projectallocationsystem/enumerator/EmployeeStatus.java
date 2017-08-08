package org.rash.projectallocationsystem.enumerator;


/**
 * @author rasool.shaik
 * 
 */
public enum EmployeeStatus {
	BILLABLE(0, "Billable"), UNBILLALE(1, "Un Billable"), POOL(2, "Pool");

	private int value;
	private String msg;

	EmployeeStatus(int value, String msg) {
		this.value = value;
		this.msg = msg;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static EmployeeStatus value(int val) {
		for (EmployeeStatus employeeStateEnum : EmployeeStatus.values()) {
			if (employeeStateEnum.value == val) {
				return employeeStateEnum;
			}
		}
		throw new IllegalArgumentException();
	}

}
