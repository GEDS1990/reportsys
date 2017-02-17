package System;

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
}
