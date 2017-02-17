package com.midnight.reportsys.util;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonFloatValueProcessor implements JsonValueProcessor {
	
	
	 public JsonFloatValueProcessor() { }  
	  
	    /** 
	     * ������������ 
	     */  
	    public Object processArrayValue(Object value, JsonConfig jsonConfig) {  
	          
	        if (value instanceof float[]) {  
	              
	            String[] obj = {};  
	              
	            float[] nums = (float[]) value;  
	              
	            for (int i = 0; i < nums.length; i++) {  
	                obj[i] = roundHalfUp(nums[i], 3);  
	            }  
	              
	            return obj;  
	        }  
	          
	        return value;  
	    }  
	  
	    /** 
	     * ���?������ 
	     */  
	    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {  
	          
	        if (value instanceof Float) {  
	            return roundHalfUp((Float)value, 3);  
	        }  
	          
	        return value;  
	    }  
	      
	    /** 
	     * �������롣 
	     *  
	     * @param number ��ֵ 
	     * @return ��������ֵ 
	     * @see java.text.RoundingMode.HALF_UP 
	     */  
	    public String roundHalfUp(double number, int frac) {  
	        NumberFormat fmt = NumberFormat.getInstance(Locale.CHINESE);  
	        fmt.setGroupingUsed(false); 
	        fmt.setMaximumFractionDigits(frac);  
	        fmt.setRoundingMode(RoundingMode.HALF_UP);  
	          
	        return fmt.format(number);  
	    }  
	
	
	
	
	

}
