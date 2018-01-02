package org.vincent.mq.persist.exception;

/**
 * @Package: org.vincent.mq.persist <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/1/2 23:46 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/1/2. <br/>
 */

public class AccountPersistException extends Exception {
		public AccountPersistException(String msg){
				super(msg);
		}
		public AccountPersistException(String msg,Throwable e){
				super(msg,e);
		}

}
