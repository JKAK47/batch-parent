package org.batch.java8;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		SpringImpl impl = new SpringImpl();
		System.out.println(impl.getName());
		System.out.println(impl.Search());
	}
}
