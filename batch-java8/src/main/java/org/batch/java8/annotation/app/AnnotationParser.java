package org.batch.java8.annotation.app;

import java.lang.reflect.Method;
import org.batch.java8.annotation.MethodInfo;

/**
 * @Package: org.batch.java8.annotation.app <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/5/26 2:19 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/5/26. <br/>
 *
 *
 *
 * 获取自定义注解 MethodInfo 类型修改 App.java 类方法属性值。
 * 做这个测试时候务必将MethodInfo 注解 Retention 方法设置为 RetentionPolicy.RUNTIME
 */

public class AnnotationParser {
		public static void main(String[] args) {
				Class<? extends App> class1 = App.class;
				for (Method method : class1.getMethods()) {
						MethodInfo methodInfo;
						// 返回注解这个方法的指定注解
						methodInfo = method.getAnnotation(MethodInfo.class);
						if (methodInfo != null) {
								System.out.println("方法名： "+method.getName());
								// 获取指定注解指定方法的值
								System.out.println("MethodInfo 注解author属性： "+methodInfo.author());
								System.out.println("MethodInfo 注解date属性： "+methodInfo.date());
								System.out.println("MethodInfo 注解version属性： "+methodInfo.version());
						}
				}
		}
}
