package NDS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.geotools.data.shapefile.dbf.DbaseFileReader;

import NDS.test.test;



public class GetURCode {
	
	
	
	public static void main(String[] args) throws Exception {
		/*	    String[][] arraylist=new String[2][150000];
			    String[][] resultarraylist=new String[2][150000];
			    String uploadFileName="E:\\quan\\Export_Output_8.dbf";
			    String resultarrayfilepath="E:\\quan\\jiegou.txt";
			    arraylist=getFileData(uploadFileName);
			    resultarraylist=getCrossMeshes(arraylist);
			    writetotxt(resultarraylist,resultarrayfilepath);*/
				HashMap<String,String> mapout=new HashMap<String,String>();
				HashMap<String,String> map=new HashMap<String,String>();
//				HashMap<String,ArrayList<String>> UR_code=new HashMap<String,ArrayList<String>>();
//				HashMap<String,String> resultmap=new HashMap<String,String>();
//			    String uploadFileName="E:/20161020/Export_Output_2.dbf";
			    String city_shape_filepath="E:/20161112/union_TEST_city.dbf";
			    String city_shape_filepath_union="E:/20161112/union_TEST_city_union.dbf";
			    String mesh_shape_filepath="E:/20161112/union_TEST_mesh.dbf";
			    String UR_code_city_name_filepath="E:/20161020/UR_code.xlsx";
			    String resultarrayfilepath="E:/20161115/quanguoresult/";
				String midchar="\r\n";
			    test.union_Polygon(city_shape_filepath, city_shape_filepath_union,map);
			    mapout=hashMapStringArrayToHashMapString(midchar,test.GetCity_nameAndmesh_file(city_shape_filepath_union,mesh_shape_filepath,map
			    		));
			    writetotxtdispath(mapout,UR_code_city_name_filepath,resultarrayfilepath);
			    //countmesh(uploadFileName,filepath,resultarrayfilepath);
			}
			
	/**
	 * 
	 * @methodsName hashMapStringArrayToHashMapString
	 * @Description 将hashmap的arraystring转为string
	 * @author wb-lzg228465
	 * @param mapout 目标HashMap String,ArrayList
	 * @return HashMap String,String
	 * @version 
	 * 创建时间：2016年10月31日 下午3:02:36
	 */
	public static HashMap<String,String> hashMapStringArrayToHashMapString(String midchar,HashMap<String,ArrayList<String>> mapout){
		HashMap<String,String> mapout1=new HashMap<String,String>();
		for(String name_chn:mapout.keySet()){
			if(mapout.get(name_chn).size()>1){

				String mesh1="";
				String mesh="";
				
				for(int i=0;i<mapout.get(name_chn).size();i++){
					mesh1=(mesh1+midchar+mapout.get(name_chn).get(i).toString()).trim();
				}
				mesh=mesh1.substring(0);
				mapout1.put(name_chn, mesh);
			}
		}
	    return mapout1;
	}
	
