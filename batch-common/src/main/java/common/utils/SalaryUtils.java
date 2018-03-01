package common.utils;

import common.ExactCompute;
import common.utils.bean.RateBean;
import common.utils.bean.SalaryBean;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Salary Utility
 */
public class SalaryUtils {
		/**
		 * pension rate社保保险比率
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
		/**
		 * 扣除缴税基数 3500
		 */
		private static final String TAX_BASE = "3500";

		public static void main(String[] args) {
				System.out.println(defaultSalary("11000","200","0"));
		}

		/**
		 * 计算薪水的默认方法
		 * @param baseSalary 工资基数
		 * @param bounty 需要缴税的津贴
		 * @param butie 不需要缴税的津贴
		 * @return 返回税后工资
		 */
		public static String defaultSalary(String baseSalary, String bounty, String butie) {
				Map<String, String> map = new HashMap<>(4);
				map.put(PENSION_RATE, "0.08");
				map.put(MEDICAL_INSURANCE_RATE, "0.02");
				map.put(UNEMPLOYMENT_INSURANCE_RATE, "0.005");
				map.put(COMMON_RESERVE_FUNDS_RATE, "0.12");
				BigDecimal salary = computeTax(baseSalary, bounty, map);
				return ExactCompute.formatBigDecimal(salary.add(new BigDecimal(butie)));
		}

		/**
		 * 计算税后工资
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
				/** 计算缴税 */
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
		 * @param bigBaseSalary 工资基数
		 * @param bigBounty     津贴
		 * @param bean          五险一金
		 */
		private static BigDecimal conputteTax(BigDecimal bigBaseSalary, BigDecimal bigBounty, SalaryBean bean) {
				BigDecimal result = bigBaseSalary.add(bigBounty);
				result = result.subtract(bean.getPension());
				result = result.subtract(bean.getMedical_insurance());
				result = result.subtract(bean.getUnemployment_insurance());
				result = result.subtract(bean.getCommon_reserve_funds());

				result = result.subtract(new BigDecimal(TAX_BASE));
				RateBean rate = getRateData(result);
				result = result.multiply(new BigDecimal(rate.getRate()));
				return result.subtract(new BigDecimal(rate.getSusuan()));
		}

		/**
		 * 根据应缴税额计算 税率以及速算扣除数
		 *
		 * @param result
		 * @return
		 */
		private static RateBean getRateData(BigDecimal result) {
				// 税率和 速算扣除数
				RateBean bean = new RateBean();
				if (result.subtract(new BigDecimal(1500)).doubleValue() <= 0) {
						bean.setRate("0.03");
						bean.setSusuan("0");
				} else if (result.subtract(new BigDecimal(4500)).doubleValue() <= 0) {
						bean.setRate("0.1");
						bean.setSusuan("105");
				} else if (result.subtract(new BigDecimal(9000)).doubleValue() <= 0) {
						bean.setRate("0.2");
						bean.setSusuan("555");
				} else if (result.subtract(new BigDecimal(35000)).doubleValue() <= 0) {
						bean.setRate("0.25");
						bean.setSusuan("1005");
				} else if (result.subtract(new BigDecimal(55000)).doubleValue() <= 0) {
						bean.setRate("0.3");
						bean.setSusuan("2755");
				} else if (result.subtract(new BigDecimal(80000)).doubleValue() <= 0) {
						bean.setRate("0.35");
						bean.setSusuan("5505");
				} else {
						bean.setRate("0.45");
						bean.setSusuan("13505");
				}
				return bean;
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
				/** 养老保险 */
				if (StringUtils.isNotBlank(pension_rate)) {
						pension = bigSaseSalary.multiply(new BigDecimal(pension_rate));
				} else {
						pension = new BigDecimal(0);
				}
				/** 医疗保险 */
				if (StringUtils.isNotBlank(medical_insurance_rate)) {
						medical_insurance = bigSaseSalary.multiply(new BigDecimal(medical_insurance_rate));
				} else {
						medical_insurance = new BigDecimal(0);
				}
				/** 失业保险；缴费基数 以深圳市最低工资 */
				if (StringUtils.isNotBlank(unemployment_insurance_rate)) {
						BigDecimal base=new BigDecimal("2130");//深圳市工资最低标准 2130
						unemployment_insurance = base.multiply(new BigDecimal(unemployment_insurance_rate));
				} else {
						unemployment_insurance = new BigDecimal(0);
				}
				/** 公积金 */
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
