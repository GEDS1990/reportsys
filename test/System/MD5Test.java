package System;

import org.junit.Test;
import org.apache.commons.codec.digest.DigestUtils;


public class MD5Test {

	@Test
	public void md5test(){
		System.out.println(DigestUtils.md5Hex("123456"));
	
	}
}
