package org.batch.db.mysql.mybatis; 

import org.junit.*;

/** 
* StudentInterfaceDaoImpl Tester. 
* 
* @author <Authors name> 
* @since  
* @version 1.0 
*/ 
public class StudentInterfaceDaoImplTest { 

@Before
public void before() throws Exception {
		System.out.print("before start");
}

@After
public void after() throws Exception {
		System.out.print("after stop");
}

/**
 *  所有测试方法执行前执行一次，
 */
@BeforeClass
public static void  beforeClassA(){
	System.out.print("beforeClass");
}

/**
 * 所有测试方法执行完执行一次
 */
@AfterClass
public static void  afterClassB(){
		System.out.print("beforeClass");
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
		StudentInterfaceDaoImpl studentInterfaceDaoImpl = new StudentInterfaceDaoImpl();
		System.out.println((studentInterfaceDaoImpl
						.queryBySql(sqlStatement, 1)));
		studentInterfaceDaoImpl.closeSession();
} 

/** 
* 
* Method: getStudentbyId(int id) 
* 
*/ 
@Test
public void testGetStudentbyId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getStudents(int id) 
* 
*/ 
@Test
public void testGetStudents() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: insertStudent(Student student) 
* 
*/ 
@Test
public void testInsertStudent() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: updateStudent(Student student) 
* 
*/ 
@Test
public void testUpdateStudent() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteStudent(int id) 
* 
*/ 
@Test
public void testDeleteStudent() throws Exception { 
//TODO: Test goes here... 
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
