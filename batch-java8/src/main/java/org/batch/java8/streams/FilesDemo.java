package org.batch.java8.streams;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 */
public class FilesDemo {

	public static void main(String[] args) throws IOException, URISyntaxException {
		String bString = String.join(" ", "中国sdfsdf", "美fsd国dfsdfdsf", "日本");
		System.out.println(bString);

		System.out.println();
		
		/**
		 * 读取classpath目录下的资源文件  使用ClassLoader.getSystemResource 方法获取 。
		 */
		Path path1 = Paths.get(ClassLoader.getSystemResource("资源汇总.md").toURI());
		List<String> content = Files.readAllLines(path1);
		for (String string : content) {
			System.out.println(string);
		}
	}
}
