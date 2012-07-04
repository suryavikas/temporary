/**
 * 
 */
package com.mckinsey.coding.session.enums;

/**
 * @author surya
 *
 * Jul 3, 2012 
 */
public enum DiscountTypes {
	EMPLOYEE(30),AFFILIATE(10),CUSTOMER(5), NOTAPPLICABLE(0);
	
	private final int discountAmount;

	private DiscountTypes(int discountAmount) {
		this.discountAmount = discountAmount;
	}
	public int getDiscountAmount() {
		return discountAmount;
	}
}
