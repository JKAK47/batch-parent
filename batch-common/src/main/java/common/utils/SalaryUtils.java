package common.utils;

import java.math.BigDecimal;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * Salary Utility
 */
public class SalaryUtils {
    /** pension rate养老保险比率 */
    private static  final String PENSION_RATE="pension";
    /** MEDICAL INSURANCE RATE 医疗保险 比率*/
    private static  final  String MEDICAL_INSURANCE_RATE="pension";
    /** UNEMPLOYMENT INSURANCE RATE 失业保险 比率*/
    private static  final  String UNEMPLOYMENT_INSURANCE_RATE="pension";
    /** COMMON RESERVE FUNDS RATE 公积金 比率*/
    private static  final String COMMON_RESERVE_FUNDS_RATE="pension";

    /**
     *
     * @param baseSalary 缴税薪水基数 </br>
     * @param bounty  补贴 </br>
     * @param map   五险一金各险 费率 </br>
     */
    public  static  void  computeTax(String baseSalary, String bounty,  Map<String,String> map){
        /** 养老金费率 */
        String pension_rate=map.get(SalaryUtils.PENSION_RATE);
        /** 医疗保险费率率 */
        String medical_insurance_rate=map.get(SalaryUtils.MEDICAL_INSURANCE_RATE);
        /** 失业保险费率 */
        String unemployment_insurance_rate=map.get(SalaryUtils.UNEMPLOYMENT_INSURANCE_RATE);
        /** 公积金费率 */
        String common_reserve_funds_rate=map.get(SalaryUtils.COMMON_RESERVE_FUNDS_RATE);


        BigDecimal bigSaseSalary= new BigDecimal(baseSalary);
        computeTax(bigSaseSalary,pension_rate,medical_insurance_rate,unemployment_insurance_rate,common_reserve_funds_rate);

    }

    /**
     *
     * @param bigSaseSalary 缴税薪水基数 </br>
     * @param pension_rate  养老保险费率 </br>
     * @param medical_insurance_rate 医疗保险费率 </br>
     * @param unemployment_insurance_rate 失业保险费率  </br>
     * @param common_reserve_funds_rate  公积金比率 </br>
     */
    private static void computeTax(BigDecimal bigSaseSalary, String pension_rate, String medical_insurance_rate, String unemployment_insurance_rate, String common_reserve_funds_rate) {
        if (StringUtils.isNotBlank(pension_rate)){
            BigDecimal pension=bigSaseSalary.multiply(new BigDecimal(pension_rate));
        }
    }
}
