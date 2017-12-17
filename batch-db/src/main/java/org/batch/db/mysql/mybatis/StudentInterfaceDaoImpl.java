package org.batch.db.mysql.mybatis;

import com.mysql.jdbc.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.batch.db.mysql.bean.Student;
import org.batch.db.mysql.mybatis.model.IStudentOperation;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Package: org.batch.db.mysql.mybatis <br/>
 * Description： IStudentOperation 这个定义了操作Student 单表增删改查 的接口方法<br/>
 * Author: PengRong <br/>
 * Date: Created in 2017/12/17 23:48 <br/>
 * Company: PLCC <br/>
 * Copyright: Copyright (c) 2017 <br/>
 * Version: 1.0 <br/>
 * Modified By:<br/>
 * Created by PengRong on 2017/12/17.<br/>
 */

public class StudentInterfaceDaoImpl implements  BaseDao,IStudentOperation {
		private SqlSession Localsession = null;
		public SqlSession getLocalsession() {
				return this.Localsession;
		}
		public StudentInterfaceDaoImpl() {
				try {
						this.Localsession = this.getSession();
				} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}// 获取到sqlSession
		}
		/**
		 * 关闭Session
		 *
		 * @Title: closeSession
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param
		 * @return void 返回类型
		 * @throws
		 */
		public void closeSession() {
				this.closeSession(this.Localsession);
		}
		// 通关id和匹配到Student.xml中对应的select标签执行单表查询;没用通关Mapper接口
		public Student queryBySql(String sql, int id) {
				Student result = null;
				if (!StringUtils.isNullOrEmpty(sql)) {
						result = this.Localsession.selectOne(sql, id);
				} else {
						result = null;
				}
				return result;
		}
		/**
		 * 通过一条记录的id根据IStudentOperation接口方法映射到sql mapper文件中一个select标签；获取到一条记录，
		 */
		@Override
		public Student getStudentbyId(int id) {
				// TODO Auto-generated method stub
				IStudentOperation operation = this.Localsession
								.getMapper(IStudentOperation.class);
				Student student = operation.getStudentbyId(id);
				BaseDao.logger.debug(student.toString());
				return student;
		}

		// 获取到大于指定id的所有记录
		@Override
		public List<Student> getStudents(int id) {
				// TODO Auto-generated method stub
				IStudentOperation operation = this.Localsession
								.getMapper(IStudentOperation.class);
				// ② 通过接口方法获取一组数据库记录
				List<Student> list = operation.getStudents(id);
				return list;
		}

		/**
		 * 根据一个实例student，将对象插入到数据库中。
		 */
		@Override
		public void insertStudent(Student student) {
				// TODO Auto-generated method stub
				IStudentOperation operation = this.Localsession
								.getMapper(IStudentOperation.class);
				if (Objects.nonNull(student)) {
						operation.insertStudent(student);
				} else {
						//
				}
		}

		/**
		 * 更新一条记录的相关属性。
		 */
		@Override
		public void updateStudent(Student student) {
				// TODO Auto-generated method stub
				IStudentOperation operation = this.Localsession
								.getMapper(IStudentOperation.class);
				operation.updateStudent(student);
		}

		/**
		 * 删除一条数据记录
		 */
		@Override
		public void deleteStudent(int id) {
				// TODO Auto-generated method stub
				IStudentOperation operation = this.Localsession
								.getMapper(IStudentOperation.class);
				operation.deleteStudent(id);
		}

		@Override
		@Deprecated
		public Student getStudentbyId2(int id) {
				// TODO Auto-generated method stub
				return null;
		}

		@Override
		@Deprecated
		public List<Student> getStudents2(int id) {
				// TODO Auto-generated method stub
				return null;
		}

		@Override
		@Deprecated
		public void insertStudent2(Student student) {
				// TODO Auto-generated method stub

		}

		@Override
		@Deprecated
		public void updateStudent2(Student student) {
				// TODO Auto-generated method stub

		}

		@Override
		@Deprecated
		public void deleteStudent2(int id) {
				// TODO Auto-generated method stub

		}

		@Override
		@Deprecated
		public List<Student> fuzzyQuery(String name) {
				// TODO Auto-generated method stub
				return null;
		}

		@Override
		@Deprecated
		public List<Student> fuzzyQuery2(String name) {
				// TODO Auto-generated method stub
				return null;
		}
}
