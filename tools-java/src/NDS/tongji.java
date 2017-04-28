package NDS;
import java.util.ArrayList;
import java.util.HashMap;

import NDS.factory;
public class tongji {
	public static void main(String[] args){
		Ttongji();
	}
	
	public static void Ttongji(){
		HashMap<String,String> map=new HashMap<String,String>();//存储log记录
		HashMap<String,String> tempmap=new HashMap<String,String>();//存储log记录到tempmap中
		HashMap<String,ArrayList<String>> tongjimap=new HashMap<String,ArrayList<String>>();//将统计结果记录到tongjimap中
		HashMap<String,String> caselistmap=new HashMap<String,String>();
		HashMap<String,String> morecaselistmap=new HashMap<String,String>();//额外的case
		HashMap<String,String> urmeshmap=new HashMap<String,String>();//存储原来UR内的图幅数
		String filepath="E:/NDSDataCheckProject/SimpleDst/log";//文件路径
		String[] urlist;
		urlist=factory.GetAllFileNameList(filepath);
		String str="";
		String strl="";
		String urmeshfilepath="E:/testdata/20161101/urmesh.txt";
		urmeshmap=factory.readtxttomap(urmeshfilepath);
		for(int urjs=0;urjs<urlist.length;urjs++){
			morecaselistmap=new HashMap<String,String>();
			String urfilepath=filepath+"/"+urlist[urjs];//ur路径
			String[] meshlist;
			String meshfilepath="";//mesh路径
			String[] logfilelist;
			String logfilepath="";//log路径
			//caselist的路径E:\20161101\caselist.txt
			String casepath="E:/testdata/20161101/caselist.txt";
			caselistmap=factory.readtxttomap(casepath);
			String[] caselist=new String[caselistmap.size()];
			int caselistjs=0;
			String line="";
			meshlist=factory.GetAllFileNameList(urfilepath);
			for(String Tcaselist:caselistmap.keySet()){
				caselist[caselistjs]=caselistmap.get(Tcaselist);
				caselistjs++;
			}
			for(int casejs=0;casejs<caselist.length;casejs++){
				tongjimap.put(caselist[casejs],new ArrayList<String>());
			}
			System.out.println("筛选的mesh：");
			for(int i=0;i<meshlist.length;i++){
				meshfilepath=urfilepath+"/"+meshlist[i];
				logfilelist=factory.GetAllFileNameList(meshfilepath);
				if(logfilelist==null||logfilelist.length==0){
					
				}else{
					for(int j=0;j<logfilelist.length;j++){
						logfilepath=meshfilepath+"/"+logfilelist[j];
						map=new HashMap<String,String>(factory.readtxttomap(logfilepath));
						tempmap=new HashMap<String,String>(map);
						for(int js=0;js<caselist.length;js++){
							if(js==0){
								
							}else{
								map=new HashMap<String,String>(tempmap);
							}
							try{
								for(String mapkey:map.keySet()){
									line=map.get(mapkey).toString();
									try{
										if(line.contains("roadname的值")){
											String linestr=line.substring(0, line.lastIndexOf("与"));
											boolean b=true;
											if(linestr.contains("桥")||linestr.contains("隧道")){
												tongjimap.get("roadname的值(XX桥/隧道)").add(line);
												tempmap.remove(mapkey);
												b=false;
											}
											if(b){
												String linestrlast=line.substring(line.lastIndexOf("与"), line.lastIndexOf("不"));
												String numstr="";
												for(int linejs=0;linejs<linestrlast.length();linejs++){
													if(linestrlast.charAt(linejs)>=48&&linestrlast.charAt(linejs)<=57){
														numstr+=linestrlast.charAt(linejs);
													}
												}
												if(numstr!=null&&!"".equals(numstr)){
													tongjimap.get("roadname的值(高速路编码)").add(line);
													tempmap.remove(mapkey);
												}
												b=false;
											}
											if(b){
												if(line.lastIndexOf(".")>0||line.contains("·")){
													tongjimap.get("roadname的值(.·)").add(line);
													tempmap.remove(mapkey);
												}
												b=false;
											}
											if(b){
												if(line.lastIndexOf("零")>0||line.contains("〇")){
													tongjimap.get("roadname的值(零〇)").add(line);
													tempmap.remove(mapkey);
												}
												b=false;
											}
										}
										if(line.contains(caselist[js].toString())){
											//当找到对应case时，进行以下处理
											try{
												tempmap.remove(mapkey);
											}catch(Exception e){
												System.out.println(e);
											}
											try{
												tongjimap.get(caselist[js]).add(line);
											}catch(Exception e){
												System.out.println(e);
											}

										}
									}catch(Exception e){
										System.out.println(e);
									}
								}
							}catch(Exception e){
								System.out.println(e);
							}
						}
						if(tempmap.size()>0){
							for(String m:tempmap.keySet()){
								if(tempmap.get(m)!=null&&!"".equals(tempmap.get(m))){
									if(!tempmap.get(m).contains("错误")){
										morecaselistmap.put(m, tempmap.get(m).trim()+"===mesh:"+meshlist[i].toString());//将新case存储
									}
								}
							}
						}
					}
				}
				System.out.print(meshlist[i]+"==");
			}
			String urlistnumstr="";
			String urliststr=urlist[urjs].toString().trim();
			String oldmeshnumstr="";
			int urlistnum=0;
			if(urliststr!=null&&!"".equals(urliststr)){
				for(int i=0;i<urliststr.length();i++){
					if(urliststr.charAt(i)>=48&&urliststr.charAt(i)<=57){
						urlistnumstr+=urliststr.charAt(i);
					}
				}
				if(urlistnumstr!=null&&!"".equals(urlistnumstr)){
					urlistnum=Integer.parseInt(urlistnumstr);
					urlistnum=urlistnum-1;	
				}
			}
			if(urlistnumstr!=null&&!"".equals(urlistnumstr)){
				urlistnumstr=String.valueOf(urlistnum);
				oldmeshnumstr=urmeshmap.get(urlistnumstr);
			}else{
				oldmeshnumstr="0";
			}
			//将tongjimap写入txt中
			String fengeifu="=";
			for(int i=5;i>0;i--){
				fengeifu=fengeifu+fengeifu;
			}
			str=str+fengeifu+"\r\n"+"ur:"+urlist[urjs].toString()+"\r\n"+"有log文件（夹）:"+meshlist.length+"总共有mesh："+oldmeshnumstr+"\r\n";
			System.out.println("比对的case");
			for(String caselistkey:tongjimap.keySet()){
				str=str+caselistkey+":"+tongjimap.get(caselistkey).size()+"\r\n";
				System.out.println(caselistkey);
			}
			//E:\20161101\tongjimap.txt
			strl=strl+"ur:"+urlist[urjs].toString()+"\r\n";
			for(String morecase:morecaselistmap.keySet()){
				strl=strl+morecaselistmap.get(morecase).toString()+"\r\n";
			}
		}
		String txtfilepath="E:/testdata/20170104/output/tongjimap.txt";
		factory.writestrtotxt(txtfilepath, str.trim());
		String txtfilepathl="E:/testdata/20170104/output/morecaselist.txt";
		factory.writestrtotxt(txtfilepathl, strl.trim());
	}	
}
