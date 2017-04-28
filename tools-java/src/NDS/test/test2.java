package NDS.test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test2 {
	public static void main(String[] args){
//		double a=1232132.12;
//		int i=(int) Math.floor(a);
//		System.out.println(i);
		testzhengze();
	}
	
	public static void testzhengze(){
		// 匹配月份日期
		ArrayList<String> a= new ArrayList<String>();
		a.add("[[[(t1)]+[(t7)]]*[(h8m30){h16m30}]]");
		a.add("[[[(t1){t7}]+[(t7)]]*[(h8m30){h16m30}]]");
		a.add("[[[(t1)]+[(t7){t7}]]*[(h8m30){h16m30}]]");
		a.add("[[[(t1){t7}]+[(t7){t7}]]*[(h8m30){h16m30}]]");
		a.add("[[[(t1)]+[(t7)]]*[[(h8m30){h16m30}]+[(h8m30){h16m30}]+[(h8m30){h16m30}]+[(h8m30){h16m30}]]]");
		a.add("[[[(t1){t7}]+[(t7){t7}]]*[[(h8m30){h16m30}]+[(h8m30){h16m30}]+[(h8m30){h16m30}]+[(h8m30){h16m30}]]]");
		a.add("[[[(t1){t7}]+[(t7)]]*[[(h8m30){h16m30}]+[(h8m30){h16m30}]+[(h8m30){h16m30}]+[(h8m30){h16m30}]]]");
		String patternString = "(\\[\\([^)]+\\)\\])|(\\[\\([^)]+\\)\\{[^}]+\\}\\])";
		String result="";
		Pattern p = Pattern.compile(patternString);
		for(int i=0; i<7;i++){
			String timezone=a.get(i);
			Matcher m = p.matcher(timezone);
			while (m.find()) {
				String subStr = m.group(0);
				result+=subStr+"====";
			}
			result+="\n";
		}
		
		System.out.println(result);
	}
}
