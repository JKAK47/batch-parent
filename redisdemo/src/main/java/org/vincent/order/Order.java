package org.vincent.order;

import java.io.Serializable;
import java.util.Date;

/**
 * @Package: org.vincent.order <br/>
 * @Description： [普通工程集成](https://my.oschina.net/u/2312022/blog/741502?utm_medium=referral) <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/3/11 1:36 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/3/11. <br/>
 */

public class Order implements Serializable{
		private static final long serialVersionUID = 1L;

		private String id;
		private String orderNo;
		private double price;
		private Date createDate;

		@Override
		public String toString() {
				return "Order{" +
								"id='" + id + '\'' +
								", orderNo='" + orderNo + '\'' +
								", price=" + price +
								", createDate=" + createDate +
								'}';
		}

		public static long getSerialVersionUID() {
				return serialVersionUID;
		}

		public String getId() {
				return id;
		}

		public void setId(String id) {
				this.id = id;
		}

		public String getOrderNo() {
				return orderNo;
		}

		public void setOrderNo(String orderNo) {
				this.orderNo = orderNo;
		}

		public double getPrice() {
				return price;
		}

		public void setPrice(double price) {
				this.price = price;
		}

		public Date getCreateDate() {
				return createDate;
		}

		public void setCreateDate(Date createDate) {
				this.createDate = createDate;
		}
}
