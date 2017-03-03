package System;

import java.util.ArrayList;

import org.junit.Test;

import com.sun.org.apache.xalan.internal.xsltc.trax.SAX2DOM;

public class StringUtils {

	@Test
	public void toArray() {
		String s = "8,2,4,2";
		String[] ints = s.split(",");
		StringBuffer sb = new StringBuffer();

		for (String _int : ints) {
			sb.append(_int+",");
		}
		String s2 = sb.toString();
		System.out.println(s2.substring(0,s2.length()-1));
		System.out.println(s2.substring(s2.lastIndexOf(",")));
	}
	
	@Test
	public void testSubSting(){
		String string = "2017-02-24_日报表_刘良.docx";
		int year = Integer.parseInt(string.substring(0,4));
		int month = Integer.parseInt(string.substring(5,7));
		int day = Integer.parseInt(string.substring(8,10));
		String string2 = year+"-"+month+"-"+day;
		System.out.println(string2);
	}
	
	@Test
	public void list(){
		int[] a1 = {1,4,8,20};
		int[] a2 = {3,4,5,20};
		int[] a3 = {1,4,8,10};
		int[] a4 = {5,7,9,14};
		
		ArrayList<Object> month = new ArrayList<>();
		month.add(a1);
		month.add(a2);
		month.add(a3);
		month.add(a4);
		
		ArrayList<Object> year = new ArrayList<>();
	}
}
