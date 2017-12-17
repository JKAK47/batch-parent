package org.batch.db.mysql.mybatis.model;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.batch.db.mysql.bean.Student;

import java.util.List;

/**
 * Package: org.batch.db.mysql.mybatis.model
 * Description： Mybatis 基于Mapper 映射接口执行SQL 语句，同一功能用两种方式实现。
 * Author: PengRong
 * Date: Created in 2017/12/17 23:41
 * Company: PLCC
 * Copyright: Copyright (c) 2017
 * Version: 1.0
 * Modified By:
 * Created by PengRong on 2017/12/17.
 */

  public interface IStudentOperation {
		// 查询一个记录
		Student getStudentbyId(int id);

		@Select("select * from student where id=#{id}")
		Student getStudentbyId2(int id);

		// 查询一组记录
		List<Student> getStudents(int id);

		// 查询大于某一个id的所有记录
		@Select("	select * from student where id >#{id}")
		List<Student> getStudents2(int id);

		// 插入一条记录
		void insertStudent(Student student);

		@Insert("INSERT INTO student(name, age, address) VALUES(#{name}, #{age} ,#{address})")
		void insertStudent2(Student student);

		// 更新一条记录
		void updateStudent(Student student);

		@Update("update student SET name=#{name} , address=#{address} ,age=#{age} where id=#{id}")
		void updateStudent2(Student student);

		// 删除一条记录
		void deleteStudent(int id);

		@Delete("	delete from student where id =#{id}")
		void deleteStudent2(int id);

		/**
		 * 模糊查询
		 */
		List<Student> fuzzyQuery(String name);

		@Select("SELECT * FROM student where name like \"%\"#{name}\"%\"")
		List<Student> fuzzyQuery2(String name);

}

