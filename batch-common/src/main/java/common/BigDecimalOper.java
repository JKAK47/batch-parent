package common;

import java.math.BigDecimal;

import static java.lang.System.out;

/**
 * batch-parent.common <br/>
 * Created by PengRong on 2017/12/22. <br/>
 *
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2017-12-22 16:43 <br/>
 */
public class BigDecimalOper {

    /**
     * double 精确相加
     *
     * @param parama
     * @param paramb
     * @return
     */
    public static double add(double parama, double paramb) {
        BigDecimal bigDecimala = new BigDecimal(parama);
        BigDecimal bigDecimalb = new BigDecimal(paramb);
        return bigDecimala.add(bigDecimalb).doubleValue();
    }

    /**
     * double 精确相减
     *
     * @param parama
     * @param paramb
     * @return
     */
    public static double sub(double parama, double paramb) {
        BigDecimal bigDecimala = new BigDecimal(parama);
        BigDecimal bigDecimalb = new BigDecimal(paramb);
        return bigDecimala.subtract(bigDecimalb).doubleValue();
    }

    /**
     * double 精确相乘
     *
     * @param parama
     * @param paramb
     * @return
     */
    public static double mul(double parama, double paramb) {
        BigDecimal bigDecimala = new BigDecimal(parama);
        BigDecimal bigDecimalb = new BigDecimal(paramb);
        return bigDecimala.multiply(bigDecimalb).doubleValue();
    }

    public static double div(double value1, double value2, int scale) throws IllegalAccessException {
        //如果精确范围小于0，抛出异常信息
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.divide(b2, scale).doubleValue();
    }

    public static void main(String[] args) throws IllegalAccessException {
        out.println(add(121.454, 545.15454));
        out.println(sub(121.454, 545.15454));
        out.println(div(1212.222,21524.45,2));
    }
}
