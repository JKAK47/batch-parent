package org.batch.db.mysql.dao;

import org.batch.db.mysql.bean.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/** 
* StudentDao Tester. 
* 
* @author <PengRong> 
* @since  
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:mysql/spring/Application.xml"})
public class StudentDaoTest extends AbstractJUnit4SpringContextTests{
		@Autowired
		private  StudentDao dao;
		@Before
		public void before() throws Exception {

		}

		@After
		public void after() throws Exception {
		}

		/**
		*
		* Method: select(String statement, Map<String,Object> parameter)
		*
		*/
		@Test
		public void testSelect() throws Exception {
			 String statement="StudentMapper.queryStudent";
			 Map<String, Integer> maps=new HashMap<>();
			 maps.put("id",1);
			 Student student=dao.select(statement,maps);
			 System.out.println(student);

		}


		}
