package common;

/**
 * batch-parent.common <br/>
 * Created by PengRong on 2018/1/23. <br/>
 * 关于递归和尾递归的说明，以阶乘计算为例。
 *
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-01-23 13:24 <br/>
 */
public class FactorialRecursion {

    /**
     * 递归调用，会多次创建并使用栈帧
     *
     * @param n
     * @return
     */
    public static long factorial(long n) {
        if (n == 1){
            return 1;
        }
        else{
            return n * factorial(n - 1);
        }
    }

    /**
     * 尾递归， 函数递归调用自己并且它的返回值不属于表达式一部分时， 那么就不需要再次返回来计算结果了。这个递归方式就是尾递归。
     * 尾递归，递归调用过程中可以共用一个栈帧，所以没有栈帧溢出风险；
     * @param n
     * @param result
     * @return
     */
    public static long factorial(long n, long result) {
        if (n == 1){
            return result;
        }
        else{
            return factorial(n - 1, n * result);
        }
    }


    public static void main(String[] args) {
        long time=System.currentTimeMillis();
        System.out.println(factorial(50));
        System.out.println((System.currentTimeMillis()-time)/1000);
        time=System.currentTimeMillis();
        System.out.println(factorial(50,1));
        System.out.println((System.currentTimeMillis()-time)/1000);

    }
}
