package NDS;

import java.io.File;
import NDS.toolsfactory;
/**
 * 
 * @ClassName: qukong
 * @Description: 去掉因错误导致A2N时产生的空log及其文件夹
 * @author wb-lzg228465
 * @version 
 * 创建时间：2016年9月29日 下午5:24:20 
 * 程序的简单说明
 */
public class qukong {
	
	public static void main(String[] args){
		String filepath="E:\\AliDrive\\log\\ur271\\dst\\";
		clearnull(filepath);
	}

	public static void clearnull(String filepath){
		String repath=filepath;
		String[] list=NDS.list.GetAllFileNameList(repath);
		toolsfactory factory=new toolsfactory();
		
		for(int i=0;i<list.length;i++)
		{
			if(factory.isFileOrisDirectory(repath+list[i])=="D"){
				String path=repath+list[i]+"\\";
				File filedir=new File(path);
				
				File[] file=filedir.listFiles();
				if(0==file[0].length())
				{
					file[0].delete();
					filedir.delete();
				}
			}
		}
	}
}
