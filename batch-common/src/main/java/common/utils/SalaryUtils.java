package common.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import common.ExactCompute;
import common.utils.bean.SalaryBean;
import org.apache.commons.lang3.StringUtils;

/**
 * Salary Utility
 */
public class SalaryUtils {
    /**
     * pension rate养老保险比率
     */
    private static final String PENSION_RATE = "PENSION_RATE";
    /**
     * MEDICAL INSURANCE RATE 医疗保险 比率
     */
    private static final String MEDICAL_INSURANCE_RATE = "MEDICAL_INSURANCE_RATE";
    /**
     * UNEMPLOYMENT INSURANCE RATE 失业保险 比率
     */
    private static final String UNEMPLOYMENT_INSURANCE_RATE = "UNEMPLOYMENT_INSURANCE_RATE";
    /**
     * COMMON RESERVE FUNDS RATE 公积金 比率
     */
    private static final String COMMON_RESERVE_FUNDS_RATE = "COMMON_RESERVE_FUNDS_RATE";
    /** */
    private static final String TAX_BASE = "3500";

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put(PENSION_RATE, "0.08");
        map.put(MEDICAL_INSURANCE_RATE, "0.02");
        map.put(UNEMPLOYMENT_INSURANCE_RATE, "0.01");
        map.put(COMMON_RESERVE_FUNDS_RATE, "0.12");

        BigDecimal salary = computeTax("10000", "1700", map);
        System.out.println(ExactCompute.formatBigDecimal(salary));
    }

    /**
     * @param baseSalary 缴税薪水基数 </br>
     * @param bounty     补贴 </br>
     * @param map        五险一金各险 费率 </br>
     */
    public static BigDecimal computeTax(String baseSalary, String bounty, Map<String, String> map) {
        /** 养老金费率 */
        String pension_rate = map.get(SalaryUtils.PENSION_RATE);
        /** 医疗保险费率率 */
        String medical_insurance_rate = map.get(SalaryUtils.MEDICAL_INSURANCE_RATE);
        /** 失业保险费率 */
        String unemployment_insurance_rate = map.get(SalaryUtils.UNEMPLOYMENT_INSURANCE_RATE);
        /** 公积金费率 */
        String common_reserve_funds_rate = map.get(SalaryUtils.COMMON_RESERVE_FUNDS_RATE);


        BigDecimal bigBaseSalary = new BigDecimal(baseSalary);
        BigDecimal bigBounty = new BigDecimal(bounty);
        SalaryBean bean = computewuxianyijin(bigBaseSalary, pension_rate, medical_insurance_rate, unemployment_insurance_rate, common_reserve_funds_rate);
        /** jisuanjiaoshui */
        BigDecimal shui = conputteTax(bigBaseSalary, bigBounty, bean);
        BigDecimal result = bigBaseSalary.add(bigBounty);
        result = result.subtract(bean.getPension());
        result = result.subtract(bean.getMedical_insurance());
        result = result.subtract(bean.getUnemployment_insurance());
        result = result.subtract(bean.getCommon_reserve_funds());
        return result.subtract(shui);


    }

    /**
     * 计算交税金额
     *
     * @param bigBaseSalary
     * @param bigBounty
     * @param bean
     */
    private static BigDecimal conputteTax(BigDecimal bigBaseSalary, BigDecimal bigBounty, SalaryBean bean) {
        BigDecimal result = bigBaseSalary.add(bigBounty);
        result = result.subtract(bean.getPension());
        result = result.subtract(bean.getMedical_insurance());
        result = result.subtract(bean.getUnemployment_insurance());
        result = result.subtract(bean.getCommon_reserve_funds());

        result = result.subtract(new BigDecimal(TAX_BASE));
        String rate = "0.2";
        String susuan = "555";
        result = result.multiply(new BigDecimal(rate));
        return result.subtract(new BigDecimal(susuan));
    }

    /**
     * 计算五险一金
     *
     * @param bigSaseSalary               缴税薪水基数 </br>
     * @param pension_rate                养老保险费率 </br>
     * @param medical_insurance_rate      医疗保险费率 </br>
     * @param unemployment_insurance_rate 失业保险费率  </br>
     * @param common_reserve_funds_rate   公积金比率 </br>
     */
    private static SalaryBean computewuxianyijin(BigDecimal bigSaseSalary, String pension_rate, String medical_insurance_rate, String unemployment_insurance_rate, String common_reserve_funds_rate) {
        BigDecimal pension = null;
        BigDecimal medical_insurance = null;
        BigDecimal unemployment_insurance = null;
        BigDecimal common_reserve_funds = null;
        /** yanglaobaoxian */
        if (StringUtils.isNotBlank(pension_rate)) {
            pension = bigSaseSalary.multiply(new BigDecimal(pension_rate));
        } else {
            pension = new BigDecimal(0);
        }
        /** yiliaobaoxian */
        if (StringUtils.isNotBlank(medical_insurance_rate)) {
            medical_insurance = bigSaseSalary.multiply(new BigDecimal(medical_insurance_rate));
        } else {
            medical_insurance = new BigDecimal(0);
        }
        /** shiyebaoxian */
        if (StringUtils.isNotBlank(unemployment_insurance_rate)) {
            unemployment_insurance = bigSaseSalary.multiply(new BigDecimal(unemployment_insurance_rate));
        } else {
            unemployment_insurance = new BigDecimal(0);
        }
        /** gongjinj */
        if (StringUtils.isNotBlank(common_reserve_funds_rate)) {
            common_reserve_funds = bigSaseSalary.multiply(new BigDecimal(common_reserve_funds_rate));
        } else {
            common_reserve_funds = new BigDecimal(0);
        }
        SalaryBean bean = new SalaryBean();
        bean.setCommon_reserve_funds(common_reserve_funds);
        bean.setPension(pension);
        bean.setMedical_insurance(medical_insurance);
        bean.setUnemployment_insurance(unemployment_insurance);
        return bean;
    }
}
