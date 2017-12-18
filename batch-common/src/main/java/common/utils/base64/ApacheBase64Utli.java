package common.utils.base64;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * batch-parent.common.utils  </br>
 * Created by PengRong on 2017/11/8. </br>
 *
 * @author PengRong</br>
 * @since 2017-11-08 9:25</br>
 */
public class ApacheBase64Utli {

	public ApacheBase64Utli() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Apache 的Base64 编码方法，首先将字符串用指定字符串解码为一个字节数组，然后通过Base64 编码为一个字符串
	 *
	 * @Title: Base64Encode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param text
	 *            需要Base64编码的字符串
	 * @param charsetName
	 *            字符串解码为字节数组时选取编码字符集, 默认解码字符集方案为UTF-8
	 * @return String 返回base64编码后的字符串
	 * @throws
	 */
	public static String Base64Encode(String text, String charsetName) {

		String result = null;
		if (!StringUtils.isEmpty(text)) {
			try {
				if (!StringUtils.isEmpty(charsetName)) {
					result = Base64Encode(text.getBytes(charsetName));
				} else {
					result = Base64Encode(text.getBytes("utf-8"));
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 *
	 * @param bytes 需要Base64编码的字节数组 </br>
	 * @return
	 */
	public  static  String Base64Encode(byte[] bytes){
		Base64 base64 = new Base64();
		if (bytes!=null){
			return  base64.encodeToString(bytes);
		}else {
			return null;
		}
	}

	/**
	 *	Apache 的Base64 编码方法，将Base64编码的字符串解码为字节数组，然后用指定的字符集将字节数组编码为字符串</br>
	 *	所以必须知道Base64 编码的字符串 原来是用什么字符集解码的为字节数组；默认编码字符集为 UTF-8
	 * @param text
	 * @param charsetName
	 * @return
	 */
	public static String Base64Decode(String text, String charsetName) {
		Base64 base64 = new Base64();
		// 解码Base64
		byte[] debytes = base64.decode(text);
		// System.out.println(Arrays.toString(debytes));
		// 还原字符串
		String result = null;

		try {
			if (!StringUtils.isEmpty(charsetName)) {
				result = new String(debytes, charsetName);
			} else {
				result = new String(debytes, "utf-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;

	}

    /**
     * author: PengRong<br/>
     * Desc: Base64 测试类
     * @param args
     */
	public static void main(String[] args) {
		// apache base64 编码测试案例
		System.out.println(ApacheBase64Utli.Base64Encode("中国", null));
		System.out.println(ApacheBase64Utli.Base64Encode("中国", "utf-8"));
		System.out.println(ApacheBase64Utli.Base64Encode("中国", "ASCII"));
		System.out.println(ApacheBase64Utli.Base64Encode("中国", "GBK"));
		// base64 解码测试案例,用UTF-8 编码字符串为字节数组后然后再次用Base64进行编码
		String encode = ApacheBase64Utli.Base64Encode("中国", "utf-8");

        System.out.println(encode);
        System.out.println(ApacheBase64Utli.Base64Decode(encode, null));
		System.out.println(ApacheBase64Utli.Base64Decode(encode, "utf-8"));
		/**
		 * 下面解码会错误，当base64 解码后再次用GBK将字节数组解码为字符串时候出错， 原来字符串使用utf-8编码为字节数组
		 */
		System.out.println(ApacheBase64Utli.Base64Decode(encode, "GBK"));
	}
}
