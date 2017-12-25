package base.MutiThread;

import java.util.concurrent.ExecutionException;

public class MutiThreadDemo {



	public static void main(String[] args) throws ExecutionException,
			InterruptedException {

		MutiThreadDemo demo=new  MutiThreadDemo();
		String sql=demo.getStatement("insertAA");
		System.out.println(sql);
		
	}
	
	/**
	 * 在数据库中自动获取到单表的Mapper命令空间
	 * @param sqlId
	 * @return
	 */
	public   String getStatement(String sqlId) {
		String name = this.getClass().getName();
		StringBuffer sb = new StringBuffer();
		sb.append(name).append(".").append(sqlId);
		String statement = sb.toString();

		return statement;
	}


}
