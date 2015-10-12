package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eMajor;

public class Student_Test {
	static ArrayList<Course> CourseList;
	static ArrayList<Semester> SemesterList;
	static ArrayList<Section> SectionList;
	static ArrayList<Student> StudentList; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		CourseList = new ArrayList<Course>();
		SemesterList = new ArrayList<Semester>();
		SectionList = new ArrayList<Section>();
		//Adds three course records and adds them to CourseList
		UUID course1UUID = UUID.randomUUID();
		UUID course2UUID = UUID.randomUUID();
		UUID course3UUID = UUID.randomUUID();
		Course c1 = new Course(course1UUID,"Chem111",3,eMajor.CHEM);
		Course c2 = new Course(course2UUID,"Cisc181",3,eMajor.COMPSI);
		Course c3 = new Course(course3UUID,"Phys208",3,eMajor.PHYSICS);
		CourseList.add(c1);
		CourseList.add(c2);
		CourseList.add(c3);
		//Adds two semesters and adds them to Semester List
		UUID fallID = UUID.randomUUID();
		UUID springID = UUID.randomUUID();
		Semester fall = new Semester(fallID, new Date(100000L*1000), new Date());
		Semester spring = new Semester(springID,new Date(500000L*1000), new Date(2500000L*1000));
		SemesterList.add(fall);
		SemesterList.add(spring);
		
		//Adds two Sections for each course and semester and adds them to SectionList
		for(int i=0;i<CourseList.size();i++){
			for(int j=0; j < SemesterList.size(); j++){
				UUID sectionID = UUID.randomUUID();
				Section section = new Section(CourseList.get(i).getCourseID(), 
						SemesterList.get(j).getSemesterID(), sectionID);
				SectionList.add(section);
			}
		}
		//Creates ten student records and adds them to StudentList
		StudentList = new ArrayList<Student>();
		Student student1 = new Student("Sally","Beth","Smith", new Date(), eMajor.NURSING, 
				"123 Test Ln", "(123) 456-7890","test@gmail.com");
		Student student2 = new Student("Jane","Beth","Smith", new Date(), eMajor.BUSINESS, 
				"123 Test Ln", "(123) 456-7890","test@gmail.com");
		Student student3 = new Student("John","Bill","White", new Date(), eMajor.CHEM, 
				"123 Test Ln", "(123) 456-7890","test@gmail.com");
		Student student4 = new Student("Jeff","Will","Black", new Date(), eMajor.COMPSI, 
				"123 Test Ln", "(123) 456-7890","test@gmail.com");
		Student student5 = new Student("Jane","Jill","Jones", new Date(), eMajor.PHYSICS, 
				"123 Test Ln", "(123) 456-7890","test@gmail.com");
		Student student6 = new Student("Sally","Beth","Smith", new Date(), eMajor.PHYSICS, 
				"123 Test Ln", "(123) 456-7890","test@gmail.com");
		Student student7 = new Student("Jane","Beth","Smith", new Date(), eMajor.COMPSI, 
				"123 Test Ln", "(123) 456-7890","test@gmail.com");
		Student student8 = new Student("John","Bill","White", new Date(), eMajor.CHEM, 
				"123 Test Ln", "(123) 456-7890","test@gmail.com");
		Student student9 = new Student("Jeff","Will","Black", new Date(), eMajor.BUSINESS, 
				"123 Test Ln", "(123) 456-7890","test@gmail.com");
		Student student10 = new Student("Jane","Jill","Jones", new Date(), eMajor.NURSING, 
				"123 Test Ln", "(123) 456-7890","test@gmail.com");
		StudentList.add(student1);
		StudentList.add(student2);
		StudentList.add(student3);
		StudentList.add(student4);
		StudentList.add(student5);
		StudentList.add(student6);
		StudentList.add(student7);
		StudentList.add(student8);
		StudentList.add(student9);
		StudentList.add(student10);
		
	}

	@Test
	public void TestGPAandCourseAverage() {
		
		ArrayList<UUID> studentidlist = new ArrayList<UUID>();
		ArrayList<Enrollment> EnrollmentList= new ArrayList<Enrollment>();
		//Enrolls ten students in each of the created sections
		for(Student student:StudentList){
			UUID studentid = UUID.randomUUID();
			studentidlist.add(studentid);
			for(Section section:SectionList){
				Enrollment enrollment = new Enrollment(studentid, section.getSectionID());
				EnrollmentList.add(enrollment);	
			}
		
		}
		assertTrue("Method didnt't work", EnrollmentList.size() == 60);
		//Set grade for all of Student1
		EnrollmentList.get(0).SetGrade(4.00);
		EnrollmentList.get(1).SetGrade(3.00);
		EnrollmentList.get(2).SetGrade(3.00);
		EnrollmentList.get(3).SetGrade(4.00);
		EnrollmentList.get(4).SetGrade(2.00);
		EnrollmentList.get(5).SetGrade(4.00);
		
		//Calculating GPA for one of the students
		double totalgradepoints = 0;
		int counter = 0;
		double credits = 0;
		double GPA1;
		//Loop through enrollment list
		for (Enrollment enrollment: EnrollmentList){
			//If the enrollment record has a specific student id get grade for that student
			if (enrollment.getStudentID() == studentidlist.get(0)){
				double grade = enrollment.getGrade();
				credits = 3.00;
				double gradepoints = grade * credits;
				totalgradepoints = gradepoints + totalgradepoints;
				counter = counter + 1;
			}
		}
		//Calculate GPA for the student from the grade total calculated in the loop
		GPA1 = totalgradepoints/(credits*counter);
		assertEquals(GPA1,3.33, 0.01);
		
		//Set grades for one of the sections
		EnrollmentList.get(0).SetGrade(4.00);
		EnrollmentList.get(6).SetGrade(4.00);
		EnrollmentList.get(12).SetGrade(4.00);
		EnrollmentList.get(18).SetGrade(3.00);
		EnrollmentList.get(24).SetGrade(3.00);
		EnrollmentList.get(30).SetGrade(3.00);
		EnrollmentList.get(36).SetGrade(2.00);
		EnrollmentList.get(42).SetGrade(2.00);
		EnrollmentList.get(48).SetGrade(2.00);
		EnrollmentList.get(54).SetGrade(1.00);
	
		double totalsectiongrade = 0;
		int counter2 = 0;
		//Loop through EnrollmentList
		for (Enrollment enrollment: EnrollmentList){
			//If the enrollment record has a specific section id get grade for that Section
			if (enrollment.getSectionID() == SectionList.get(0).getSectionID()){
				double grade = enrollment.getGrade();
				totalsectiongrade = grade + totalsectiongrade;
				counter2 = counter2 + 1;
			}
		}
		//Calculate average for that specific section
		double section1average = totalsectiongrade/counter2;
		assertEquals(section1average,2.80,0);
	}
	
	//Test changing major of one of students records
	@Test
	public void ChangeMajorTest() {
		assertTrue("Method didn't work", StudentList.get(0).getMajor() == eMajor.NURSING);
		StudentList.get(0).setMajor(eMajor.CHEM);
		assertTrue("Method didn't work", StudentList.get(0).getMajor() == eMajor.CHEM);
	}

}
