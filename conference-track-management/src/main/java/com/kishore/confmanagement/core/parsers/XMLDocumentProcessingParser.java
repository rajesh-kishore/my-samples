/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.parsers;

import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.kishore.confmanagement.core.exceptions.ParsingException;
import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;
import com.kishore.confmanagement.core.vos.talks.ConferenceTalks;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class XMLDocumentProcessingParser implements
    DocumentProcessingParser {

  /**
   * The jaxb context instance.
   */
  private static final JAXBContext jaxbContext;

  static {
    try {
      jaxbContext = JAXBContext
          .newInstance(ConferenceTalks.class);
    } catch (JAXBException e) {
      LOGGER.logSevere("Error occurred while getting jaxb instance", e);
      throw new ParsingException(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kishore.confmanagement.core.parsers.DocumentProcessingParser#
   * parseListOfTalks(java.io.InputStream)
   */
  @Override
  public List<ConferenceTalk> parseListOfTalks(InputStream stream) {

    /*
     * SchemaFactory factory =
     * SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); Schema
     * schema; try { schema = factory
     * .newSchema(this.getClass().getClassLoader()
     * .getResource("conference-talk.xsd")); } catch (SAXException e) {
     * LOGGER.logSevere("Error in schema loding", e); throw new
     * ConferenceTalkParsingException(e); }
     * 
     * Validator validator = schema.newValidator(); try { validator.validate(new
     * StreamSource(stream)); LOGGER.logInfo("validation successfull", null); }
     * catch (SAXException e) { LOGGER.logSevere("Error in validating schema",
     * e); throw new ConferenceTalkParsingException(e); } catch (IOException e)
     * { LOGGER.logSevere("Error in validating schema", e); throw new
     * ConferenceTalkParsingException(e); }
     */
    Unmarshaller unmarshaller = null;
    try {
      unmarshaller = jaxbContext.createUnmarshaller();
    } catch (JAXBException e) {
      LOGGER.logSevere("Error occurred while parsing", e);
      throw new ParsingException(e);
    }

    ConferenceTalks jaxbConferenceTalk = null;

    try {
      jaxbConferenceTalk =
          (ConferenceTalks) unmarshaller.unmarshal(stream);
    } catch (JAXBException e) {
      LOGGER.logSevere("Error occurred while parsing", e);
      throw new ParsingException(e);
    }
    return jaxbConferenceTalk.getConferenctalkLst();
  }

}
