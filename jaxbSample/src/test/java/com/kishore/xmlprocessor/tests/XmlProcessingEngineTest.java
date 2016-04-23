/**
 * 
 */
package com.kishore.xmlprocessor.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.kishore.xmlprocessor.XmlProcessingEngine;

/**
 * The test class to validate the xml processing logic defined in @see com.kishore.xmlprocessor.XmlProcessingEngine
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
public class XmlProcessingEngineTest {

	/**
	 * Test method for {@link com.kishore.xmlprocessor.XmlProcessingEngine#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		XmlProcessingEngine singletonInstance = XmlProcessingEngine.getInstance();
		XmlProcessingEngine tryAnotherInstance = XmlProcessingEngine.getInstance();
		assertTrue("Singleton violated ",singletonInstance == tryAnotherInstance);
	}

	/**
	 * Test method for {@link com.kishore.xmlprocessor.XmlProcessingEngine#validateXml(java.lang.String)}.
	 */
	@Test
	public void testValidateXml() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<?xml version=\"1.0\"?>");
		stringBuilder.append("<stud:student xmlns:stud=\"http://www.example.org/student\">");
		stringBuilder.append("<stud:studentId>10</stud:studentId>");
		stringBuilder.append("<stud:studentName>Rajesh Kishore</stud:studentName>");
		stringBuilder.append("<stud:course><crs:courseId xmlns:crs=\"http://www.example.org/courses\">100</crs:courseId><crs:courseName xmlns:crs=\"http://www.example.org/courses\">English</crs:courseName></stud:course>");
		stringBuilder.append("<stud:course><crs:courseId xmlns:crs=\"http://www.example.org/courses\">200</crs:courseId><crs:courseName xmlns:crs=\"http://www.example.org/courses\">Maths</crs:courseName></stud:course>");
		stringBuilder.append("</stud:student>");
		assertTrue("Validation did not passed ",Boolean.TRUE == XmlProcessingEngine.getInstance().validateXml(stringBuilder.toString()));
	}
	
	/**
	 * Test method for {@link com.kishore.xmlprocessor.XmlProcessingEngine#processXml(String)}.
	 */
	@Test
	public void testProcessXml() {
		com.kishore.vos.Student student = null;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<?xml version=\"1.0\"?>");
		stringBuilder.append("<stud:student xmlns:stud=\"http://www.example.org/student\">");
		stringBuilder.append("<stud:studentId>10</stud:studentId>");
		stringBuilder.append("<stud:studentName>Rajesh Kishore</stud:studentName>");
		stringBuilder.append("<stud:course><crs:courseId xmlns:crs=\"http://www.example.org/courses\">100</crs:courseId><crs:courseName xmlns:crs=\"http://www.example.org/courses\">English</crs:courseName></stud:course>");
		stringBuilder.append("<stud:course><crs:courseId xmlns:crs=\"http://www.example.org/courses\">200</crs:courseId><crs:courseName xmlns:crs=\"http://www.example.org/courses\">Maths</crs:courseName></stud:course>");
		stringBuilder.append("</stud:student>");
		
		student = XmlProcessingEngine.getInstance().processXml(stringBuilder.toString());
		System.out.println("student is  "+student.getStudentId());
		
		assertTrue("not the correct JaxbObject, studentId is not correct", student != null && "10".equals(student.getStudentId()));
		assertTrue("not the correct JaxbObject, name is not correct", student != null && "Rajesh Kishore".equals(student.getStudentName()));
		assertTrue("not the correct JaxbObject, course size is not correct ", student != null && student.getCourse() != null && student.getCourse().size() == 2);
		assertTrue("not the correct JaxbObject, course(0) id is not correct ", student != null && "100".equals(student.getCourse().get(0).getCourseId()));
		
	}
	

}
