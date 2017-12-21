package common;

import common.utils.LoggerFactory;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Package: common <br/>
 * Description： 一些杂七杂八的公共方法 <br/>
 *
 * @author: PengRong <br/>
 * Date: Created in 2017/12/21 23:32 <br/>
 * Company: PLCC <br/>
 * Copyright: Copyright (c) 2017 <br/>
 * Version: 1.0 <br/>
 * Modified By: <br/>
 * Created by PengRong on 2017/12/21. <br/>
 */

public class CommonLib {
		static {
				Logger logger = LoggerFactory.getLogger();
		}

		/**
		 * 以###,###.00 格式将一个dubbo 返回为一个字符串（两个小数点，整数以 逗号分隔，每组三个字符）。
		 * @param value
		 * @return
		 */
		public static String formatDouble(double value) {
				BigDecimal bigDecimal=new BigDecimal(value);
				DecimalFormat format=new DecimalFormat();
				format.applyPattern("###,###.00");
				String resutlt=format.format(bigDecimal);
				return resutlt;
		}

		public static void main(String[] args) {
				System.out.println(formatDouble(121154.156));
		}
}
