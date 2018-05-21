package org.batch.java8.chap1;

/**
 * @Package: org.batch.java8.chap1 <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/5/20 17:38 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/5/20. <br/>
 */
public  class Apple {
		private int weight = 0;
		private String color = "";

		public Apple(int weight, String color) {
				this.weight = weight;
				this.color = color;
		}

		public Integer getWeight() {
				return this.weight;
		}

		public void setWeight(Integer weight) {
				this.weight = weight;
		}

		public String getColor() {
				return this.color;
		}

		public void setColor(String color) {
				this.color = color;
		}

		@Override
		public String toString() {
				return "Apple{" + "color='" + this.color + '\'' + ", weight="
								+ this.weight + '}';
		}
}