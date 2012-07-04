/**
 * 
 */
package com.mckinsey.coding.session.enums;

/**
 * @author surya
 *
 * Jul 3, 2012 
 */
public enum ProductTypes {
	GROCERIES(1), OTHERS(2);
	private final int productTypeId;

	private ProductTypes(int productTypeId) {
		this.productTypeId = productTypeId;
	}
	public int getProductTypeId() {
		return productTypeId;
	}
}
