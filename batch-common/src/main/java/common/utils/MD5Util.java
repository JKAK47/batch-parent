package common.utils;


import common.utils.base64.ApacheBase64Utli;
import org.slf4j.Logger;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * batch-parent.common.utils.base64 <br/>
 * Created by PengRong on 2017/12/18. <br/>
 *
 * @author PengRong <br/>
 * @Description MD5摘要算法第五版 java实现; 消息摘要算法有两个显著特点：第一，两个不同的报文序列难以生成相同的摘要字符串；第二，难于通过摘要字符串反推报文信息。<br/>
 * 将一个任意长度的“字节串”通过一个不可逆的字符串变换算法变换成一个128 bit 的大整数.
 * @ClassName: ${MD5Util}<br/>
 * @since 2017-12-18 14:45 <br/>
 */
public class MD5Util {
		private static final Logger logger = LoggerFactory.getLogger();
		protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		protected static MessageDigest messagedigest = null;

		static {
				try {
						messagedigest = MessageDigest.getInstance("MD5");
				} catch (NoSuchAlgorithmException e) {
						logger.info("MD5FileUtil messagedigest初始化失败");
				}
		}

		/**
		 * 算法步骤：
		 * - 填充消息，使的消息长度（比特位）为比 512 位的倍数仅小64 位的数；填充方法是附一个 1 在消息后面，后接任意个 0，知道满足条件。
		 * - 在上一步填充信息完成后，消息长度在添加 64 bit。这 64 bit记录消息长度。
		 * - 上面两步使的最后消息长度为 512 位的整数倍
		 * - 在一些初始化处理后，MD5以512位分组来处理输入文本，每一分组又划分为16个32位子分组。算法的输出由四个32位分组组成，将它们级联形成一个128位散列值
		 * -
		 */

		//下面是利用JDK平台 自有的消息摘要算法实现的Md5 算法。

		public static String getFileMD5String(File file) throws IOException {
				FileInputStream in = new FileInputStream(file);
				FileChannel ch = in.getChannel();
				MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
				messagedigest.update(byteBuffer);
				return bufferToHex(messagedigest.digest());
		}
		//调用函数：String str="0123456789"System.out.println(EncoderByMd5(str));输出：eB5eJF1ptWaXm4bijSPyxw==

		public static String getFileMD5String(InputStream inputStream) throws IOException {
				FileInputStream in = (FileInputStream) inputStream;
				FileChannel ch = in.getChannel();
				MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, in.available());
				messagedigest.update(byteBuffer);
				return bufferToHex(messagedigest.digest());
		}

		public static String getMD5String(String s) {
				return getMD5String(s.getBytes());
		}

		/**
		 * 根据字节数组获取到字节数组的数字指纹
		 *
		 * @param bytes
		 * @return
		 */
		public static String getMD5String(byte[] bytes) {
				messagedigest.update(bytes);
				return bufferToHex(messagedigest.digest());
		}

		/**
		 * 将一个字节数组转换为 16进制形式的字符串
		 *
		 * @param bytes
		 * @return
		 */
		private static String bufferToHex(byte bytes[]) {
				return bufferToHex(bytes, 0, bytes.length);
		}

		/**
		 * 对bytes 字节数组每一个字节转换为 16进制 字符
		 *
		 * @param bytes
		 * @param m
		 * @param n
		 * @return
		 */
		private static String bufferToHex(byte bytes[], int m, int n) {
				StringBuffer stringbuffer = new StringBuffer(2 * n);
				int k = m + n;
				for (int l = m; l < k; l++) {
						appendHexPair(bytes[l], stringbuffer);
				}
				return stringbuffer.toString();
		}

		/**
		 * 将一个字节的高低 4个比特位 转换为16 进制形式并存放到stringbuffer 变量中</br>
		 *
		 * @param bt           字节 </br>
		 * @param stringbuffer 存放十六进制地方 </br>
		 */
		private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
				//取字节中高 4 位的bit 转换
				char c0 = hexDigits[(bt & 0xf0) >> 4];
				//取字节中低 4 位的bit 转换
				char c1 = hexDigits[bt & 0xf];
				stringbuffer.append(c0);
				stringbuffer.append(c1);
		}

		public static boolean checkPassword(String password, String md5PwdStr) {
				String s = getMD5String(password);
				return s.equals(md5PwdStr);
		}

		public static void main(String[] args) throws Exception {
				String md5 = getMD5String("aasdfsdf");
				System.out.println(md5);
				System.out.println(md5.length());//32
				System.out.println(md5.length() / 2);//16
				long begin = System.currentTimeMillis();
				File big = new File("c:/keystoreOutput2.txt");
				md5 = getFileMD5String(big);
				FileInputStream in = new FileInputStream(big);
				String md52 = getFileMD5String(in);

				long end = System.currentTimeMillis();
				System.err.println("md5:" + "384ba3f72ec5658f27533ca922dac5cc");
				System.out.println("md5 :" + md5);
				System.out.println("md52:" + md5);
				System.out.println("time:" + ((end - begin) / 1000) + "s");
		}

		/**
		 * 利用MD5进行加密
		 *
		 * @param str 待加密的字符串
		 * @return 加密后的字符串
		 * @throws NoSuchAlgorithmException     没有这种产生消息摘要的算法
		 * @throws UnsupportedEncodingException
		 */
		public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
				//确定计算方法
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				ApacheBase64Utli base64en = new ApacheBase64Utli();
				//加密后的字符串再用Base64 进行编码
				String newstr = base64en.Base64Encode(md5.digest(str.getBytes("utf-8")));
				return newstr;
		}

		/**
		 * 判断用户密码是否正确
		 *
		 * @param newpasswd 用户输入的密码
		 * @param oldpasswd 数据库中存储的密码－－用户密码的摘要
		 * @return
		 * @throws NoSuchAlgorithmException
		 * @throws UnsupportedEncodingException
		 */
		public boolean checkpassword(String newpasswd, String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
				if (EncoderByMd5(newpasswd).equals(oldpasswd))
						return true;
				else
						return false;
		}


}
