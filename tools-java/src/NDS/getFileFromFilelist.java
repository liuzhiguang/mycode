package NDS;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import NDS.factory;
/**
 * @ClassName getFileFromFilelist
 * @Description 从文件列表中取出目标文件（文件夹），放入指定目录中
 * @author wb-lzg228465
 * @version 
 * 创建时间：2016年10月31日 下午4:13:15 
 */
public class getFileFromFilelist {
	public static void main(String[] args) throws IOException{
		String path="E:/AliDrive/log/ur271/dst/test/";//文件夹
		String filepath="E:/20161020/UR_mesh.txt";//UR——mesh列表映射文件
		int jiedian =5;
		String[] allfilelist;
		HashMap<String,String> map=new HashMap<String,String>();
		HashMap<String,String> shaixuanmap=new HashMap<String,String>();//为了适应跨UR，简单的改进，但问题未完全解决
		allfilelist=factory.GetAllFileNameList(path);//文件夹中所有文件列表（mesh）
		map=factory.readtxttomap(filepath);//UR——mesh列表
		for(int i=0+jiedian-1;i<map.size();){
			String mesh=map.get(String.valueOf(i));
			//mesh串，包含ur_name，
			//阿拉善盟_alashanmeng_152900_41;J48F032018,J48F031017,J48F031018,J48F031019,J48F031020。。。
			if(mesh==""||mesh==null||mesh.length()<1){
				continue;
			}
			else{
				String UR_code=mesh.substring(mesh.lastIndexOf("_")+1, mesh.lastIndexOf(";"));
				for(int j=0;j<allfilelist.length;j++){
					if(mesh.contains(allfilelist[j])){
						String destpath="E:/20161020";
						String[] filedirfilelist;
						String destpathdir;
						String temppath;
						temppath=path+allfilelist[j]+"/";
						filedirfilelist=factory.GetAllFileNameList(path+allfilelist[j]+"/");//文件夹中所有文件列表（log）
						try{
							if(filedirfilelist==null||filedirfilelist.length==0){
								System.out.println("11");
							}else{
								for(int list=0;list<filedirfilelist.length;list++){
									String logdir="";
									destpath=path;
									destpathdir=destpath+"/"+UR_code+"/"+allfilelist[j]+"/";
									logdir=path+allfilelist[j]+"/"+filedirfilelist[list];
									destpath=destpath+"/"+UR_code+"/"+allfilelist[j]+"/"+filedirfilelist[list];
									File source=new File(logdir);
									File dest=new File(destpath);
									File destdir=new File(destpathdir);
									if(!dest.exists()){
										destdir.mkdirs();
									}
									source.renameTo(dest);
								}
							}
						}catch(Exception e){
							System.out.println(e);
						}
						
						File temp=new File(temppath); 
						temp.delete();
					}
				}
			}
			i=i+7;
		}
	}
	
}
