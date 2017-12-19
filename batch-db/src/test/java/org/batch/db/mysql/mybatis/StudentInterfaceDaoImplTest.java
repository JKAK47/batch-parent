package org.batch.db.mysql.mybatis;

import common.utils.LoggerFactory;
import org.batch.db.mysql.bean.Student;
import org.junit.*;
import org.slf4j.Logger;

import java.util.List;

/**
 * StudentInterfaceDaoImpl Tester.
 * 基于IStudentOperation接口作为java 和Student表 映射xml文件之间连接的桥梁；
 * 只要调用到这个接口的某个接口方法就会调用到对应xml文件下对应查询标签对应的SQL 语句。
 * @author <Authors name>
 * @since
 * @version 1.0
 */
public class StudentInterfaceDaoImplTest {

		private static  StudentInterfaceDaoImpl studentInterfaceDaoImpl;
		private static Logger logger= LoggerFactory.getLogger();
		@Before
		public void before() throws Exception {
				logger.info("before start");
		}

		@After
		public void after() throws Exception {
				logger.info("after stop");
		}

		/**
		 *  所有测试方法执行前执行一次，
		 */
		@BeforeClass
		public static void  beforeClassA(){
				logger.info("beforeClass");
				studentInterfaceDaoImpl=new StudentInterfaceDaoImpl();
		}

		/**
		 * 所有测试方法执行完执行一次
		 */
		@AfterClass
		public static void  afterClassB(){
				logger.info("afterClass");
				studentInterfaceDaoImpl.closeSession();
		}
		/**
		 *
		 * Method: getLocalsession()
		 *
		 */
		@Test
		public void testGetLocalsession() throws Exception {
				//TODO: Test goes here...
		}

		/**
		 *
		 * Method: closeSession()
		 *
		 */
		@Test
		public void testCloseSession() throws Exception {
				//TODO: Test goes here...
		}

		/**
		 * 根据一个sqlStatement获取到一条记录；使用selcetOne 函数，通过
		 * sqlStatement字符串直接映射为Student中对应的SQL语句去执行查询
		 * Method: queryBySql(String sql, int id)
		 *
		 */
		@Test
		public void testQueryBySql() throws Exception {
				// sqlStatement是根据Student.xml 这个mapper映射文件中namespace+ select
				// 的id=getStudentbyId 唯一确定的sql字符串
				String sqlStatement = "org.batch.db.mysql.mybatis.model.IStudentOperation.getStudentbyId";
				System.out.println((studentInterfaceDaoImpl
								.queryBySql(sqlStatement, 1)));
		}

		/**
		 * 通过定义在IStudentOperation 接口中的方法获取数据库中一条记录。</br>
		 * Method: getStudentbyId(int id) </br>
		 * 同时这里查询两次，第二次没有输出SQL ；说明指示查询了一次数据库。 </br>
		 *  第二次查询数据库只是从一级缓存获取的结果。</br>
		 *  [mybatis的一级缓存是SqlSession范围的](http://m.blog.csdn.net/caoxuekun/article/details/76921838)</br>
		 */
		@Test
		public void testGetStudentbyId() throws Exception {
				Student student=studentInterfaceDaoImpl.getStudentbyId(1);
				System.out.println(student);
				student=studentInterfaceDaoImpl.getStudentbyId(1);
				System.out.println(student);
		}

		/**
		 *
		 * Method: getStudents(int id)
		 *
		 */
		@Test
		public void testGetStudents() throws Exception {
				List<Student> lists=studentInterfaceDaoImpl.getStudents(1);
				for (Student student : lists){
						System.out.println(student);
				}
		}


		/**
		 *
		 * Method: insertStudent(Student student)
		 *
		 */
		@Test
		public void testInsertStudent() throws Exception {
				Student student = new Student();
				student.setAddress("dfsafsdfsadfaswerwertwe");
				student.setAge(27);
				student.setName("XXYY");
				Assert.assertEquals("XXYY", student.getName());
				studentInterfaceDaoImpl.insertStudent(student);
				// 基于mybatis 原生的数据库默认插入（openSession 方法设置 ）为手动提交
				//studentInterfaceDaoImpl.getLocalsession().commit();
				System.out.println(student);// 能获取到数据库生成得id
		}

		/**
		 *
		 * Method: updateStudent(Student student)
		 *
		 */
		@Test
		public void testUpdateStudent() throws Exception {
				Student student = studentInterfaceDaoImpl.getStudentbyId(13);
				String content="中国.广州";
				System.out.println(content);
				student.setAddress(content);
				studentInterfaceDaoImpl.updateStudent(student);
		}

		/**
		 *
		 * Method: deleteStudent(int id)
		 *
		 */
		@Test
		public void testDeleteStudent() throws Exception {
				studentInterfaceDaoImpl.deleteStudent(13);
		}

		/**
		 *
		 * Method: getStudentbyId2(int id)
		 *
		 */
		@Test
		public void testGetStudentbyId2() throws Exception {
				//TODO: Test goes here...
		}

		/**
		 *
		 * Method: getStudents2(int id)
		 *
		 */
		@Test
		public void testGetStudents2() throws Exception {
				//TODO: Test goes here...
		}

		/**
		 *
		 * Method: insertStudent2(Student student)
		 *
		 */
		@Test
		public void testInsertStudent2() throws Exception {
				//TODO: Test goes here...
		}

		/**
		 *
		 * Method: updateStudent2(Student student)
		 *
		 */
		@Test
		public void testUpdateStudent2() throws Exception {
				//TODO: Test goes here...
		}

		/**
		 *
		 * Method: deleteStudent2(int id)
		 *
		 */
		@Test
		public void testDeleteStudent2() throws Exception {
				//TODO: Test goes here...
		}

		/**
		 *
		 * Method: fuzzyQuery(String name)
		 *
		 */
		@Test
		public void testFuzzyQuery() throws Exception {
				//TODO: Test goes here...
		}

		/**
		 *
		 * Method: fuzzyQuery2(String name)
		 *
		 */
		@Test
		public void testFuzzyQuery2() throws Exception {
				//TODO: Test goes here...
		}


}
