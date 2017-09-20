package org.batch.java8.functioninterface;

/**
 * 函数式接口，只能有一个抽象方法的接口；默认方法可以有很多；
 * 
 * @author liuhy
 *
 */
@FunctionalInterface
public interface Spring {

	public String getName();

	/**
	 * 默认 方法
	 * 
	 * @return
	 */
	default public String Search() {
		return "XXAA";
	}
}
