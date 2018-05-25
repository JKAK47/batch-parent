package org.batch.java8.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Package: org.batch.java8.annotation <br/>
 * @Description： java 自定义注解，该注解
 * 可存在于运行时，只可注解方法。 <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/5/26 2:01 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/5/26. <br/>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface MethodInfo {

		// String author() String 表示注解author 只能赋值字符串类型的值。
		// author是MethodInfo 其中一个注解属性类型
		String author() default "Vicent";// 指定了默认值，也可以不指定

		String date() default "2017/03/03";

		int version() default 1;
}