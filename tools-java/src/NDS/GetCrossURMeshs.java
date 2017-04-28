package NDS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.geotools.data.shapefile.dbf.DbaseFileException;
import org.geotools.data.shapefile.dbf.DbaseFileHeader;
import org.geotools.data.shapefile.dbf.DbaseFileReader;
import org.geotools.data.shapefile.dbf.DbaseFileWriter;
/**
 * 
 * @ClassName: GetCrossURMeshs
 * @Description: 从预处理数据中取出跨UR（省）的图幅，采用了loop方法，结果写在txt文档中
 * @author wb-lzg228465
 * @version 
 * 创建时间：2016年9月29日 下午5:25:16 
 * 程序的简单说明
 */
public class GetCrossURMeshs {
	public static int length=0;
	public static int resultlength=0;
	
	public static void main(String[] args) throws RowsExceededException, WriteException, IOException {
/*	    String[][] arraylist=new String[2][150000];
	    String[][] resultarraylist=new String[2][150000];
	    String uploadFileName="E:\\quan\\Export_Output_8.dbf";
	    String resultarrayfilepath="E:\\quan\\jiegou.txt";
	    arraylist=getFileData(uploadFileName);
	    resultarraylist=getCrossMeshes(arraylist);
	    writetotxt(resultarraylist,resultarrayfilepath);*/
		HashMap<String,ArrayList<String>> map=new HashMap<String,ArrayList<String>>();
		HashMap<String,String> resultmap=new HashMap<String,String>();
	    String uploadFileName="E:\\20161020\\Export_Output_2.dbf";
	    String resultarrayfilepath="E:\\20161020\\jiegou1.xlsx";
	    map=getFileData_mapmethod_djs(uploadFileName);
	    resultmap=getCrossMeshes_mapmethod(map);
	    WriteToExcel_CompatibleExcel_2007(resultmap,resultarrayfilepath);
	}
	
