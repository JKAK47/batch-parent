package org.vincent;

import redis.clients.jedis.Jedis;

/**
 * Java 操作Redis 简单操作之 Hello world!。
 *
 */
public class App 
{
    private static  Jedis jedis;
    private  static String HOST="112.74.63.132";
    private  static int PORT=6379;

    public static void main( String[] args )
    {
        System.out.println( "Hello World Redis!" );
        jedis=new Jedis(HOST,PORT);
        System.out.println("连接服务成功: "+jedis.toString());

        test();
    }
    /**
     * 排序
     */
    public static  void test(){
        jedis.del("number");//先删除数据，再进行测试
        jedis.rpush("number","4");//将一个或多个值插入到列表的尾部(最右边)
        jedis.rpush("number","5");
        jedis.rpush("number","3");

        jedis.lpush("number","9");//将一个或多个值插入到列表头部
        jedis.lpush("number","1");
        jedis.lpush("number","2");
        System.out.println(jedis.lrange("number",0,jedis.llen("number")));
        System.out.println("排序:"+jedis.sort("number"));
        System.out.println(jedis.lrange("number",0,-1));//不改变原来的排序
        jedis.del("number");//测试完删除数据
    }


}
