/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.confmanagement.core.factories;

import java.util.HashMap;
import java.util.Map;

import com.kishore.confmanagement.core.parsers.DocumentProcessingParser;
import com.kishore.confmanagement.core.parsers.XMLDocumentProcessingParser;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class DocumentParserFlyWeightFactory {

  /**
   * The parser constant.
   * 
   * @author Rajesh Kishore
   * @version 1.0
   * @since V1
   */
  public static enum PARSERTYPE {
    XML
  };

  /**
   * the map containing parsers.
   */
  static Map<PARSERTYPE, DocumentProcessingParser> parserTypeByDocumentParser =
      new HashMap<PARSERTYPE, DocumentProcessingParser>(4);

  /**
   * Returns The document parser. If no parser found then returns
   * {@link XMLDocumentProcessingParser}.
   * 
   * @param type The parser type.
   * @return The document parser.
   */
  public static DocumentProcessingParser getParserInstance(PARSERTYPE type) {

    if (parserTypeByDocumentParser.containsKey(type)) {
      return parserTypeByDocumentParser.get(type);
    }

    if (type == PARSERTYPE.XML) {
      parserTypeByDocumentParser.put(PARSERTYPE.XML,
          new XMLDocumentProcessingParser());
    }

    return parserTypeByDocumentParser.get(PARSERTYPE.XML);

  }
}
