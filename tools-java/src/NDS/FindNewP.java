package NDS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.HashMap;

public class FindNewP {
	public static void main(String[] args){
		String[] list=new String[36];
		list=getList();
		findNewP(list);
	}
	
	/**
	 * 
	 * @methodsName getList 
	 * @Description getList
	 * @author wb-lzg228465
	 * @return String[]
	 * @version 
	 * 创建时间：2016年10月31日 下午3:42:33
	 */
	public static String[] getList(){
		String[] list=new String[36];
		list[0]="该道路没有bmd索引";
		list[1]="8层道路只有一个节点";
		list[2]="12层道路只有一个节点";
		list[3]="14层道路只有一个节点";
		list[4]="各索引存的道路名称不统一";
		list[5]="14层UpLink在12层中不存在";
		list[6]="12层UpLink在8层中不存在";
		list[7]="12层的DownLink";
		list[8]="8层的DownLink";
		list[9]="8层的DownLink";
		list[10]="14层道路属性和12层道路首节点坐标不对应";
		list[11]="14层道路属性和12层道路尾节点坐标不对应";
		list[12]="14层道路属性和8层道路首节点坐标不对应";
		list[13]="14层道路属性和8层道路尾节点坐标不对应";
		list[14]="12层道路属性和8层道路首节点坐标不对应";
		list[15]="12层道路属性和8层道路尾节点坐标不对应";
		list[16]="12层道路属性和8层道路名称不对应";
		list[17]="14层道路名称不为空12层为空";
		list[18]="14层道路名称不为空8层为空";
		list[19]="14层道路名称为空12层不为空";
		list[20]="14层道路名称为空8层不为空";
		list[21]="12层道路名称为空8层不为空";
		list[22]="12层道路名称不为空8层为空";
		list[23]="14层道路属性和12层道路名称不对应";
		list[24]="14层道路属性和8层道路名称不对应";
		list[25]="下级道路（14层）不连续";
		list[26]="下级道路（12层）不连续";
		list[27]="14层道路属性和12层道路IsToll不同";
		list[28]="14层道路属性和8层道路IsToll不同";
		list[29]="12层道路属性和8层道路IsToll不同";
		list[30]="14层道路属性和12层道路IsBuilding不同";
		list[31]="12层道路属性和8层道路IsBuilding不同";
		list[32]="14层道路属性和12层道路LinkType不同";
		list[33]="14层道路属性和8层道路LinkType不同";
		list[34]="14层FC=1、2的道路没有被提到8层";
		list[35]="12层FC=1、2的道路，Formway!=2";
		return list;
	}
	
	/***
	 * 
	 * @methodsName findNewP
	 * @Description 过滤txt中的内容
	 * @author wb-lzg228465
	 * @param list 需要过滤内容的列表
	 * @version 
	 * 创建时间：2016年10月31日 下午3:40:40
	 */
	public static void findNewP(String[] list){
		String txt="";
		String result="";
		String filepath="E:\\AliDrive\\log\\20161030-jiedian100.81.1.66(2)\\ndstoshape\\ur_66\\debug_log\\201610\\20161029_error.log";
		String resultfilepath="E:\\AliDrive\\log\\20161030-jiedian100.81.1.66(2)\\ndstoshape\\ur_66\\debug_log\\201610\\20161029_error_1.txt";
		int js=0;
		try{
			String encoding="utf-8";
			File file=new File(filepath);
			if(file.isFile()&&file.exists()){
				InputStreamReader reader=new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader bufferedReader =new BufferedReader(reader);
              while((txt = bufferedReader.readLine()) != null){
            	  for(int i=0;i<list.length;i++){
	                  if(txt.contains(list[i].trim())){
	                	  continue;
	                  }
	                  else{
	                	  result=result+txt+"\r\n";
	                  }
                  }
            	  js++;
					if (js % 5000 == 0) {
						System.out.println("已完成" + js + "条记录过滤");
					}
              }
              reader.close();
			}
		}
		catch (Exception e){
			
		}
		File f = new File(resultfilepath);		
		Writer out = null;	
        try {
			out =  new FileWriter(f,true);
			out.write(result);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @methodsName readtxttomap
	 * @Description 读取txt到map文件中
	 * @author wb-lzg228465
	 * @param filepath 目标txt文件目录
	 * @return
	 * @version 
	 * 创建时间：2016年10月31日 下午4:06:05
	 */
	public static HashMap<String,String> readtxttomap(String filepath){

		HashMap<String,String> map=new HashMap<String,String>();
		int key=0;
		String keys="";
		String txt="";
		try{
			String encoding="utf-8";
			File file=new File(filepath);
			if(file.isFile()&&file.exists()){
				InputStreamReader reader=new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader bufferedReader =new BufferedReader(reader);
              while((txt = bufferedReader.readLine()) != null){
            	  map.put(keys, txt);
            	  key++;
            	  keys=String.valueOf(key);
            	
              }
              reader.close();
			}
		}
		catch (Exception e){
			
		}
		return map;
	}
}
