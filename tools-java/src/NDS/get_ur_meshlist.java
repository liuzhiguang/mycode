package NDS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import NDS.GetURCode;
import NDS.test.test;
//class Thread1 extends Thread{
//	private String name;
//	public Thread1(String name){
//		this.name=name;
//	}
//	public void run(){
//		for(int i=0; i<5; i++){
//			System.out.println(name+"运行:"+i);
//			try{
//				sleep((int)Math.random()*10);
//			}catch(InterruptedException e){
//				e.printStackTrace();
//			}
//		}
//	}
//}
class thread implements Runnable {
	private int ur;
	public thread(int ur){
		this.ur=ur;
	}
	public void run(){
		System.out.println("开始运行:"+ur);
		long startTime=System.currentTimeMillis();

		HashMap<String,String> mapout=new HashMap<String,String>();
		HashMap<String,String> map=new HashMap<String,String>();
		
		HashMap<String, ArrayList<String>> temp=new HashMap<String, ArrayList<String>>();
	    String city_shape_filepath="E:/testdata/20161202/city.dbf";
	    String city_shape_filepath_union="E:/testdata/20161202/city_union.dbf";
	    city_shape_filepath_union="E:/testdata/20161202/city_union"+"_"+ur+".dbf";
	    String mesh_shape_filepath="E:/testdata/20161202/bnd.dbf";
	    String UR_code_city_name_filepath="E:/testdata/20161202/UR_code.xlsx";
	    String resultarrayfilepath="E:/testdata/20161202/ur-mesh/";
		String midchar="\r\n";
//	    String midchar=",";
	    try {
			map=GetURCode.get_map(UR_code_city_name_filepath,ur);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    test.union_Polygon(city_shape_filepath, city_shape_filepath_union,map);
	    temp=test.GetCity_nameAndmesh_file(city_shape_filepath_union,mesh_shape_filepath,map);
	    mapout=GetURCode.hashMapStringArrayToHashMapString(midchar,temp);
	    System.out.println("---------线程："+ur+"正在写入txt-----------");
	    try {
			GetURCode.writetotxtdispath(mapout,UR_code_city_name_filepath,resultarrayfilepath);
//			GetURCode.writetotxt(mapout,UR_code_city_name_filepath,resultarrayfilepath+"jieguo.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    

	    try{
	    	Thread.sleep((int)Math.random()*10);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		long endTime=System.currentTimeMillis();
			
		System.out.println("线程"+ur+"运行时间："+(endTime-startTime)+"ms");

	}
	



}
public class get_ur_meshlist {
	public static void main(String[] args){
//		Thread1 mTh1=new Thread1("A");
//		Thread1 mTh2=new Thread1("B");
//		mTh1.start();
//		mTh2.start();

//		for(int i=1;i<8;i++){
//			int j=53;
//			new Thread(new thread(i*j)).start();
//
//		}
		long startTime=System.currentTimeMillis();
		Vector<Thread> threads = new Vector<Thread>();
		Thread thread1=new Thread(new thread(54));
		threads.add(thread1);
		thread1.start();
		Thread thread2=new Thread(new thread(107));
		threads.add(thread2);
		thread2.start();
		Thread thread3=new Thread(new thread(160));	
		threads.add(thread3);
		thread3.start();
		Thread thread4=new Thread(new thread(213));
		threads.add(thread4);
		thread4.start();
		Thread thread5=new Thread(new thread(266));
		threads.add(thread5);
		thread5.start();
		Thread thread6=new Thread(new thread(319));
		threads.add(thread6);
		thread6.start();
		Thread thread7=new Thread(new thread(380));
		threads.add(thread7);
		thread7.start();
//		new Thread(new thread(372)).start();
//		new Thread(new thread(372)).start();
//		new Thread(new thread(372)).start();
		for (Thread iThread : threads) {
			try {
				// 等待所有线程执行完毕
				iThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("主线执行。");
		long endTime=System.currentTimeMillis();
		System.out.println("程序运行时间："+(endTime-startTime)+"ms");
		
	}
}

class thread2 implements Runnable{
	private String name;
	public thread2(String name){
		this.name=name;
	}
	
	//@Override
	public void run(){
		for(int i=0;i<5;i++){
			System.out.println(name+"运行："+i);
			try{
				Thread.sleep((int)Math.random()*10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}

