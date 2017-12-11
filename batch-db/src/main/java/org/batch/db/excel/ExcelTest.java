package org.batch.db.excel;

import com.generated.lifepro.dao.T1aplDao;
import com.generated.lifepro.query.T1aplParam;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * https://www.journaldev.com/2562/apache-poi-tutorial
 * http://www.cnblogs.com/hongten/archive/2012/02/22/java2poi.html
 * https://my.oschina.net/zimingforever/blog/101142
 * http://hlhpyasd.iteye.com/blog/865865
 * http://www.cnblogs.com/mingforyou/archive/2013/08/26/3282922.html
 * http://blog.csdn.net/sunjiahaoz/article/details/2523244
 * 
 * @author liuhy
 *
 */
public class ExcelTest {

	@Autowired
	private static  T1aplDao aplDao;
	public static void main(String[] args) {
		printapl();

	}

	private static void printapl() {
		T1aplParam param=new T1aplParam();
		param.setPlcno("15451521521");
		//List<T1apl> apls=aplDao.
	}
}
