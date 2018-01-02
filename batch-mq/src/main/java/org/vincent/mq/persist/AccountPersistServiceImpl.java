package org.vincent.mq.persist;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.vincent.mq.persist.exception.AccountPersistException;

/**
 * @Package: org.vincent.mq.persist <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/1/2 23:49 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2017 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/1/2. <br/>
 */

public class AccountPersistServiceImpl implements AccountPersistService {
		private String fileName;
		private SAXReader reader = new SAXReader();

		private Document readDocument() {
				return null;
		}

		private void writeDocument(Document doc) throws AccountPersistException {
				Writer writer = null;
				try {
						writer = new OutputStreamWriter(new FileOutputStream(fileName), "utf-8");
						XMLWriter xmlWriter = new XMLWriter(writer, OutputFormat.createPrettyPrint());
						xmlWriter.write(doc);
				} catch (IOException e) {
						throw new AccountPersistException("unable to write persist data xml.", e);
				}
				finally {
						try {
								if (writer!=null){
										writer.close();
								}
						} catch (IOException e) {
								e.printStackTrace();
								throw new AccountPersistException("unable to close persist data xml.",e);
						}
				}
		}

		@Override
		public Account createAccount(Account account) throws AccountPersistException {
				return null;
		}

		@Override
		public Account readAccount(String id) throws AccountPersistException {
				return null;
		}

		@Override
		public Account updateAccount(Account account) throws AccountPersistException {
				return null;
		}

		@Override
		public Account deleteAccount(String id) throws AccountPersistException {
				return null;
		}
}
