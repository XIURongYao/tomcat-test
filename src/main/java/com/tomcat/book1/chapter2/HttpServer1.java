package com.tomcat.book1.chapter2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/** 
 * 类名: HttpServer1.java    
 * 描述: 这个类是用来
 * 作者: honourx@foxmail.com  
 * 时间: 2018-06-30 16:20  
 */
public class HttpServer1 {
	
	//shutdown command
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	
	private boolean shutdown = false;
	
	public static void main(String[] args){
		HttpServer1 server = new HttpServer1();
		server.await();
	}
	
	public void await(){
		ServerSocket serverSocket = null;
		int port = 8080;
		try{
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		}
		catch(IOException e){
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
				
				//创建请求对象并且解析
				Request request = new Request(input);
				request.parse();
				//创建响应对象
				Response response = new Response(output);
				response.setRequest(request);
				
				//检查这个请求是servlet还是静态资源
				if(request.getUri().startsWith("/servlet/")){
					ServletProcessor1 processor = new ServletProcessor1();
					processor.process(request, response);
				}else{
					StaticResourceProcessor processor = new StaticResourceProcessor();
					processor.process(request, response);
				}
				
				//关闭socket
				socket.close();
				//检查是否shutdown
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			}catch(Exception e){
				e.printStackTrace();
				System.exit(1);
			}
		}
			
	}

}
