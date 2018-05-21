package org.batch.java8.chap1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Package: org.batch.java8.chap1 <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/5/20 17:37 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/5/20. <br/>
 */

public class FilteringApples {
		public static void main(String[] args){
				//用filter 刷选器选出需要的苹果
				List<Apple> apples=new ArrayList<>();
				apples.add(new Apple(10,"Red"));
				apples.add(new Apple(15,"Block"));
				apples.add(new Apple(20,"yellow"));
				apples.add(new Apple(25,"blue"));
				apples.add(new Apple(30,"white"));
				apples.add(new Apple(40,"green"));
				// filterGreenApples方法安装苹果的颜色刷选
				System.out.println(filterGreenApples(apples));
				// filterHeavyApples方法安装苹果的颜色刷选
				System.out.println(filterHeavyApples(apples));
				//上面只是对苹果的两个属性进行刷选，就需要添加两个不同的刷选方法。两个刷选方法只是有一行代码不同

				//通过 Predicate 传递自定义比较逻辑代码，下面两种调用方式都是传递方法代码的形式
				System.out.println(filterApples(apples,AppleFilter::isGreenApple));
				System.out.println(filterApples(apples,AppleFilter::isHeavyApple));
				// 上面传递的方法代码中的代码都是只有一行，简单的代码。可以用 Lambda表达式 代替方法代码
				System.out.println(filterApples(apples,(Apple a) -> "green".equals(a.getColor())));
				System.out.println(filterApples(apples,(Apple a) ->  a.getWeight() > 15));
		}

		/**
		 * 第一个参数 是待刷选的Apple List
		 * 第二个参数  Predicate<Apple> ,谓词，表示为一个函数的东西，接受一个参数并返回true 和 false 。其实就是传递可以返回true或者false的方法给他即可。比如AppleFilter::isGreenApple
		 * 或者AppleFilter::isHeavyApple
		 * @param inventory
		 * @param p
		 * @return
		 */
		public static List<Apple> filterApples(List<Apple> inventory,
		                                       Predicate<Apple> p) {
				List<Apple> result = new ArrayList<>();
				for (Apple apple : inventory) {
						if (p.test(apple)) {
								result.add(apple);
						}
				}
				return result;
		}

		/**
		 * 传递进一个apple 列表，然后 通过苹果颜色刷选
		 * @param inventory
		 * @return
		 */
		public static List<Apple> filterGreenApples(List<Apple> inventory) {
				List<Apple> result = new ArrayList<>();
				for (Apple apple : inventory) {
						if ("green".equals(apple.getColor())) {
								result.add(apple);
						}
				}
				return result;
		}

		/**
		 * * 传递进一个apple 列表，然后 通过苹果重量刷选
		 * @param inventory
		 * @return
		 */
		public static List<Apple> filterHeavyApples(List<Apple> inventory) {
				List<Apple> result = new ArrayList<>();
				for (Apple apple : inventory) {
						if (apple.getWeight() > 150) {
								result.add(apple);
						}
				}
				return result;
		}
}
