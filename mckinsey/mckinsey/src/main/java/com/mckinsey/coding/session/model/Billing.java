/**
 * 
 */
package com.mckinsey.coding.session.model;

import java.util.Iterator;
import java.util.List;

import com.mckinsey.coding.session.enums.DiscountTypes;
import com.mckinsey.coding.session.enums.UserTypes;

/**
 * @author surya
 * 
 *         Jul 3, 2012
 */
public class Billing {

	private User user;
	private List<Products> productList;
	// For every $100 purchase user gets $5 off.
	private final int FLATDISCOUNT = 5;
	private float disCountableBillAmount;
	private float nonDiscountableBillAmount;
	private float totalAmount;
	private float discountAmount;
	private double netPayable;
	private DiscountTypes discountType = DiscountTypes.NOTAPPLICABLE;

	public User getUser() {
		return user;
	}

	public float getDisCountableBillAmount() {
		return disCountableBillAmount;
	}

	public float getNonDiscountableBillAmount() {
		return nonDiscountableBillAmount;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public float getDiscountAmount() {
		return discountAmount;
	}

	public double getNetPayable() {
		return netPayable;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Products> getProductList() {
		return productList;
	}

	public void setProductList(List<Products> productList) {
		this.productList = productList;
	}

	private int validateInput() {
		int status = -1;
		if (user != null || productList.size() != 0) {
			status = 1;
		}
		return status;
	}

	public void calculateDiscount() {
		if (this.validateInput() == -1) {
			System.out.println("Input parameters not set");
			discountAmount = -1;
		} else {
			this.totalDiscountableAmount();
			if (user.getUserType().getUserTypesId() == UserTypes.EMPLOYEE
					.getUserTypesId()) {
				discountType = DiscountTypes.EMPLOYEE;
			} else if (user.getUserType().getUserTypesId() == UserTypes.AFFILIATE
					.getUserTypesId()) {
				discountType = DiscountTypes.AFFILIATE;
			} else if (user.getUserType().getUserTypesId() == UserTypes.CUSTOMER
					.getUserTypesId()) {
				if (user.isDiscountApplicable()) {
					discountType = DiscountTypes.CUSTOMER;
				}
			} else {
				System.out.println("Invalid User type Passed!!");
			}
			discountAmount = (disCountableBillAmount * discountType
					.getDiscountAmount()) / 100;
		}
	}

	private void totalDiscountableAmount() {
		for (Products product : productList) {
			if (product.isDiscountable()) {
				disCountableBillAmount += product.getPrice();
			} else {
				nonDiscountableBillAmount += product.getPrice();
			}
		}		
	}

	

	private double totalFlatDiscount() {
		double flatDiscount = 0.0;
		if (this.validateInput() == -1) {
			System.out.println("Input parameters not set");
			flatDiscount = -1;
		} else {			
			float totalAmount = nonDiscountableBillAmount
					+ (disCountableBillAmount - this.getDiscountAmount());
			if(totalAmount > 100){
				double counts =  totalAmount / 100;
				int flatIndex = (int) Math.floor(counts);
				flatDiscount = (flatIndex * FLATDISCOUNT);
				
			}
		}
		return flatDiscount;

	}

	public void generateBill() {
		float discounts = this.getDiscountAmount();
		float nonDiscountable = this.getNonDiscountableBillAmount();
		double flatDiscount = this.totalFlatDiscount();
		netPayable = ((disCountableBillAmount - discounts) + nonDiscountable)
				- flatDiscount;
	}

}