	/**
	 * 
	 * @methodsName  getFileData_mapmethod
	 * @Description 从UR_name和mesh的映射表取出ur_name和mesh
	 * @author wb-lzg228465
	 * @param uploadFileName 目标文件目录
	 * @return HashMap String,ArrayList<String>
	 * @version 
	 * 创建时间：2016年10月31日 下午3:00:07
	 */
	public static HashMap<String,ArrayList<String>> getFileData_mapmethod(String uploadFileName) {  
		HashMap<String,ArrayList<String>> mapout=new HashMap<String,ArrayList<String>>();
	    try {  
	    	@SuppressWarnings("resource")
			FileInputStream in=new FileInputStream(uploadFileName);  
			DbaseFileReader reader = new DbaseFileReader(in.getChannel(), false, Charset.forName("GBK"));	        
			try{
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
			e1.printStackTrace();
		} 
	    return mapout;
	}  

	/**
	 * 
	 * @methodsName writetotxt
	 * @Description 将map写入txt中,每一列为ur——mesh
	 * @author wb-lzg228465
	 * @param mapout 提供的urcode-mesh映射列表
	 * @param filepath URcode和ur_name的映射文件所在目录
	 * @param resultarrayfilepath 输出的txt文件存放路径
	 * @throws IOException
	 * @version 
	 * 创建时间：2016年10月31日 下午2:57:16
	 */
	public static void writetotxt(HashMap<String,String> mapout,String filepath,String resultarrayfilepath)throws IOException{
		String str ="";
		HashMap<String,String> map1=new HashMap<String,String>();
		HashMap<String,String> map2=new HashMap<String,String>();
		HashMap<String,String> map3=new HashMap<String,String>();
		
        map1=getExcelconentToHashmap(filepath,0,2);
        map2=getExcelconentToHashmap(filepath,0,1);
        map3=getExcelconentToHashmap(filepath,0,3);
        
        for(String name_chn:mapout.keySet()){
        	String urcode=map3.get(name_chn);//urcode
        	String adcode=map1.get(name_chn);//汉语名称
        	String pinyin=map2.get(name_chn);//拼音
        	String outvalue=name_chn+"_"+pinyin+"_"+adcode+"_"+urcode+";"+mapout.get(name_chn).toString();
    		str = str+"\r\n"+outvalue+"\r\n";		
        }
      	writestrtotxt(resultarrayfilepath,str);
	}
	
	/**
	 * 
	 * @methodsName getExcelconentToHashmap
	 * @Description 从excel中取出两列存入map中
	 * @author wb-lzg228465
	 * @param filepath 目标excel所在的目录
	 * @param keyindex key所在的列序号，从0开始计数
	 * @param conentindex 内容所在的列序号，从0开始计数
	 * @param sheetindex sheet的位置，从0开始计数，不输入则默认为0
	 * @return HashMap
	 * @throws IOException
	 * @version 
	 * 创建时间：2016年10月31日 下午2:09:06
	 */
	public static HashMap<String,String> getExcelconentToHashmap(String filepath,int keyindex,int conentindex,int...sheetindex) throws IOException{
		HashMap<String,String> map=new HashMap<String,String>();
		InputStream input=new FileInputStream(filepath);
		XSSFWorkbook wb=new XSSFWorkbook(input);
		Sheet sheet;
		String mapkey="";
		String mapconent="";
		if(sheetindex.length==0){
			sheet=wb.getSheetAt(0);
		}
		else{
			sheet=wb.getSheetAt(sheetindex[0]);
		}
		Iterator<Row> rows=sheet.rowIterator();
		while(rows.hasNext()){
			Row row=rows.next();
			mapkey=String.valueOf(row.getCell(keyindex));
			mapconent=String.valueOf(row.getCell(conentindex));
			if(mapkey.contains(".")){
				String pointlast="0"+mapkey.substring(mapkey.lastIndexOf("."));
				try{
					double point=Double.valueOf(pointlast);
					if(point==0){
						mapkey=mapkey.substring(0, mapkey.lastIndexOf("."));
					}
					else{
	//					nothing to do
					}
				}catch(Exception e){
//					nothing to do
				}
			}
			
			if(mapconent.contains(".")){
				String pointlast="0"+mapconent.substring(mapconent.lastIndexOf("."));
				try{
					double point=Double.valueOf(pointlast);
					if(point==0){
						mapconent=mapconent.substring(0, mapconent.lastIndexOf("."));
					}
					else{
	//					nothing to do
					}
				}catch(Exception e){
//					nothing to do
				}
			}
			map.put(mapkey, mapconent);
		}
		wb.close();
		return map;
	}

	/**
	 * 
	 * @methodsName writetotxtdispath
	 * @Description 将map写入txt中，并以urcode为文件名，每个UR单独输出一个txt文件
	 * @author wb-lzg228465
	 * @param mapout 提供的urcode-mesh映射列表
	 * @param filepath URcode和ur_name的映射文件所在目录
	 * @param resultarrayfilepath 输出的txt文件存放路径
	 * @throws IOException
	 * @version 
	 * 创建时间：2016年10月31日 下午2:27:13
	 */
	public static void writetotxtdispath(HashMap<String,String> mapout,String filepath,String resultarrayfilepath)throws IOException{
		String str ="";
		HashMap<String,String> map=new HashMap<String,String>();
		HashMap<String,String> result=new HashMap<String,String>();
		map=getExcelconentToHashmap(filepath,0,3);
        for(String name_chn:mapout.keySet()){
        	String urcode=map.get(name_chn);//urcode
        	String outvalue=mapout.get(name_chn).toString();
        	name_chn=urcode;
        	result.put(name_chn, outvalue);		
        }
        for(String name_chn:result.keySet()){
        	str=result.get(name_chn).toString();
          	writestrtotxt(resultarrayfilepath+name_chn+".txt",str);
        }
	}
	
	/**
	 * 
	 * @methodsName countmesh
	 * @Description 统计每个ur有多少个mesh，写入txt中
	 * @author wb-lzg228465
	 * @param uploadFileName ur_name和mesh的映射文件，视为最开始导出来的格式
	 * @param filepath URcode和ur_name的映射文件所在目录
	 * @param resultarrayfilepath 输出的txt文件存放路径
	 * @throws Exception
	 * @version 
	 * 创建时间：2016年10月31日 下午2:50:35
	 */
	public static void countmesh(String uploadFileName,String filepath,String resultarrayfilepath) throws Exception{
		HashMap<String,ArrayList<String>> mapout=new HashMap<String,ArrayList<String>>();
		HashMap<String,String> map=new HashMap<String,String>();
		HashMap<String,String> result=new HashMap<String,String>();
		String str ="";
        map=getExcelconentToHashmap(filepath,0,3);
        mapout=getFileData_mapmethod(uploadFileName);
       for(String name_chn:mapout.keySet()){
    	   String size=String.valueOf(mapout.get(name_chn).size());
       		String urcode=map.get(name_chn);
       		name_chn=urcode;
        	result.put(name_chn, size);
       }
       for(String name_chn:result.keySet()){
       	str=str+name_chn+";"+result.get(name_chn).toString()+"\r\n";
       }
      	writestrtotxt(resultarrayfilepath,str);
	}
	
	/**
	 * 
	 * @methodsName writestrtotxt
	 * @Description 将字符串写入txt文件中
	 * @author wb-lzg228465
	 * @param txtfilepath 输出目录
	 * @param str 输入字符串
	 * @version 
	 * 创建时间：2016年10月31日 下午3:12:27
	 */
	public static void writestrtotxt(String txtfilepath,String str){
		File f = new File(txtfilepath);	
//		if(!f.exists()){
//			f.mkdirs();			
//		}
		Writer out = null;	
       try {
			out =  new FileWriter(f,true);
			out.write(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<String,String> get_map(String path,int i) throws IOException{
		HashMap<String,String> map=new HashMap<String,String>();
		HashMap<String,String> result_map=new HashMap<String,String>();
		String key="";
		map=getExcelconentToHashmap(path, 3, 0);
		if(i==380){
			for(int j=319;j<i;j++){
				key=String.valueOf(j);
				result_map.put(map.get(key), key);
			}
		}else{
			for(int j=i-53;j<i;j++){
				key=String.valueOf(j);
				result_map.put(map.get(key), key);
			}
		}
		return result_map;
	}
}
