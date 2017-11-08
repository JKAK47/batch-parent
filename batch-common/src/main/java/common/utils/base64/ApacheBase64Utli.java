package common.utils.base64;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

/**
 * batch-parent.common.utils
 * Created by PengRong on 2017/11/8.
 *
 * @author PengRong
 * @since 2017-11-08 9:25
 */
public class ApacheBase64Utli {

	public ApacheBase64Utli() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Apache 的Base64 编码方法
	 *
	 * @Title: Base64Encode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param text
	 *            需要Base64编码的字符串
	 * @param charsetName
	 *            字符串编码为字节数组的编码字符集, 默认字符集方案为UTF-8
	 * @return String 返回base64编码后的字符串
	 * @throws
	 */
	public static String Base64Encode(String text, String charsetName) {
		Base64 base64 = new Base64();
		String result = null;
		if (!StringUtils.isEmpty(text)) {
			try {
				if (!StringUtils.isEmpty(charsetName)) {
					result = base64.encodeToString(text.getBytes(charsetName));
				} else {
					result = base64.encodeToString(text.getBytes("utf-8"));
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
