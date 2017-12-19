package org.batch.db.mysql.dao;

import org.batch.db.mysql.BaseService;
import org.batch.db.mysql.bean.Student;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Package: org.batch.db.mysql <br/>
 * Description： TODO <br/>
 * Author: PengRong <br/>
 * Date: Created in 2017/12/20 0:29 <br/>
 * Company: PLCC <br/>
 * Copyright: Copyright (c) 2017 <br/>
 * Version: 1.0 <br/>
 * Modified By: <br/>
 * Created by PengRong on 2017/12/20. <br/>
 */
@Service
public class StudentDao extends BaseService{
		public Student select(String statement, Map<String, Integer> parameter){
			Student student=sessionTemplate.selectOne(statement,parameter);
			return  student;
		}
}
