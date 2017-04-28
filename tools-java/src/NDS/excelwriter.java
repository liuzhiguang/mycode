package NDS;

import java.io.File;
import java.io.FileInputStream;  
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.usermodel.HSSFSheet;
/**
 * 
 * @ClassName: excelwriter
 * @Description: 统计N2A时生成的symaat，maat，sysaat，saat四者的记录数
 * @author wb-lzg228465
 * @version 
 * 创建时间：2016年9月29日 下午5:17:03 
 * 程序的简单说明
 */
public class excelwriter {
	private HSSFWorkbook workbook=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public  boolean fileExist(String fileDir){
		boolean flag;
		File file=new File(fileDir);
		flag=file.exists();
		return flag;
	}

	
	public  boolean sheetExist(String fileDir,String sheetName){
		boolean flag=false;
		File file=new File(fileDir);
		if(file.exists()){
			try{
				workbook=new HSSFWorkbook(new FileInputStream(file));
				Sheet sheet=workbook.createSheet(sheetName);
				if(null!=sheet)
					flag=true;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else{
			flag=false;
		}
		return flag;
	}

	public void writetoExcel(String fileDir, String sheetName, String counts ,int cols){
		
		if(fileExist(fileDir)&&
			sheetExist(fileDir,sheetName)){
			File file=new File(fileDir);
			try{
			workbook=new HSSFWorkbook(new FileInputStream(file));
			}
			catch(FileNotFoundException e){
				
			}
			catch(Exception e){
				
			}
			FileOutputStream out=null;
			HSSFSheet sheet=workbook.getSheet(sheetName);
			int rowcount=sheet.getLastRowNum()+1;
			//int columncount=sheet.getRow(0).getLastCellNum();
			try{
				Row row=sheet.createRow(rowcount);
				
				String data=counts;
				Cell cell=row.createCell(cols);
				cell.setCellValue(data);
				out=new FileOutputStream(fileDir);
				workbook.write(out);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try{
					out.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}


}
