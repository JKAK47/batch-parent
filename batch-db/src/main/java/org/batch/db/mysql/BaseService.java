package org.batch.db.mysql;

import common.utils.LoggerFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Package: org.batch.db.mysql <br/>
 * Description： 单表数据库访问基类 <br/>
 * Author: PengRong <br/>
 * Date: Created in 2017/12/20 0:30 <br/>
 * Company: PLCC <br/>
 * Copyright: Copyright (c) 2017 <br/>
 * Version: 1.0 <br/>
 * Modified By: <br/>
 * Created by PengRong on 2017/12/20. <br/>
 */

public class BaseService {
		protected Logger logger= LoggerFactory.getLogger();
		@Autowired
		protected SqlSessionTemplate sessionTemplate;
}
