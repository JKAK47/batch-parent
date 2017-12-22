package common;

import java.math.BigDecimal;

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
     * @param parama
     * @param paramb
     * @return
     */
    public  static  double add(double parama, double paramb){
        BigDecimal bigDecimala=new BigDecimal(parama);
        BigDecimal bigDecimalb=new BigDecimal(paramb);
        return bigDecimala.add(bigDecimalb).doubleValue();
    }

    /**
     * double 精确相减
     * @param parama
     * @param paramb
     * @return
     */
    public  static  double sub(double parama, double paramb){
        BigDecimal bigDecimala=new BigDecimal(parama);
        BigDecimal bigDecimalb=new BigDecimal(paramb);
        return bigDecimala.subtract(bigDecimalb).doubleValue();
    }
    /**
     * double 精确相乘
     * @param parama
     * @param paramb
     * @return
     */
    public  static  double mul(double parama, double paramb){
        BigDecimal bigDecimala=new BigDecimal(parama);
        BigDecimal bigDecimalb=new BigDecimal(paramb);
        return bigDecimala.multiply(bigDecimalb).doubleValue();
    }

    public static void main(String[] args){
        System.out.println(add(121.454,545.15454));
        System.out.println(sub(121.454,545.15454));
    }
}
