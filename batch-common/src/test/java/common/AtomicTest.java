package common;

import common.utils.LoggerFactory;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;

/**
 * @Package: common <br/>
 * @Description： 原子类型Atomic 类  <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/1/10 23:09 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/1/10. <br/>
 */

public class AtomicTest {
		private static Logger logger = LoggerFactory.getLogger();

		public static void main(String[] args) {
				//1、第一种：基本类型原子操作
				logger.info("第一种：基本类型原子操作");
				AtomicInteger atomicInteger = new AtomicInteger(10);
				int a1 = atomicInteger.getAndAdd(5);
				int a2 = atomicInteger.addAndGet(5);
				int a3 = atomicInteger.decrementAndGet();
				int a4 = atomicInteger.getAndDecrement();
				int a5 = atomicInteger.get();
				System.out.println("a1=" + a1 + "; a2=" + a2 + "; a3=" + a3 + "; a4=" + a4 + "; a5=" + a5);
				//2、 第二种：引用类型原子操作类
				logger.info("第二种：引用类型原子操作类，原子类包含引用类型变量");
				AtomicReference<Book> atomicBook = new AtomicReference<Book>();
				AtomicTest.Book book = new AtomicTest.Book("java", "pengrong");
				atomicBook.set(book);
				System.out.println(atomicBook.get());
				Book book1 = new Book("C++", "Jack Ma");
				atomicBook.compareAndSet(book, book1);// atomicBook 内部缓存的实例如果和book实例一致，则更新为book1
				System.out.println(atomicBook.get());
				// 3、 第三种，数组类型原子操作类
				logger.info("第三种：数组类型原子操作类");
				int[] array = new int[]{1, 2, 3, 4, 5};
				System.out.println(Arrays.toString(array));
				AtomicIntegerArray aIA = new AtomicIntegerArray(array);//根据传进来的数组参数，新建一个新的数组；
				aIA.getAndSet(2, 123);
				System.out.println(aIA.toString());// atomic 数组改变了
				System.out.println(Arrays.toString(array));// 但是原来的数组array 并没有变
				System.out.println(aIA.get(2)); // 123
				logger.info("第四种：属性类型原子操作类，原子类包含引用类型，但是我们需要对引用类型某个属性修改设置原型性");
				//对于需要更新的属性需要用public volatile进行修饰
//更新Book类的price属性
				AtomicIntegerFieldUpdater<Book> au =
								AtomicIntegerFieldUpdater.newUpdater(Book.class, "price");
				Book book2 = new Book("划重点", "alibaba");
				book2.setPrice(12);
				System.out.println(book2.getPrice());
				//更新值为 10；
				au.addAndGet(book1, 10);
				//获取更新的值
				int newPrice = au.get(book1);
				// 旧值 : 新值
				System.out.println(book2.getPrice() + " : " + newPrice);
		}

		public static class Book {
				//需要更新的属性需要用public volatile进行修饰
				public volatile int price;
				private String name;
				private String author;
				public Book(String n, String a) {
						this.name = n;
						this.author = a;
				}
				public int getPrice() {
						return price;
				}
				public void setPrice(int price) {
						this.price = price;
				}
				public String getName() {
						return this.name;
				}
				public String getAuthor() {
						return this.author;
				}
				@Override
				public String toString() {
						return "Book{" +
										"name='" + name + '\'' +
										", author='" + author + '\'' +
										'}';
				}
		}

}
