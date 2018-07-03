package com.tomcat.book1.chapter3;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/** 
 * 类名: HttpConnector.java <br>   
 * 描述: 这个类是用来     <br>
 * 作者: honourx@foxmail.com  <br>
 * 时间: 2018-07-03 22:45  <br>
 */
public class HttpConnector implements Runnable{
	
	boolean stopped;
	private String scheme = "http";
	
	public String getScheme(){
		return scheme;
	}
	
	public void run(){
		ServerSocket serverSocket = null;
		int port = 8080;
		try{
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		while(!stopped){
			//等待连接
			Socket socket = null;
			try{
				socket = serverSocket.accept();
			}catch(Exception e){
				continue;
			}
			// Hand this socket off to an HttpProcessor
			HttpProcessor processor = new HttpProcessor(this);
			processor.process(socket);
		}
	}
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}


}

