package com.mckinsey.coding.session.model;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.mckinsey.coding.session.enums.ProductTypes;

public class ProductsTest {

	private Products product;

	@BeforeClass
	public void beforeClass() {
		product = new Products();
		product.setName("T-Shirt");
		product.setProductType(ProductTypes.GROCERIES);
	}

	@AfterClass
	public void afterClass() {
	}

	@Test
	public void isDiscountable() {
		assertEquals(product.isDiscountable(), false);
	}

	@Test
	public void isNotDiscountable() {
		product.setProductType(ProductTypes.OTHERS);
		assertEquals(product.isDiscountable(), true);
	}
}
