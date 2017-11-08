package org.batch.java8;

import org.batch.java8.functioninterface.SpringImpl;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		/**
		 * 调用函数式接口
		 */
		SpringImpl impl = new SpringImpl();
		System.out.println(impl.getName());
		System.out.println(impl.Search());
		System.out.println("hello world");
	}
}
