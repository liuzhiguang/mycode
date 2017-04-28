package NDS;

public class string2num {
	/**
	 * @methodsName: 字母转数字
	 * @Description:抄的研发的方法，将字母转换成数字
	 * @author wb-lzg228465
	 * @param arrow
	 * @return
	 * @version 
	 * 创建时间：2016年12月29日 下午8:51:20
	 */
	private static String ConvertImageToID(String arrow){
		int nId=0;
		int nlen=arrow.length();
		for(int i=0;i<nlen;i++){
			if( '0' <= arrow.charAt(i) && arrow.charAt(i) <= '9'){
				nId=nId*10+(arrow.charAt(i)-'0');
			}else if(('a' <= arrow.charAt(i) && arrow.charAt(i) <= 'z')||
	                ('A' <= arrow.charAt(i) && arrow.charAt(i) <= 'Z')){
	            nId = nId * 100 + ( arrow.charAt(i) -'0');
//	            if(('a' <= arrow.charAt(i) && arrow.charAt(i) <= 'z')){
//	            	nId = nId * 100 + ( arrow.charAt(i) -'a' + 36);
//	            }else if( ('A' <= arrow.charAt(i) && arrow.charAt(i) <= 'Z')){
//	            	nId = nId * 100 + ( arrow.charAt(i) -'A' + 10);
//	            }
			}else{
	            System.out.println("File name contains one illegal character:"+arrow.charAt(i));
	        }
		}
		return String.valueOf(nId); 
	}

	public static void main(String[] args){
//		String str="001211111";
//		String a=ConvertImageToID(str);
//		System.out.println(a);
		String SEASON ="02";
		SEASON = Integer.valueOf(SEASON,16).toString();
	
	
	}
}
