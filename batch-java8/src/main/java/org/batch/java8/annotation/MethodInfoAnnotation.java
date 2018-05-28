package org.batch.java8.annotation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * @Package: org.batch.java8.annotation <br/>
 * @Description： <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/5/26 2:07 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/5/26. <br/>
 *
 *
 * 给MethodInfo注解添加处理器  ，暂时还没有发现怎么让这个注解处理器调用起来。
 *
 */
@SupportedAnnotationTypes(value = {"org.batch.java8.annotation.MethodInfo"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MethodInfoAnnotation extends AbstractProcessor{
		@Override
		public synchronized void init(ProcessingEnvironment processingEnv) {
				// TODO Auto-generated method stub
				super.init(processingEnv);
				System.out.println("MethodInfo Annotation . "+processingEnv.toString());
		}
		/**
		 * {@inheritDoc}
		 *
		 * @param annotations
		 * @param roundEnv
		 */
		@Override
		public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
				System.out.println("MethodInfoAnnotation process  ...");
				Map<String, String> map = new HashMap<>(16);
				for (TypeElement e : annotations) {
						MethodInfo mi = e.getAnnotation(MethodInfo.class);
						map.put(e.getEnclosingElement().toString(), mi.author());
						System.out.println(map);
						System.out.println("xxxx");
				}
				if (!roundEnv.processingOver()) {
						processingEnv.getMessager().printMessage( // 注释处理器的报告
										Diagnostic.Kind.NOTE, "Hello World MethodInfo!");
				}
				return true;
		}
}
