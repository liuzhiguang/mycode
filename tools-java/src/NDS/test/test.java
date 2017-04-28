package NDS.test;


import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import com.vividsolutions.jts.geom.Geometry;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.geotools.data.FeatureWriter;
import org.geotools.data.FileDataStoreFactorySpi;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.referencing.crs.DefaultGeographicCRS;


public class test {
	public static void main(String[] args){
		String city_shape_filepath="E:/20161112/union_TEST_city.dbf";
		String mesh_shape_filepath="E:/20161112/union_TEST_mesh.dbf";
		//GetCity_nameAndmesh_file(city_shape_filepath,mesh_shape_filepath);
	}
	
	
	/**
	 * 
	 * @methodsName: city和mesh的空间连接处理
	 * @Description: 将city和mesh进行相交处理，获得city和mesh的映射关系
	 * @author wb-lzg228465
	 * @param city_shape_filepath
	 * @param mesh_shape_filepath
	 * @return 一个城市名称和mesh号的映射关系
	 * @version 
	 * 创建时间：2016年11月12日 下午5:46:19
	 */
	public static HashMap<String,ArrayList<String>> GetCity_nameAndmesh_file(String city_shape_filepath,String mesh_shape_filepath,HashMap<String,String> map){
		//city和mesh的shape文件路径
		/*city_shape_filepath="E:/20161112/CITY.dbf";
		mesh_shape_filepath="E:/20161112/Bnd.dbf";*/
		//建立一个map，用于存储此线程需要跑的UR的name
		HashMap<String,String> thread_map=new HashMap<String,String>();
		thread_map=map;
		//输出结果的map
		HashMap<String,ArrayList<String>> result_map=new HashMap<String,ArrayList<String>>();
		try {
		    System.out.println("---------开始融合shape文件："+city_shape_filepath+"-----------" + System.currentTimeMillis());
			//读取UR和mesh的shape文件
			ShapefileDataStore city_shape=(ShapefileDataStore) new ShapefileDataStoreFactory()
					.createDataStore(new File(city_shape_filepath).toURI().toURL());
			ShapefileDataStore mesh_shape=(ShapefileDataStore) new ShapefileDataStoreFactory()
					.createDataStore(new File(mesh_shape_filepath).toURI().toURL());
		/*	geotools读取shapefile文件的默认编码为ISO-8859-1，但中文环境下shapefile文件的默认编码一般为GBK，
			所以需要告诉shapefiledatastore使用GBK进行解析*/
			((ShapefileDataStore) city_shape).setCharset(Charset.forName("GBK"));
			((ShapefileDataStore) mesh_shape).setCharset(Charset.forName("GBK"));
			//取要素集====city_shape.getTypeNames()[0]没看懂
			SimpleFeatureSource city_shape_fs=city_shape.getFeatureSource(city_shape.getTypeNames()[0]);
			SimpleFeatureSource mesh_shape_fs=mesh_shape.getFeatureSource(mesh_shape.getTypeNames()[0]);
			if(city_shape_fs instanceof SimpleFeatureSource&&mesh_shape_fs instanceof SimpleFeatureSource){
				//取要素集的迭代器
				SimpleFeatureIterator city_shape_fs_it=city_shape_fs.getFeatures().features();
					while(city_shape_fs_it.hasNext()){
						//取一个city要素
						SimpleFeature city_f=city_shape_fs_it.next();
						//取出city的名称===编码问题未解决===编码问题已解决18:43
						String city_name_ch=city_f.getAttribute(3).toString();
						//对北京，上海，重庆，天津等直辖市进行特殊处理
						city_name_ch=city_name_special_handling(city_name_ch);
						//判断线程map是否包含此city的name，不包含的话直接continue
						if(!thread_map.containsKey(city_name_ch)){
							continue;
						}
						//把city的name作为keys放入hashmap中
						if(!result_map.containsKey(city_name_ch)){
							result_map.put(city_name_ch, new ArrayList<String>());
						}
						//取要素的几何形状
						Geometry city_geometry=(Geometry) city_f.getDefaultGeometry();		
						//取mesh的迭代器，=====先前在最外层取迭代器，但因为循环一次后迭代器失效，会导致循环内的次级循环无法使用此迭代器，所以在次级循环前取迭代器
						SimpleFeatureIterator mesh_shape_fs_it=mesh_shape_fs.getFeatures().features();
						//进行一个大循环(在想出更好办法之前，大循环是必须要做的)，把mesh遍历一次，取出所有与UR相交的mesh====mesh超过了10万，可能会很慢
						while(mesh_shape_fs_it.hasNext()){
							//取一个mesh的要素
							SimpleFeature mesh_f=mesh_shape_fs_it.next();
							//取mesh要素的几何形状
							Geometry mesh_geometry=(Geometry) mesh_f.getDefaultGeometry();
							//判断mesh和city是否相交
							if(city_geometry.intersects(mesh_geometry)){
								//取mesh的图幅号
								String mesh_tfh=mesh_f.getAttribute(1).toString();
								//将图幅号放入hashmap对应keys的list中
								result_map.get(city_name_ch).add(mesh_tfh);
							}
						}
						//关闭迭代器，否则会有警告
						mesh_shape_fs_it.close();
					}
					//关闭迭代器，否则会有警告
					city_shape_fs_it.close();
					//进行释放或处理
					city_shape.dispose();
					
					//一个city的图斑可能有好几块，这样会造成mesh号有重复的
					//对arraylist进行去重处理
				    System.out.println("---------正在去重-----------"+city_shape_filepath+  System.currentTimeMillis());
					for(String city_name_ch:result_map.keySet()){
						ArrayList<String> mesh_arraylist=new ArrayList<String>(result_map.get(city_name_ch));
						//使用hashset进行去重处理
						ArrayList<String> mesh_arraylist_nonredundant=new ArrayList<String>(new HashSet<String>(mesh_arraylist));
						result_map.get(city_name_ch).clear();
						result_map.get(city_name_ch).addAll(mesh_arraylist_nonredundant);
					}
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				//(new File(city_shape_filepath).toURI().toURL());

	    System.out.println("---------融合结束-----------" +city_shape_filepath+ System.currentTimeMillis());
		return result_map;
	}

	/**
	 * 
	 * @methodsName: 特殊城市名称处理
	 * @Description: 对特殊的城市名称进行处理（“北京城区”变为“北京市”）
	 * @author wb-lzg228465
	 * @param city_name
	 * @return 修改后的城市名称
	 * @version 
	 * 创建时间：2016年11月12日 下午6:12:56
	 */
	public static String city_name_special_handling(String city_name){
		String[] special_city_name={"北京","上海","天津","重庆"};
		for(int i=0;i<special_city_name.length;i++){
			if(city_name.contains(special_city_name[i].toString())){
				city_name=special_city_name[i].toString().trim()+"市";
			}
		}

		return city_name;
		
	}

	//方案一：建一个数据集，拿在city数据集中取到的首次出现的name作为查找项，在city中遍历查找，找到同名的后进行union，
	//遍历结束后，将其写入新的shapefile中
	/**
	 * 
	 * @methodsName: 
	 * @Description：想到新的方法=====在取到融合的feature后直接传给方法GetCity_nameAndmesh_file
	 * （优点：加快运行速度，减少资源占用 ；   风险：方法union_Polygon失去独立价值）
	 * @author wb-lzg228465
	 * @version 
	 * 创建时间：2016年11月12日 下午8:34:28
	 */
	public static void union_Polygon(String shapefilepath,String shapefile_temp_file,HashMap<String,String> thread_map){
//		String shapefilepath="";
//		String shapefile_temp_file="";
		try {
			//取需融合图斑的shape文件
			ShapefileDataStore incomplete_fusion_city_polygon_shape=(ShapefileDataStore) new ShapefileDataStoreFactory()
					.createDataStore(new File(shapefilepath).toURI().toURL());
			//创建新的shapefile
			//设置一个params（参数）
			HashMap<String,Serializable> params=new HashMap<String,Serializable>();
			//调用相关方法的集合
			FileDataStoreFactorySpi factory=new ShapefileDataStoreFactory();
			//没有看明白
			params.put(ShapefileDataStoreFactory.URLP.key, new File(shapefile_temp_file).toURI().toURL());
			//通过params参数创建一个新的shape文件
			ShapefileDataStore complete_fusion_city_polygon_shape=(ShapefileDataStore) factory
					.createNewDataStore(params);
			/*	geotools读取shapefile文件的默认编码为ISO-8859-1，但中文环境下shapefile文件的默认编码一般为GBK，
			所以需要告诉shapefiledatastore使用GBK进行解析*/
			//2016/11/15 16:41 先前忘了这件事，导致融合一直出错
			((ShapefileDataStore) incomplete_fusion_city_polygon_shape).setCharset(Charset.forName("GBK"));
			((ShapefileDataStore) complete_fusion_city_polygon_shape).setCharset(Charset.forName("GBK"));
			//从未融合图斑的shape文件中提取要素集
			SimpleFeatureSource incomplete_fusion_city_polygon_shape_fs=incomplete_fusion_city_polygon_shape
					.getFeatureSource(incomplete_fusion_city_polygon_shape.getTypeNames()[0]);
			if(incomplete_fusion_city_polygon_shape_fs instanceof SimpleFeatureSource){
				//将新建的shape文件的坐标系与未融合图斑的shape设为一致
				complete_fusion_city_polygon_shape.createSchema(SimpleFeatureTypeBuilder
						.retype(incomplete_fusion_city_polygon_shape_fs.getSchema(), DefaultGeographicCRS.WGS84));
				//设置一个featurewriter
				FeatureWriter<SimpleFeatureType, SimpleFeature> writer = complete_fusion_city_polygon_shape
						.getFeatureWriter(complete_fusion_city_polygon_shape.getTypeNames()[0],
						Transaction.AUTO_COMMIT);
				//取未融合图斑的迭代器
				SimpleFeatureIterator incomplete_fusion_city_polygon_shape_fs_it = incomplete_fusion_city_polygon_shape_fs
						.getFeatures().features();
				try{
					//建一个hashmap表，存放已经融合的city的name
					HashMap<String,String> city_name=new HashMap<String,String>();
					
					while(incomplete_fusion_city_polygon_shape_fs_it.hasNext()){
						//取未融合图斑的一个要素
						SimpleFeature incomplete_polygon_f=incomplete_fusion_city_polygon_shape_fs_it.next();
//						if(incomplete_polygon_f.getAttribute(3).toString().contains("北京")){
//							System.out.println("北京1");
//						}
						//特殊城市处理
						String incomplete_city_name=city_name_special_handling(incomplete_polygon_f.getAttribute(3).toString());
//						if(incomplete_polygon_f.getAttribute(3).toString().contains("北京")){
//							System.out.println("北京2");
//						}
						//进行判断，如果已经融合过了，就跳过
						if(!city_name.containsKey(incomplete_city_name)&&thread_map.containsKey(incomplete_city_name)){
							//将未融合city的name放入hashmap中
							city_name.put(incomplete_city_name, incomplete_city_name);
							//writer往下走一步,取要写入的要素
							SimpleFeature complete_polygon_f=writer.next();
							//没看懂
							complete_polygon_f.setAttributes(incomplete_polygon_f.getAttributes());
							//定义路形变量
							Geometry city_geometry = null;
							//再把所有的city遍历一遍，将同名的图斑融合起来
							//取一个新的迭代器
							SimpleFeatureIterator incomplete_fusion_city_polygon_shape_fs_it_ = incomplete_fusion_city_polygon_shape_fs
									.getFeatures().features();
							while(incomplete_fusion_city_polygon_shape_fs_it_.hasNext()){
								//取未融合图斑的一个要素
								SimpleFeature incomplete_polygon_f_=incomplete_fusion_city_polygon_shape_fs_it_.next();
								//判断是否与要融合的city的name一致（特殊城市暂未处理==先不予处理）
								String incomplete_city_name_=city_name_special_handling(incomplete_polygon_f_.getAttribute(3).toString());
								if(city_name.get(incomplete_city_name).equals(incomplete_city_name_)){
									if(city_geometry==null){
										//获取路形
										city_geometry=(Geometry) incomplete_polygon_f_.getDefaultGeometry();
									}else{
										//获取其他同名的图斑
										Geometry city_geometry_=(Geometry) incomplete_polygon_f_.getDefaultGeometry();
										//融合其他同名的图斑
										city_geometry=city_geometry.union(city_geometry_);
									}									
								}
							}
							//2016/11/15 17:39在融合路形后，还需写入源文件中（先前没有写，导致融合操作后只保留读到的最后一块图斑）
							complete_polygon_f.setDefaultGeometry(city_geometry);
							incomplete_fusion_city_polygon_shape_fs_it_.close();
						}
						
						
					}
					writer.write();
				}finally{
					incomplete_fusion_city_polygon_shape_fs_it.close();
					writer.close();
					incomplete_fusion_city_polygon_shape.dispose();
					complete_fusion_city_polygon_shape.dispose();
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @methodsName: 
	 * @Description:在取到融合的feature后直接传给方法GetCity_nameAndmesh_file
	 * @author wb-lzg228465
	 * @param shapefilepath
	 * @param shapefile_temp_file
	 * @param thread_map
	 * @version 
	 * 创建时间：2016年11月16日 下午8:38:47
	 */
	public static SimpleFeature union_Polygon_sf(String shapefilepath,String shapefile_temp_file,HashMap<String,String> thread_map){
//		String shapefilepath="";
//		String shapefile_temp_file="";
		try {
			//取需融合图斑的shape文件
			ShapefileDataStore incomplete_fusion_city_polygon_shape=(ShapefileDataStore) new ShapefileDataStoreFactory()
					.createDataStore(new File(shapefilepath).toURI().toURL());
			//创建新的shapefile
			ShapefileDataStore complete_fusion_city_polygon_shape=new ShapefileDataStore(null);
			/*	geotools读取shapefile文件的默认编码为ISO-8859-1，但中文环境下shapefile文件的默认编码一般为GBK，
			所以需要告诉shapefiledatastore使用GBK进行解析*/
			//2016/11/15 16:41 先前忘了这件事，导致融合一直出错
			((ShapefileDataStore) incomplete_fusion_city_polygon_shape).setCharset(Charset.forName("GBK"));
			((ShapefileDataStore) complete_fusion_city_polygon_shape).setCharset(Charset.forName("GBK"));
			//从未融合图斑的shape文件中提取要素集
			SimpleFeatureSource incomplete_fusion_city_polygon_shape_fs=incomplete_fusion_city_polygon_shape
					.getFeatureSource(incomplete_fusion_city_polygon_shape.getTypeNames()[0]);
			if(incomplete_fusion_city_polygon_shape_fs instanceof SimpleFeatureSource){
				//将新建的shape文件的坐标系与未融合图斑的shape设为一致
				complete_fusion_city_polygon_shape.createSchema(SimpleFeatureTypeBuilder
						.retype(incomplete_fusion_city_polygon_shape_fs.getSchema(), DefaultGeographicCRS.WGS84));
				//设置一个featurewriter
				FeatureWriter<SimpleFeatureType, SimpleFeature> writer = complete_fusion_city_polygon_shape
						.getFeatureWriter(complete_fusion_city_polygon_shape.getTypeNames()[0],
						Transaction.AUTO_COMMIT);
				//取未融合图斑的迭代器
				SimpleFeatureIterator incomplete_fusion_city_polygon_shape_fs_it = incomplete_fusion_city_polygon_shape_fs
						.getFeatures().features();
				try{
					//建一个hashmap表，存放已经融合的city的name
					HashMap<String,String> city_name=new HashMap<String,String>();
					
					while(incomplete_fusion_city_polygon_shape_fs_it.hasNext()){
						//取未融合图斑的一个要素
						SimpleFeature incomplete_polygon_f=incomplete_fusion_city_polygon_shape_fs_it.next();
						String incomplete_city_name=city_name_special_handling(incomplete_polygon_f.getAttribute(3).toString());
						//进行判断，如果已经融合过了，就跳过
						if(!city_name.containsKey(incomplete_city_name)&&thread_map.containsKey(incomplete_city_name)){
							//将未融合city的name放入hashmap中
							city_name.put(incomplete_city_name, incomplete_city_name);
							//writer往下走一步,取要写入的要素
							SimpleFeature complete_polygon_f=writer.next();
							//没看懂
							complete_polygon_f.setAttributes(incomplete_polygon_f.getAttributes());
							//定义路形变量
							Geometry city_geometry = null;
							//再把所有的city遍历一遍，将同名的图斑融合起来
							//取一个新的迭代器
							SimpleFeatureIterator incomplete_fusion_city_polygon_shape_fs_it_ = incomplete_fusion_city_polygon_shape_fs
									.getFeatures().features();
							while(incomplete_fusion_city_polygon_shape_fs_it_.hasNext()){
								//取未融合图斑的一个要素
								SimpleFeature incomplete_polygon_f_=incomplete_fusion_city_polygon_shape_fs_it_.next();
								//判断是否与要融合的city的name一致（特殊城市暂未处理==先不予处理）
								if(city_name.get(incomplete_city_name).equals(incomplete_polygon_f_.getAttribute(3).toString())){
									if(city_geometry==null){
										//获取路形
										city_geometry=(Geometry) incomplete_polygon_f_.getDefaultGeometry();
									}else{
										//获取其他同名的图斑
										Geometry city_geometry_=(Geometry) incomplete_polygon_f_.getDefaultGeometry();
										//融合其他同名的图斑
										city_geometry=city_geometry.union(city_geometry_);
									}									
								}
							}
							//2016/11/15 17:39在融合路形后，还需写入源文件中（先前没有写，导致融合操作后只保留读到的最后一块图斑）
							complete_polygon_f.setDefaultGeometry(city_geometry);
							incomplete_fusion_city_polygon_shape_fs_it_.close();
						}
						
						
					}
					writer.write();
				}finally{
					incomplete_fusion_city_polygon_shape_fs_it.close();
					writer.close();
					incomplete_fusion_city_polygon_shape.dispose();
					complete_fusion_city_polygon_shape.dispose();
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}


}
