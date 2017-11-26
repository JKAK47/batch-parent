package common.utils.base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.util.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * batch-parent.common.utils Created by PengRong on 2017/11/8.
 *
 * @author PengRong
 * @since 2017-11-08 9:25
 */
@SuppressWarnings("restriction")
public class ConverterUtils {

	public ConverterUtils() {
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * @Title: Base64Encode 编码
	 * @Description: TODO(Base64Encode 编码工具方法)
	 * @param text
	 *            待编码的字符串
	 * @param charsetName
	 *            待编码字符串转换为字节的字符编码集，默认是UTT-8
	 * @throws UnsupportedEncodingException
	 *             设定文件
	 * @return String 返回Base64编码后的字符串
	 * @throws
	 */
	public static String Base64Encode(String text, String charsetName)
			throws UnsupportedEncodingException {
		BASE64Encoder encoder = new BASE64Encoder();
		String result = null;

		if (!StringUtils.isEmpty(text)) {
			if (StringUtils.isEmpty(charsetName)) {
				result = encoder.encode(text.getBytes("utf-8"));
			} else {
				result = encoder.encode(text.getBytes(charsetName));
			}
		}
		return result;
	}

	/**
	 *
	 * @Title: Base64Decode
	 * @Description: TODO(Base64 解码工具方法 )
	 * @param decodeText
	 *            待解码的Base64 编码字符串
	 * @param charsetName
	 *            Base64解码后组装字符串时使用的字符集，默认使用UTF-8
	 * @param
	 * @param IOException
	 *            设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String Base64Decode(String decodeText, String charsetName)
			throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytes = null;
		String result = null;
		if (!StringUtils.isEmpty(decodeText)) {
			bytes = decoder.decodeBuffer(decodeText);
		}
		if (!StringUtils.isEmpty(charsetName)) {
			result = new String(bytes, charsetName);
		} else {
			result = new String(bytes, "utf-8");
		}
		return result;

	}

	public static String ByMD5() {
		return null;
	}

	public static void main(String[] args) throws IOException {
		byte[] bytes = "A".getBytes("utf-8");
		for (int i = 0; i < bytes.length; i++) {
			System.out.println(bytes[i]);
		}
		System.out.println(Integer.toHexString(65));
		System.out.println("A Base64 编码后 :"
				+ ConverterUtils.Base64Encode("A", "UTF-8"));
		System.out.println("A Base64 解码后 :"
				+ ConverterUtils.Base64Decode(
						ConverterUtils.Base64Encode("A", "UTF-8"), null));
	}

}
