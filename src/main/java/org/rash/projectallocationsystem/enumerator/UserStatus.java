package org.rash.projectallocationsystem.enumerator;


/**
 * @author rasool.shaik
 * 
 */
public enum UserStatus {
	BILLABLE(0, "Locked"), UNBILLALE(1, "Un Locked");

	private int value;
	private String msg;

	UserStatus(int value, String msg) {
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

	public static UserStatus value(int val) {
		for (UserStatus employeeStateEnum : UserStatus.values()) {
			if (employeeStateEnum.value == val) {
				return employeeStateEnum;
			}
		}
		throw new IllegalArgumentException();
	}

}
