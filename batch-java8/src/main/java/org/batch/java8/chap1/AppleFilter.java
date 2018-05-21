package org.batch.java8.chap1;

/**
 * @Package: org.batch.java8.chap1 <br/>
 * @Description： 自定义苹果比较逻辑，通过方法引用去调用这里的代码 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/5/20 23:13 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/5/20. <br/>
 */

public class AppleFilter {
		public static boolean isGreenApple(Apple apple) {
				return "green".equals(apple.getColor());
		}

		public static boolean isHeavyApple(Apple apple) {
				return apple.getWeight() > 10;
		}
}
