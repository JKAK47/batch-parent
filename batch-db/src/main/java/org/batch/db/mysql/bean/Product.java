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
		private String productId;
		private int vendId;
		private String productName;
		private double productPrice;
		private String productDsc;

		public String toString() {
				return "Product [productId=" + this.productId + ", vend_id="
								+ this.vendId + ", productName=" + this.productName
								+ ", productPrice=" + this.productPrice + ", productDsc="
								+ this.productDsc + "]";
		}

		public String getProductId() {
				return this.productId;
		}

		public void setProductId(String productId) {
				this.productId = productId;
		}

		public int getVendId() {
				return this.vendId;
		}

		public void setVendId(int vendId) {
				this.vendId = vendId;
		}

		public String getProductName() {
				return this.productName;
		}

		public void setProductName(String productName) {
				this.productName = productName;
		}

		public double getProductPrice() {
				return this.productPrice;
		}

		public void setProductPrice(double productPrice) {
				this.productPrice = productPrice;
		}

		public String getProductDsc() {
				return this.productDsc;
		}

		public void setProductDsc(String productDsc) {
				this.productDsc = productDsc;
		}
}
