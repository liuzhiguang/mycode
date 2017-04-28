package NDS.test;

import java.io.UnsupportedEncodingException;

public class test20170302 {
	public static void main(String[] args){
		String msg="全国热带作物标准化生产示范园14省级标准化示范园14华南农业大学技术培训基地14山前东坡荔枝园";
		try {
			int len = msg.getBytes("utf-8").length;
			System.out.println("utf-8="+len);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		///
		int staticlen = 0x7F;
		System.out.println("Staticlen="+staticlen);
		boolean b = Namerule("");
		
		System.out.println(b);
	}
	
	public static boolean Namerule(String name){
		try {
			int len = name.getBytes("utf-8").length;
			int staticlen = 0x7F;
			if(len>staticlen){
				return false;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(name.trim().equals("")){
			return false;
		}
		return true;
	}
}
