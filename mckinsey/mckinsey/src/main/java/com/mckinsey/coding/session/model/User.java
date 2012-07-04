/**
 * 
 */
package com.mckinsey.coding.session.model;

import java.util.Calendar;
import java.util.Date;

import com.mckinsey.coding.session.enums.DiscountTypes;
import com.mckinsey.coding.session.enums.UserTypes;

/**
 * @author surya
 * 
 *         Jul 3, 2012
 */
public class User {

	private String firstName;
	private String lastName;
	private int age;
	private Calendar dateJoined;
	private UserTypes userType;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Calendar getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(Calendar dateJoined) {
		if(dateJoined == null){
			this.dateJoined = Calendar.getInstance();
		}else if(dateJoined.after(Calendar.getInstance())){
			this.dateJoined = Calendar.getInstance();
		}else {
			this.dateJoined = dateJoined;
		}
	}

	public UserTypes getUserType() {
		return userType;
	}

	public void setUserType(UserTypes userType) {
		this.userType = userType;
	}

	public long getTenure() {
		long days = 0;
		if (dateJoined == null) {
			System.out.println("Date joined input not provided");
			return -1;
		} else {			
			Calendar currentDate = Calendar.getInstance();
			 long c1 = currentDate.getTimeInMillis();
			 long c2 = dateJoined.getTimeInMillis();
			 long diff = c1 - c2;
			 days = diff / (24 * 60 * 60 * 1000);
		}
		return days;
	}

	public boolean isDiscountApplicable() {
		boolean status = false;
		if (this.getTenure() >= 730) {
			status = true;
		}
		return status;
	}

}
