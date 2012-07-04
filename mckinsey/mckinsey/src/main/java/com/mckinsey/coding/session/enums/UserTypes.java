/**
 * 
 */
package com.mckinsey.coding.session.enums;

/**
 * @author surya
 *
 * Jul 3, 2012 
 */
public enum UserTypes {

	EMPLOYEE(1),AFFILIATE(2),CUSTOMER(3);
	
	private final int userTypeId;

	private UserTypes(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	public int getUserTypesId() {
		return userTypeId;
	}
}
