package org.batch.java8.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;


/**
 * @Package: org.batch.java8.rpc <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/5/26 11:45 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/5/26. <br/>
 */

public class RpcImporter<T> {
		public T importer(final Class<?> serviceClass, final InetSocketAddress address){
				return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class<?>[]{serviceClass.getInterfaces()[0]}, new InvocationHandler() {
						/**
						 * 代理方法调用
						 * @param proxy
						 * @param method
						 * @param args
						 * @return
						 * @throws Throwable
						 */
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
								Socket socket=null;
								ObjectOutputStream outputStream=null;
								ObjectInputStream inputStream=null;
								try{

										//将远程调用组装成Rpc 网络调用。这个只是远程调用的Stub ，远程调用的一个代理
										socket=new Socket();
										socket.connect(address);
										outputStream=new ObjectOutputStream(socket.getOutputStream());
										//调用远程方法： 通过：（接口名 + 方法名 + 方法入参类型 + 方法入参值 ）的形式组织参数
										outputStream.writeUTF(serviceClass.getName());
										outputStream.writeUTF(method.getName());
										outputStream.writeObject(method.getParameterTypes());
										outputStream.writeObject(args);
										inputStream=new ObjectInputStream(socket.getInputStream());
										return inputStream.readObject();
								}catch (Exception e){

										e.printStackTrace();
								}finally {

										if (socket!=null){
												socket.close();
										}
										if (outputStream!=null){
												outputStream.close();
										}
										if (inputStream!=null){
												inputStream.close();
										}
								}

								return  null;
						}

				});
		}
}
