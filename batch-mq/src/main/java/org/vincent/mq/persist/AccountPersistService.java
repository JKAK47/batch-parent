package org.vincent.mq.persist;

import org.vincent.mq.persist.exception.AccountPersistException;

/**
 * @Package: org.vincent.mq.persist <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/1/2 1:11 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/1/2. <br/>
 */

public interface AccountPersistService {
	Account createAccount(Account account) throws AccountPersistException;
	Account readAccount(String id)  throws AccountPersistException;
	Account updateAccount(Account account)  throws AccountPersistException;
	Account deleteAccount(String id)  throws AccountPersistException;
}
