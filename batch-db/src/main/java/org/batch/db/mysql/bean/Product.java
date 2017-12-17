package org.batch.db.mysql.bean;

/**
 * Package: org.batch.db.mysql.bean
 * Description： TODO
 * Author: PengRong
 * Date: Created in 2017/12/13 0:52
 * Company: PLCC
 * Copyright: Copyright (c) 2017
 * Version: 1.0
 * Modified By:
 * Created by PengRong on 2017/12/13.
 */

public class Product {
		private String prod_id;
		private int vend_id;
		private String prod_name;
		private double prod_price;
		private String prod_desc;

		public int getVend_id() {
				return vend_id;
		}

		public void setVend_id(int vend_id) {
				this.vend_id = vend_id;
		}

		@Override
		public String toString() {
				return "Product{" +
								"prod_id='" + prod_id + '\'' +
								", vend_id='" + vend_id + '\'' +
								", prod_name='" + prod_name + '\'' +
								", prod_price=" + prod_price +
								", prod_desc='" + prod_desc + '\'' +
								'}';
		}

		public String getProd_id() {
				return this.prod_id;
		}

		public void setProd_id(String prod_id) {
				this.prod_id = prod_id;
		}

		public String getProd_name() {
				return this.prod_name;
		}

		public void setProd_name(String prod_name) {
				this.prod_name = prod_name;
		}

		public double getProd_price() {
				return this.prod_price;
		}

		public void setProd_price(double prod_price) {
				this.prod_price = prod_price;
		}

		public String getProd_desc() {
				return this.prod_desc;
		}

		public void setProd_desc(String prod_desc) {
				this.prod_desc = prod_desc;
		}
}
