package com.tomcat.book1.chapter1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/** 
 * 类名: HttpServer.java    
 * 描述: 这个类是用来
 * 作者: honourx@foxmail.com  
 * 时间: 2018-06-28 22:53  
 */
public class HttpServer {
	
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
	
	public static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	
	private boolean shutdown = false;
	
	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.await();
	}
	
	public void await(){
		ServerSocket serverSocket = null;
		int port = 8080;
		try{
			serverSocket = new ServerSocket(port, 3, InetAddress.getByName("127.0.0.1"));
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		
		//循环等待请求
		while(!shutdown){
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			
			try{
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				//创建请求对象并解析
				Request request = new Request(input);
				request.parse();
				//创建响应对象
				Response response = new Response(output);
				response.setRequest(request);
				response.sendStaticResource();
				
				//关闭这个socket
				socket.close();
				
				//检查如果这个请求是不是SHUTDWON命令
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			}catch(Exception e){
				e.printStackTrace();
				continue;
			}
		}
	}

}
