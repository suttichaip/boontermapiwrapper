package com.forth.boonterm.utils;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mvisionmacmini2 on 4/18/2016 AD.
 */
public class JsonUtil {
  protected static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
  public static String toJson(Object o) throws Exception {
    try {
      return new ObjectMapper().writeValueAsString(0);
    } catch (Exception e)
    {
      logger.error("error serialize ["+o+"]", e);
      throw(e);
    }
  }

  public static <T> T fromJson(String json, Class<T> cls) throws Exception  {
    try {
      ObjectMapper result = new ObjectMapper();
      result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      result.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
      return result.readValue(json, cls);
    } catch (Exception e)
    {
      logger.error("error deserialize [json="+json+", class="+cls+"]", e);
      throw(e);
    }
  }
}


