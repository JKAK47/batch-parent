package org.batch.db.mysql.bean;

/**
 * Package: org.batch.db.mysql.bean
 * Description： TODO
 * Author: PengRong
 * Date: Created in 2017/12/17 21:17
 * Company: PLCC
 * Copyright: Copyright (c) 2017
 * Version: 1.0
 * Modified By:
 * Created by PengRong on 2017/12/17.
 */

public class Student {
		private int id;
		private String name;
		private String address;
		private int age;

		public int getId() {
				return this.id;
		}

		public void setId(int id) {
				this.id = id;
		}

		public String getName() {
				return this.name;
		}

		public void setName(String name) {
				this.name = name;
		}

		public String getAddress() {
				return this.address;
		}

		public void setAddress(String address) {
				this.address = address;
		}

		public int getAge() {
				return this.age;
		}

		public void setAge(int age) {
				this.age = age;
		}

		@Override
		public String toString() {
				return "Student [id=" + this.id + ", name=" + this.name + ", address="
								+ this.address + ", age=" + this.age + "]";
		}

}
