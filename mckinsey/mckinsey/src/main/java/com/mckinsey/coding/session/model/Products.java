/**
 * 
 */
package com.mckinsey.coding.session.model;

import com.mckinsey.coding.session.enums.DiscountTypes;
import com.mckinsey.coding.session.enums.ProductTypes;

/**
 * @author surya
 * 
 *         Jul 3, 2012
 */
public class Products {

	private String name;
	private double price;
	private float weight;
	private ProductTypes productType;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double d) {
		this.price = d;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public ProductTypes getProductType() {
		return productType;
	}

	public void setProductType(ProductTypes productType) {
		this.productType = productType;
	}	

	public boolean isDiscountable() {
		boolean status = true;
		if (productType.getProductTypeId() == ProductTypes.GROCERIES
				.getProductTypeId()) {
			status = false;
		}
		return status;
	}

}