	/**
	 * 
	 * @methodsName getFileData
	 * @Description 从name_chn和mesh的映射dbf中取出name_chn和mesh
	 * @author wb-lzg228465
	 * @param uploadFileName 目标文件目录
	 * @return String[][]
	 * @version 
	 * 创建时间：2016年10月31日 下午3:24:49
	 */
	public static String[][] getFileData(String uploadFileName) {  
	    String[][] arraylist=new String[2][150000];
	    try {  
	    	@SuppressWarnings("resource")
			FileInputStream in=new FileInputStream(uploadFileName);  
			DbaseFileReader reader = new DbaseFileReader(in.getChannel(), false, Charset.forName("GBK"));	        try{
	        	int i=0;
	        	while(reader.hasNext()){
	        		Object[] entry = reader.readEntry();
	        		String mesh=entry[0].toString();
	        		String name_chn=entry[1].toString();
	        		arraylist[0][i]=mesh;
	        		arraylist[1][i]=name_chn;
	        		i++;
	        	}
	    	    length=i;
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
	    
	    return arraylist;
	}  

	/**
	 * 
	 * @methodsName getCrossMeshes
	 * @Description 从arraylist中取出对应多个UR的mesh
	 * @author wb-lzg228465
	 * @param arraylist name_chn和mesh的映射list
	 * @return String[][]
	 * @version 
	 * 创建时间：2016年10月31日 下午3:26:49
	 */
	public static String[][] getCrossMeshes(String[][] arraylist){
		String[][] temparraylist=arraylist;
		String[] comparearraylist=new String[150000];
		String[][] resultarraylist=new String[2][150000];
		int r=0;
		int c=0;
		int lengthl=length;
		for(int i=0;i<lengthl;i++){
			if(!loop(comparearraylist,arraylist[0][i])){
				String name_chn="";
				comparearraylist[c]=arraylist[0][i];
				c++;
				for(int j=i+1;j<lengthl;j++){
					if(temparraylist[0][j].equals(arraylist[0][i])){
						if(name_chn==""){
							name_chn=arraylist[1][i].toString();
						}
						name_chn=name_chn+"|"+temparraylist[1][j].toString();
					}
					else{
						continue;
					}
				}
				if(name_chn!=""){
					resultarraylist[0][r]=arraylist[0][i];
					resultarraylist[1][r]=name_chn;
					r++;
				}
			}
			else{
				continue;
			}
		}
		resultlength=r;
		return resultarraylist;
	}
	
	/**
	 * 
	 * @param resultarraylist
	 * @param resultarrayfilepath
	 * @Description: 不可用的方法，因超出索引，暂时不用
	 * @author wb-lzg228465
	 * @version 
	 * 创建时间：2016年9月29日 下午5:25:16 
	 */
	public static void writetodbf(String[][] resultarraylist,String resultarrayfilepath){
		ArrayList<Object[]> dbfrows = new ArrayList<Object[]>();
		int resultlength1=resultlength;
		for(int i=0;i<resultlength1;i++){
			Object[] new_row = new Object[2];
			new_row[0]=resultarraylist[0][i];
			new_row[1]=resultarraylist[1][i];
			dbfrows.add(new_row);
		}
		DbaseFileWriter dbaseFileWriter = null;
		FileOutputStream outputStream = null;

		try {
			outputStream = new FileOutputStream(resultarrayfilepath);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			DbaseFileHeader newHeader = new DbaseFileHeader();
			newHeader.addColumn("mesh", 'C', 10, 0);
			newHeader.addColumn("name_chn", 'C', 10, 0);
			newHeader.setNumRecords(dbfrows.size());
			dbaseFileWriter = new DbaseFileWriter(newHeader, outputStream.getChannel(), Charset.forName("GBK"));
			for (int k = 0; k < dbfrows.size(); k++) {

				dbaseFileWriter.write(dbfrows.get(k));
			}

		} 
		catch (DbaseFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			dbaseFileWriter.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @methodsName: writetotxt
	 * @Description:将数据写到txt文档中（过渡用，已弃用）
	 * @author wb-lzg228465
	 * @param resultarraylist
	 * @param resultarrayfilepath
	 * @version 
	 * 创建时间：2016年9月29日 下午5:25:16
	 */
	public static void writetotxt(String[][] resultarraylist,String resultarrayfilepath){
		File f = new File(resultarrayfilepath);		
		Writer out = null;	
		String str ="";
		for(int i=0;i<resultlength;i++){

		str = str+"\r\n"+resultarraylist[0][i]+"_"+resultarraylist[1][i]+"\r\n";		
		
		}
		try {
			out =  new FileWriter(f,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    //true表示追加		
		try {
			out.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @methodsName: loop
	 * @Description:简单遍历循环
	 * @author wb-lzg228465
	 * @param comparearraylist 循环列表
	 * @param targetValue 目标值
	 * @return
	 * @version 
	 * 创建时间：2016年9月29日 下午5:25:16
	 */
	public static boolean loop(String[] comparearraylist,String targetValue){
		for(String s:comparearraylist){
			if(s!=null&&s.equals(targetValue)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @methodsName: getCrossMeshes_mapmethod
	 * @Description:匹配用map存储数据的方法，筛选出有多个省的mesh
	 * @author wb-lzg228465
	 * @param map name_chn和mesh的映射map
	 * @version 
	 * 创建时间：2016年10月13日 下午12:11:50
	 * @return 
	 * @throws IOException 
	 */
	public static HashMap<String, String> getCrossMeshes_mapmethod(HashMap<String,ArrayList<String>> map) throws IOException{
	    String filepath="E:/20161020/UR_code.xlsx";
		HashMap<String,String> resultmap=new HashMap<String,String>();
		HashMap<String,String> map1=new HashMap<String,String>();
        map1=getExcelconentToHashmap(filepath,0,3);
		for(String mesh:map.keySet()){
			if(map.get(mesh).size()>1){
				/*
				 * 筛选出有多个省的mesh
				 */
				String province="";
				String midchar="|";
				for(int i=0;i<map.get(mesh).size();i++){
					String urcode="";
					urcode=map1.get(map.get(mesh).get(i).toString()).toString();
					province=(province+midchar+urcode).trim().substring(0);
				}
				resultmap.put(mesh, province);
			}
		}
		return resultmap;
	}

	/**
	 * 
	 * @methodsName: getFileData_mapmethod
	 * @Description:从name_chn和mesh的映射dbf中取出name_chn和mesh，使用map代替二维数组（学习尝试使用map--没有活，无聊，学习使用map）
	 * @author wb-lzg228465
	 * @param uploadFileName 目标文件目录
	 * @return map
	 * @version 
	 * 创建时间：2016年10月13日 下午5:29:08
	 */
	public static HashMap<String,ArrayList<String>> getFileData_mapmethod(String uploadFileName) {  
		HashMap<String,ArrayList<String>> map=new HashMap<String,ArrayList<String>>();
	    try {  
	    	@SuppressWarnings("resource")
			FileInputStream in=new FileInputStream(uploadFileName);  
			DbaseFileReader reader = new DbaseFileReader(in.getChannel(), false, Charset.forName("GBK"));	        try{
	        	int i=0;
	        	while(reader.hasNext()){
	        		Object[] entry = reader.readEntry();
	        		String mesh=entry[3].toString();
	        		String name_chn=entry[4].toString();
	        		if(!map.containsKey(mesh)){
	        			map.put(mesh,new ArrayList<String>());
	        		}
	        		map.get(mesh).add(name_chn);
	        		i++;
	        	}
	    	    length=i;
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
	    
	    return map;
	}  

	/**
	 * 
	 * @methodsName: WriteToExcel
	 * @Description:往excel中写入数据，97-2003版
	 * @author wb-lzg228465
	 * @param resultmap 源数据
	 * @param resultarrayfilepath 目标文件目录
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @version 
	 * 创建时间：2016年10月13日 下午5:31:24
	 */
	public static void WriteToExcel(HashMap<String, String> resultmap,String resultarrayfilepath) throws IOException, RowsExceededException, WriteException{
		WritableWorkbook  wwb=null;
		String filepath=resultarrayfilepath.substring(0, resultarrayfilepath.lastIndexOf("\\"));
		File file=new File(filepath);
		if(!file.exists()){
			file.mkdirs();
		}
		OutputStream output=new FileOutputStream(resultarrayfilepath);
		wwb=Workbook.createWorkbook(output);
		WritableSheet sheet=wwb.createSheet("跨省图幅", 0);
		Label label;
		int i=0;
		/*
		 * 添加表头
		 */
		label=new Label(0,i,"图幅号");
		sheet.addCell(label);
		label=new Label(1,i,"省份");
		sheet.addCell(label);
		i++;
		for(String mesh:resultmap.keySet()){
			// Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
			// 在Label对象的子对象中指明单元格的位置和内容
			label=new Label(0,i,mesh);
			sheet.addCell(label);
			label=new Label(1,i,resultmap.get(mesh));
			sheet.addCell(label);
			i++;
		}
		wwb.write();
		wwb.close();
	}

	/**
	 * 
	 * @methodsName: WriteToExcel_CompatibleExcel_2007
	 * @Description: 往excel中写入数据，兼容2007版
	 * @author wb-lzg228465
	 * @param resultmap 源数据
	 * @param resultarrayfilepath 目标文件目录
	 * @throws IOException
	 * @version 
	 * 创建时间：2016年10月13日 下午5:33:56
	 */
	public static void WriteToExcel_CompatibleExcel_2007(HashMap<String, String> resultmap,String resultarrayfilepath) throws IOException{
		//对路径进行处理
		String filepath=resultarrayfilepath.substring(0, resultarrayfilepath.lastIndexOf("\\"));
		File file=new File(filepath);
		if(!file.exists()){
			file.mkdirs();
		}
		//创建excel文件对象
		XSSFWorkbook  wb=new XSSFWorkbook ();
		Sheet sheet=wb.createSheet("跨省图幅");
		//设置表头
		Row rowhead=sheet.createRow(0);
		rowhead.createCell(0).setCellValue("图幅号");
		rowhead.createCell(1).setCellValue("省份");
		int i=0;
		//对表格内容进行处理
		i++;
		for(String mesh:resultmap.keySet()){
			Row row=sheet.createRow(i);
			row.createCell(0).setCellValue(mesh);
			row.createCell(1).setCellValue(resultmap.get(mesh));
			i++;
		}
			FileOutputStream os=new FileOutputStream(resultarrayfilepath);
			wb.write(os);
			os.close();
			wb.close();
	}
	
	
	public static HashMap<String,ArrayList<String>> getFileData_mapmethod_djs(String uploadFileName) {  
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
	        		if(!mapout.containsKey(mesh)){
	        			mapout.put(mesh,new ArrayList<String>());
	        		}
	        		mapout.get(mesh).add(name_chn);
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

}
