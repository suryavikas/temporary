package com.mckinsey.coding.session.model;

import static org.testng.Assert.assertEquals;

import java.util.Calendar;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

public class UserTest {

	private User user;
	private Calendar cl = Calendar.getInstance();

	@BeforeMethod
	public void beforeMethod() {
		user = new User();
		user.setFirstName("Abc");
		user.setLastName("Pqr");
		user.setAge(20);
		user.setDateJoined(Calendar.getInstance());
	}

	@AfterClass
	public void afterMethod() {
		user = null;
	}

	@Test
	public void getTenureIsZero() {
		long tenure = user.getTenure();
		assertEquals(tenure, 0);
	}

	@Test
	public void getTenureLessThanYear() {		
		cl.set(2012, 02, 03);	
		user.setDateJoined(cl);
		long tenure = user.getTenure();		
		assertEquals(tenure, 122);
	}

	@Test
	public void getTenureWhenMoreThan2yrs() {
		cl.set(2010, 01, 01);	
		user.setDateJoined(cl);
		long tenure = user.getTenure();		
		assertEquals(tenure, 883);
	}
	@Test
	public void getTenureWhenNoJoiningDateProvided() {		
		user.setDateJoined(null);
		long tenure = user.getTenure();		
		assertEquals(tenure, 0);
	}
}
