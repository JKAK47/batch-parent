package common.utils.bean;

/**
 * @Package: common.utils.bean <br/>
 * @Description： 缴税税率以及速算扣除数Bean <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/1/11 23:44 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/1/11. <br/>
 */

public class RateBean {
		/** 税率*/
		String rate = null;
		/** 速算扣除数 */
		String susuan = null;
		public String getRate() {
				return rate;
		}

		public void setRate(String rate) {
				this.rate = rate;
		}

		public String getSusuan() {
				return susuan;
		}

		public void setSusuan(String susuan) {
				this.susuan = susuan;
		}
}
