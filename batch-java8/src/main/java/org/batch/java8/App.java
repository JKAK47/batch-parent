package org.batch.java8;

import javax.naming.NamingException;
import org.batch.java8.functioninterface.SpringImpl;

/**
 * Hello world!
 * JNDI 文章：https://www.ibm.com/developerworks/cn/java/j-jndi/
 */
public class App {
		public static void main(String[] args) throws NamingException {
				/**
				 * 调用函数式接口
				 */
				SpringImpl impl = new SpringImpl();
				System.out.println(impl.getName());
				System.out.println(impl.Search());
				System.out.println("hello world");


		}
}
