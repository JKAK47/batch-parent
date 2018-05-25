package org.batch.java8.annotation.app;

import org.batch.java8.annotation.MethodInfo;

/**
 * @Package: org.batch.java8.annotation.app <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/5/26 2:02 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/5/26. <br/>
 */

public class App {
		//使用自定义注解
		@MethodInfo(author = "Cindy", date = "day", version = 2)
		public static void main(String[] args) {
				System.out.println("Hello World!");
		}
}
