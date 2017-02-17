package System;



public class StringAppendTest {

	public static void main(String[] args) {
		String type = "daily";
		String id = "sdadscsefaaa";
		//<a href="report/downloadReport/daily?id=15">下载15</a>
		String a = "<a href=\"report/downloadReport/"+type+"?id="+id+"\">下载</a>"; 
		System.out.println(a);
	}

}
