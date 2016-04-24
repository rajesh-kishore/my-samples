/**
 * 
 */
package com.kishore.xmlprocessor;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import com.kishore.xmlprocessor.exceptions.XmlProcessingException;

/**
 * The class is acting as a engine to process the xmlString and convert this to JAXB object
 * @author Rajesh Kishore
 * @version 1.0
 * @since Release1
 */
final public class XmlProcessingEngine {
	
	
	
	
	/**
	 * The private constructor
	 */
	private XmlProcessingEngine() {
		
	}
	
	/**
	 * The private inner class acting as a helper to create the singleton 
	 * @since Release1
	 */
	private static class XmlProcessingEngineSingletonCreator {
		/**
		 *  The static private xmlProcessingEngine
		 */
		private static XmlProcessingEngine xmlProcessingEngine = new XmlProcessingEngine();
	}
	
	
	/**
	 * The utility method to return the singleton instance of @see {@link com.kishore.xmlprocessor.XmlProcessingEngine} 
	 * @return The singleton XmlProcessingEngine
	 */
	public static XmlProcessingEngine getInstance() {
		
		return XmlProcessingEngineSingletonCreator.xmlProcessingEngine;
	}
	
	
	/**
	 * The api validates a given string conforms to schema
	 * @param xmlString The xml string which needs to be validated
	 * @return when the validation passes it returns @see {@link java.lang.Boolean#TRUE} 
	 */
	public Boolean validateXml(String xmlString) {
		
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema;
		try {
			schema = factory.newSchema(this.getClass().getResource("/schemas/student.xsd"));
		} catch (SAXException e) {
			throw new XmlProcessingException("Error in loading schema");
		}
		
		Validator validator = schema.newValidator();
		try {
			validator.validate(new StreamSource(new java.io.StringReader(xmlString)));
		} catch (SAXException e) {
			e.printStackTrace();
			throw new XmlProcessingException("Error in validating schema");
		} catch (IOException e) {
			e.printStackTrace();
			throw new XmlProcessingException("Error in validating schema");
		}

		return Boolean.TRUE;
	}
	
	
	/**
	 * The api is to convert xml string to JAXB Object
	 * @param xmlString The xml string which needs to be converted to JAXB object
	 * @return "com.kishore.vos.Student jaxb object"
	 * @throws when any error occurs during schema validation or JAXB object conversion, following exception is thrown @see {@link com.kishore.xmlprocessor.exceptions.XmlProcessingException}  
	 */
	public com.kishore.vos.Student processXml(String xmlString) {
		
		JAXBElement<com.kishore.vos.Student> jaxBElementStudent = null;

		com.kishore.vos.Student student = null;
		validateXml(xmlString);
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance("com.kishore.vos");
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new XmlProcessingException("Error in creating JaxbInstance");
		}
		Unmarshaller unmarshaller = null;
		try {
			unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new XmlProcessingException("Error in creating UnMarshaller");
		}

		StringReader reader = new StringReader(xmlString);
		try {
			jaxBElementStudent = (JAXBElement<com.kishore.vos.Student>) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new XmlProcessingException("Error in creating UnMarshaller");
		}

		student = jaxBElementStudent.getValue();
		
		return student;
	}
	

}
