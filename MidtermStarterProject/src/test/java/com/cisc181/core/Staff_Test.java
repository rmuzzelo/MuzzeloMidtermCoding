package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import com.cisc181.eNums.eTitle;

public class Staff_Test {
	private ArrayList<Staff> StaffList;


	@Test
	public void AverageSalaryTest() throws PersonException{
		//Creates an ArrayList of Staff class
		StaffList = new ArrayList<Staff>();
		Calendar calendar = Calendar.getInstance();
	    calendar.set(1996, 2, 10, 12, 0, 0);
	    Date birthdate = calendar.getTime();
	    //Adds 5 sample Staff instances to the Array list with different salaries
		Staff s1 = new Staff("John", "Walter", "Smith", birthdate, "123 Test Drive", "(123) 456-7890", "test@gmail.com", "1-3pm", 3, 2000, new Date(), eTitle.ANALYST);
		Staff s2 = new Staff("John", "Walter", "Smith", birthdate, "123 Test Drive", "(123) 456-7890", "test@gmail.com", "1-3pm", 3, 5000, new Date(), eTitle.ANALYST);
		Staff s3 = new Staff("John", "Walter", "Smith", birthdate, "123 Test Drive", "(123) 456-7890", "test@gmail.com", "1-3pm", 3, 10000, new Date(), eTitle.ANALYST);
		Staff s4 = new Staff("John", "Walter", "Smith", birthdate, "123 Test Drive", "(123) 456-7890", "test@gmail.com", "1-3pm", 3, 25000, new Date(), eTitle.ANALYST);
		Staff s5 = new Staff("John", "Walter", "Smith", birthdate, "123 Test Drive", "(123) 456-7890", "test@gmail.com", "1-3pm", 3, 12000, new Date(), eTitle.ANALYST);
		StaffList.add(s1);
		StaffList.add(s2);
		StaffList.add(s3);
		StaffList.add(s4);
		StaffList.add(s5);
		//Computes average of the salaries and tests with assertEquals
		double sumsalary=0;
		for (int i=0; i < StaffList.size(); i++){
			double salary = StaffList.get(i).getSalary();
			sumsalary = salary + sumsalary;
		}
		double average = sumsalary/5;
		assertEquals(10800,average,0);
				
	}
	

	
	@Test
	public void Invalid_DOB_Test(){
		Calendar calendar = Calendar.getInstance();
	    calendar.set(1886, 2, 10, 12, 0, 0);
	    Date birthdate = calendar.getTime();
		try {
			new Staff("John", "Walter", "Smith", birthdate, "123 Test Drive", "(123) 456-7890", "test@gmail.com", "1-3pm", 3, 2000, new Date(), eTitle.ANALYST);
		} catch (PersonException e) {
			System.out.print("Invalid DOB");
		}	
		
	}
	
//Tests whether exception was thrown with invalid DOB
	@Test (expected = PersonException.class)
	public void Invalid_DOB_Test2() throws PersonException{
		Calendar calendar = Calendar.getInstance();
	    calendar.set(1888, 2, 10, 12, 0, 0);
	    Date birthdate = calendar.getTime();
		new Staff("John", "Walter", "Smith", birthdate, "123 Test Drive", "(123) 456-7890", "test@gmail.com", "1-3pm", 3, 2000, new Date(), eTitle.ANALYST);
	}	
		
	
//Tests whether exception was thrown with invalid phone number
	@Test (expected = PersonException.class)
	public void Invalid_PhoneNum_Test() throws PersonException {
		Calendar calendar = Calendar.getInstance();
	    calendar.set(1996, 2, 10, 12, 0, 0);
	    Date birthdate = calendar.getTime();
		new Staff("Sally", "Beth", "Smith", birthdate, "123 Test Drive", "456-7890", "test@gmail.com", "1-3pm", 3, 2000, new Date(), eTitle.ANALYST);
	}
	
}
