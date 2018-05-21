package org.batch.java8;

import java.io.File;
import java.io.FileFilter;

/**
 * @Package: org.batch.java8 <br/>
 * @Description： 将函数参数化传递给函数，达到可以传递函数代码的目的。是java8 核心新特性。是行为参数化把代码传递给方法的新尝试。  <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/5/19 23:45 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/5/19. <br/>
 */

public class ParamToMethodTest {
		public static void main(String[] args){
				//以前的做法 ，为了将 file.isHidden()方法传递给listFiles方法
				File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
						@Override
						public boolean accept(File file) {
								return file.isHidden();
						}
				});
				// 将本是匿名内部类的FileFilter 实例给用方法引用替换了。 java 8 方法引用语法(::) 传递方法代码
				File[] hiddenfiles=new  File(".").listFiles(File::isHidden);
		}
}
