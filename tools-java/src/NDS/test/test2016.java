package NDS.test;

public class test2016 {

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
			}else{
	            System.out.println("File name contains one illegal character:"+arrow.charAt(i));
	        }
		}
		return String.valueOf(nId); 
	}

	public static void main(String[] args){
		char a='a';
		char b='z';
		int c=a-'0';
		String m="1212";
		m=ConvertImageToID(m);
		System.out.println(m);
	}
}
