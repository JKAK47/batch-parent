package common.tests;

import java.lang.reflect.Field;

/**
 * batch-parent.common.tests <br/>
 * Created by PengRong on 2017/11/8. <br/>
 *
 * @author PengRong <br/>
 * @Description TODO( 测试 java 传值 传址)
 * @ClassName: Reflact
 * @since 2017-11-08 10:14 <br/>
 */
public class Reflact {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Integer a=1;
        Integer b=2;
        System.out.println("before a= "+a+"\tb= "+b);
        swap3(a,b);
        System.out.println("after a= "+a+"\tb= "+b);

    }

    /**
     * author: PengRong<br/>
     * Desc: 不能完成交换两个变量值的情况
     * @param a
     * @param b
     */
    private static void swap1(Integer a, Integer b) {
        Integer tmp=a;
        a=b;
        b=tmp;
    }

    /**
     * author: PengRong<br/>
     * Desc: 基于两个变量的反射,最后一步交换 b变量时候 需要对tmp 变量new Integer(tmp) 封装；
     * 因为我们的a,b变量都是出于[-128,127] 区间的数据，integer对于这个区间的数据是进行了优化。从一个cache里面存取。
     * field.set(a,b); 这个语句执行完后，a（1）位置下标的值变成了b(2). 然后第二句如果是 field.set(b,tmp);tmp=1 ,相当于从cache取
     * 下表为1+（128）的值，而上面我们已经知道了变成了2.所以b还是2.为解决这个问题我们需要 field.set(b,new Integer(tmp));
     * @param a 一个整数<br/>
     * @param b 一个整数<br/>
     */
    private static void swap2(Integer a, Integer b) throws NoSuchFieldException, IllegalAccessException {
            Field field=Integer.class.getDeclaredField("value");
            int tmp=a.intValue();
            field.setAccessible(true);
            field.set(a,b);
            field.set(b,new Integer(tmp));
    }

    /**
     * 另一种方式是设置时候直接通过setInt 方法。就不用再次
     * @param a
     * @param b
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static void swap3(Integer a, Integer b) throws NoSuchFieldException, IllegalAccessException {
        Field field=Integer.class.getDeclaredField("value");
        int tmp=a.intValue();
        field.setAccessible(true);//修改跳过权限检查。
        field.setInt(a,b);
        field.setInt(b,tmp);
    }
}
