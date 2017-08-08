package org.rash.projectallocationsystem.enumerator;

/**
 * 
 * @author rasool.shaik
 * 
 */
public enum StatusCodeEnum {
	FAILURE(0, "Failure"), SUCCESS(1, "Success"),
	PASSWORDCHANGESUCCESS(100, "Your password successfully changed, you will get temporary password to your registred mail"),
	PASSWORDCHANGEFAILURE(408, "Your password successfully changed, you will get temporary password to your registred mail"),
	CREATEACCOUNTFAILUREMESSAGEUSER(601,"Sorry! Username not available. Choose Different User Name"),
	CREATEACCOUNTFAILUREMESSAGEEMAIL(602,"This email id already exists. Please enter a new one"),
	LOGINUSERDOESTEXSIST(603,"Invalid username/password. Please try again."),
	USERDETAILUSERIDNOTPRESENT(604	,"Sorry! user id doesn't exsist"),
	USERFORGOTPASSWORDEXCEPTIONMESSAGE(605,	"Sorry! unable to send password. Please try again later"),
	USERFORGOTPASSWORDEMAILERROR (606,	"Email id not registered"),
	USERCHANGEPASSWORDWRONGMESSAGE(607,	"Password does not match"),
	USERCANNOTDELETEHIMSELF (608,	"You can not delete yourself"),
	OK(200,"OK"),
	ERROR(500,"ERROR");
	
	

	private int value;
	private String msg;

	StatusCodeEnum(int value, String msg) {
		this.value = value;
		this.msg = msg;
	}

	public int getValue() {
		return value;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
