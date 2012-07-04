package com.mckinsey.coding.session.model;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.mckinsey.coding.session.enums.ProductTypes;
import com.mckinsey.coding.session.enums.UserTypes;

public class BillingTest {
	
	private User user;
	private List<Products> productList;
	@BeforeClass
	public void beforeClass() {
		user = new User();
		user.setFirstName("Abc");
		user.setLastName("Pqr");
		user.setAge(20);
		user.setDateJoined(Calendar.getInstance());		
		//Setting up producs
		productList = new ArrayList<Products>();
//		Products product1 = new Products();
//		product1.setName("Tea");
//		product1.setProductType(ProductTypes.GROCERIES);
//		product1.setPrice(100.0);
//		productList.add(product1);
//		Products product2 = new Products();
//		product2.setName("Coffee");
//		product2.setProductType(ProductTypes.GROCERIES);
//		product2.setPrice(200.99);
//		
//		Products product3 = new Products();
//		product3.setName("T-Shirt");
//		product3.setProductType(ProductTypes.OTHERS);
//		product3.setPrice(10.0);
		
	}

	@AfterClass
	public void afterClass() {
	}
	
	@DataProvider(name = "CalculateDiscount")
	public Object[][] calculateDiscount(Method m) {
		Calendar cl = Calendar.getInstance();
		return new Object[][] {
				{2010, 01,  UserTypes.CUSTOMER, ProductTypes.GROCERIES, 10.00, 0 },
				{2010, 01,  UserTypes.CUSTOMER, ProductTypes.OTHERS, 10.00, 0.5 },
				{2012, 01,  UserTypes.CUSTOMER, ProductTypes.OTHERS, 10.00, 0 },
				{2012, 01,  UserTypes.EMPLOYEE, ProductTypes.OTHERS, 10.00, 3.0 },
				{2012, 01,  UserTypes.EMPLOYEE, ProductTypes.GROCERIES, 10.00, 0.0 },
				{2012, 01,  UserTypes.AFFILIATE, ProductTypes.OTHERS, 10.00, 1.0 },
				{2012, 01,  UserTypes.AFFILIATE, ProductTypes.GROCERIES, 10.00, 0.0 }
				};
	}

	@Test(dataProvider = "CalculateDiscount")
	public void calculateDiscount(int year, int month, UserTypes userType, ProductTypes productType, double price, double expectedResult){
		Billing billing = new Billing();
		productList.clear();
		//Overriding user joining date
		Calendar cl = Calendar.getInstance();
		cl.set(year, month, 01);
		user.setDateJoined(cl);
		user.setUserType(userType);
		billing.setUser(user);
		//Setting products
		Products product3 = new Products();
		product3.setName("Dummy");
		product3.setProductType(productType);
		product3.setPrice(price);
		productList.add(product3);
		billing.setProductList(productList);
		billing.calculateDiscount();
		System.out.println(billing.getDiscountAmount());		
		assertEquals(billing.getDiscountAmount(), expectedResult, 0);
		
	}	
	
	@DataProvider(name = "totalFlatDiscount")
	public Object[][] totalDiscount(Method m) {
		Calendar cl = Calendar.getInstance();
		return new Object[][] {
				{2010, 01,  UserTypes.CUSTOMER, ProductTypes.GROCERIES, 10.00, ProductTypes.OTHERS, 10.00, 19.5 },
				{2011, 01,  UserTypes.CUSTOMER, ProductTypes.GROCERIES, 10.00, ProductTypes.OTHERS, 10.00, 20.0 },
				{2010, 01,  UserTypes.CUSTOMER, ProductTypes.GROCERIES, 100.00, ProductTypes.OTHERS, 200.00, 280.0 },
				{2010, 01,  UserTypes.EMPLOYEE, ProductTypes.GROCERIES, 10.00, ProductTypes.OTHERS, 10.00, 17.0 },
				{2010, 01,  UserTypes.EMPLOYEE, ProductTypes.GROCERIES, 100.00, ProductTypes.OTHERS, 200.00, 230.0 },
				{2010, 01,  UserTypes.AFFILIATE, ProductTypes.GROCERIES, 10.00, ProductTypes.OTHERS, 10.00, 19.0 },
				{2010, 01,  UserTypes.AFFILIATE, ProductTypes.GROCERIES, 100.00, ProductTypes.OTHERS, 200.00, 270.0 },	
				};
	}
	@Test(dataProvider = "totalFlatDiscount")
	public void generateBill(int year, int month, UserTypes userType, ProductTypes productType1, double price1,ProductTypes productType2, double price2, double expectedResult) {
		Billing billing = new Billing();
		productList.clear();
		//Overriding user joining date
		Calendar cl = Calendar.getInstance();
		cl.set(year, month, 01);
		user.setDateJoined(cl);
		user.setUserType(userType);		
		billing.setUser(user);
		
		//Setting products
		Products product1 = new Products();
		product1.setName("Dummy1");
		product1.setProductType(productType1);
		product1.setPrice(price1);
		productList.add(product1);
		
		Products product2 = new Products();
		product2.setName("Dummy2");
		product2.setProductType(productType2);
		product2.setPrice(price2);
		productList.add(product2);
		billing.setProductList(productList);
		billing.calculateDiscount();
		billing.generateBill();
				
		assertEquals(billing.getNetPayable(), expectedResult, 0);
	}
}
