package NDS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import org.geotools.data.shapefile.dbf.DbaseFileReader;
/**
 * 
 * @ClassName: list
 * @Description:取文件夹中的文件列表，用于拉取每日比对的图幅号，计算工作量
 * @author wb-lzg228465
 * @version 
 * 创建时间：2016年9月29日 下午5:22:42 
 * 程序的简单说明
 */
public class list {
	public static void main(String[] args){
		int i=0;
		String[] list;
		String path="E:/CheckDataDir/ActiveData/citys/adfpdispathbymesh/55-HLD/";
		String head="";
		String end="\r\n";
		if(i==0){
			list=GetAllFileNameList(path);
			print(list,head,end);
		}
		else if(i==1){
			
		}
		//comparelongtoshort();
	}
	
	public static String[] GetAllFileNameList(String path){
		File file=new File(path);
		String list[];
		list=file.list();
		return list;
	}
	
	public static void print(String[] list , String head , String end){
		String s="";
		for(int i=0;i<list.length;i++)
		{
			s=s+head+list[i]+end;
		}
		System.out.println(s.trim());
	}
	
	/**
	 * 
	 * @methodsName: 
	 * @Description 比较文件列表中是否有此文件
	 * @author wb-lzg228465
	 * @version 
	 * 创建时间：2016年10月31日 下午3:48:51
	 */
	public static void comparelongtoshort(){

		String c="";
		String uploadFileName="E:/20161020/Export_Output_2.dbf";
		HashMap<String,ArrayList<String>> mapout=new HashMap<String,ArrayList<String>>();
	    try {  
	    	@SuppressWarnings("resource")
			FileInputStream in=new FileInputStream(uploadFileName);  
			DbaseFileReader reader = new DbaseFileReader(in.getChannel(), false, Charset.forName("GBK"));	        try{
	        	while(reader.hasNext()){
	        		Object[] entry = reader.readEntry();
	        		String mesh=entry[3].toString();
	        		String name_chn=entry[4].toString();
	        		if(name_chn.contains("北京")){
	        			name_chn="北京市";
	        		}
	        		if(name_chn.contains("上海")){
	        			name_chn="上海市";
	        		}
	        		if(name_chn.contains("重庆")){
	        			name_chn="重庆市";
	        		}
	        		if(name_chn.contains("天津")){
	        			name_chn="天津市";
	        		}
	        		if(!mapout.containsKey(name_chn)){
	        			mapout.put(name_chn,new ArrayList<String>());
	        		}
	        		mapout.get(name_chn).add(mesh);
	        	}
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	    }  
	     catch (FileNotFoundException e) {  
		        e.printStackTrace(); 
		        e.printStackTrace();  
	    } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		String[] list1;
//		String[] list2;
//		String[] list3;
		//String path1="E:/othercitys/adfporigindata/";
//		String path2="E:/datacheck/nds/包[综]/53-16/53-16/";
		String path3="E:\\othercitys\\adfporigindata\\53-17\\53-17\\";
		list1=GetAllFileNameList(path3);
//		list2=GetAllFileNameList(path1);
//		list3=GetAllFileNameList(path1);
		String s="";
		for(int i=0;i<list1.length;i++)
		{
			s=s+list1[i];
		}
//		for(int i=0;i<list2.length;i++)
//		{
//			s=s+list2[i];
//		}
//		for(int i=0;i<list3.length;i++)
//		{
//			s=s+list3[i];
//		}
	    for(String i:mapout.get("鞍山市")){
			if(!s.contains(i.trim())){
				c=c+"\r\n"+i;
			}

	    }
		System.out.println(c);

	}
	/*public static void n(){
		
		String repath="";
		String[] list=GetAllFileNameList(repath);
		for(int i=0;i<list.length;i++)
		{
			String path=repath+list[i]+"\\";
			File filedir=new File(path);
			
			File[] file=filedir.listFiles();
			if(0==file[0].length())
			{
				file[0].delete();
				filedir.delete();
			}
		}
	}*/

}
