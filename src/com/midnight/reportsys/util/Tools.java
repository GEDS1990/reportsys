package com.midnight.reportsys.util;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class Tools {
	
	 public static  JsonConfig getJsonConfig()
	 {
		  JsonConfig jsonConfig = new JsonConfig();
		  jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
		  jsonConfig.registerJsonValueProcessor(Float.class,new JsonFloatValueProcessor());
		  jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);  
		   	
		  return jsonConfig;
	 }


}
