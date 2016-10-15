/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.parsers;

import java.io.InputStream;
import java.util.List;

import com.kishore.confmanagement.common.loggers.BaseLogger;
import com.kishore.confmanagement.core.vos.talks.ConferenceTalk;

/**
 * The parser class parses the list of {@link ConferenceTalk} given document.
 * Document can be from any source be it XML, JSON
 * 
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public interface DocumentProcessingParser {
  /**
   * The logger instance.
   */
  final public BaseLogger LOGGER = BaseLogger.getInstance();

  /**
   * To parse the list of {@link ConferenceTalk} given document.Document can be
   * of any source.
   * 
   * @param stream The input stream
   * @return The list of {@link ConferenceTalk} parsed.
   * @throws The exception com.kishore.confmanagement.core.exceptions.
   * ParsingException when some error occurs.
   */
  public List<ConferenceTalk> parseListOfTalks(InputStream stream);

}
