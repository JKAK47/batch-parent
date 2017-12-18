package common.utils.base64;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * batch-parent.common.utils.base64 <br/>
 * Created by PengRong on 2017/12/18. <br/>
 *
 * @author PengRong <br/>
 * @Description MD5摘要算法第五版 java实现; 消息摘要算法有两个显著特点：第一，两个不同的报文序列难以生成相同的摘要字符串；第二，难于通过摘要字符串反推报文信息。<br/>
 * @ClassName: ${MD5Util}<br/>
 * @since 2017-12-18 14:45 <br/>
 */
public class MD5Util {

    /**
     * 算法步骤：
     * - 填充消息，使的消息长度（比特位）为比 512 位的倍数仅小64 位的数；填充方法是附一个 1 在消息后面，后接任意个 0，知道满足条件。
     * - 在上一步填充信息完成后，消息长度在添加 64 bit。这 64 bit记录消息长度。
     * - 上面两步使的最后消息长度为 512 位的整数倍
     * - 在一些初始化处理后，MD5以512位分组来处理输入文本，每一分组又划分为16个32位子分组。算法的输出由四个32位分组组成，将它们级联形成一个128位散列值
     * -
     */

    //下面是利用JDK平台 自有的消息摘要算法实现的Md5 算法。

    /**
     * 利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException
     * */
    public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //确定计算方法
        MessageDigest md5= MessageDigest.getInstance("MD5");
        ApacheBase64Utli base64en = new ApacheBase64Utli();
        //加密后的字符串再用Base64 进行编码
        String newstr=base64en.Base64Encode(md5.digest(str.getBytes("utf-8")));
        return newstr;    }
        //调用函数：String str="0123456789"System.out.println(EncoderByMd5(str));输出：eB5eJF1ptWaXm4bijSPyxw==
    /**
     * 判断用户密码是否正确
     * @param newpasswd  用户输入的密码
     * @param oldpasswd  数据库中存储的密码－－用户密码的摘要
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * */
    public boolean checkpassword(String newpasswd,String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if(EncoderByMd5(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }

    public  static  void  main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MD5Util md5Util=new MD5Util();
        String password="0123456789";
        String md5=md5Util.EncoderByMd5(password);
        System.out.println(md5);//eB5eJF1ptWaXm4bijSPyxw==
        System.out.println(md5Util.checkpassword(password,md5));//true
    }
}
