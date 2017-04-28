package NDS;
/**
 * 
 * @ClassName: multilmeshnode_roadcompare
 * @Description: 用于比较两个字符串，使用了loop方法
 * @author wb-lzg228465
 * @version 
 * 创建时间：2016年9月29日 下午5:23:31 
 * 程序的简单说明
 */
public class multilmeshnode_roadcompare {
	public static void main(String[] args) {
		String aftercomper="84,45==51588,9650==600016,600053==600055,600007==51648,600012==21,200==51651,600027==17,37==51650,600029==600042,600047==600009,600013==105,23==";
		String nds="ADF_ID==600042,600047==600016,600053==600009,600013==600055,600007==105,23==84,45==17,37==51648,600012==51650,600029==51651,600027==21,200==51588,9650==";
		String[] A=aftercomper.split("==");
		String[] B=nds.split("==");
		
		String m= loop(A,B);
		System.out.println(m);
		
	}
	
	public static String loop(String[] arrf,String[] arrt ){
		String m="";
		String mm="";
		String ml="";
		String n="";
		int i=0;
		int j=0;
		int a=0;
		int c=0;
		boolean b=false;
		if(arrf.length ==arrt.length ){
			a=0;
			c=0;
		}
		else if(arrf.length<arrt.length){
			c=arrt.length - arrf.length;
		}
		else if(arrt.length <arrf.length ){
			a=arrf.length - arrt.length;
		}
		for(String f:arrf){
			for(String t:arrt){
				b=false;
				if(t.equals(f)){
					b=true;
					break;
				}
			}
			if(!b){
				n="";
				mm=mm+n+f+";\r\n";
				i++;
			}
		}
		if(0!=i){
			mm="A较B多"+String.valueOf(i)+"条数据,A较B多的数据（nds跨图幅数据中没有，但在adfp中未匹配上）：\r\n"+mm;
		}
		for(String t:arrt){
			for(String f:arrf){
				b=false;
				if(f.equals(t)){
					b=true;
					break;
				}
			}
			if(!b){
				n="";
				j++;
				ml=ml+n+t+";\r\n";
			}
		}
		if(0!=j){
			ml="B较A多"+String.valueOf(j)+"条数据,B较A多的数据(在adfp中匹配上的跨图幅nds数据)：\r\n"+ml;
		}
		m=mm+ml;
		if(m.length()<4){
			m="A,B相同";
		}
		else if(0!=a){
			m="总数上A比B多"+a+"条数据\r\n"+m;
		}
		else if(0!=c){
			m="总数上B比A多"+c+"条数据\r\n"+m;
		}
		return m;
	}
}
