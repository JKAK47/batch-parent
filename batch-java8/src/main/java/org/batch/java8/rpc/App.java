package org.batch.java8.rpc;

import java.net.InetSocketAddress;
import org.batch.java8.rpc.service.echo.EchoService;
import org.batch.java8.rpc.service.echo.EchoServiceImpl;

/**
 * @Package: org.batch.java8.rpc <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/5/26 12:02 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/5/26. <br/>
 */

public class App {
		public static void main(String[] args){

				RpcImporter<EchoService> importer=new RpcImporter<>();
				EchoService service=importer.importer(EchoServiceImpl.class,new InetSocketAddress("localhost",9000));
				System.out.println(service.echo("Are you ok."));
		}
}
