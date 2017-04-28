package NDS;

import java.io.File;

/**
 * 
 * @ClassName: toolsfactory
 * @Description: 一些方法的集合
 * @author wb-lzg228465
 * @version 
 * 创建时间：2016年9月30日 下午5:33:40 
 * 程序的简单说明
 */

public class toolsfactory {
	/**
	 * 判断文件是否存在
	 * @param fileDir
	 * @return true
	 */
	public  boolean fileExist(String fileDir){
		boolean flag;
		File file=new File(fileDir);
		flag=file.exists();
		return flag;
	}
	/**
	 * 判断是文件夹还是文件
	 * @param fileDir
	 * @return
	 */
	public String isFileOrisDirectory(String fileDir){
		String flag="NotEach";
		File file=new File(fileDir);
		if(file.isDirectory()){
			flag="D";
		}
		if(file.isFile()){
			flag="F";
		}
		return flag;
	}
}
