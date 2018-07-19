package com.server.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 类名: MyTomcat.java <br>
 * 描述: 这个类是用来 <br>
 * 作者: honourx@foxmail.com <br>
 * 时间: 2018-07-03 23:51 <br>
 */
public class MyTomcat {

	public static void main(String[] args) {
		try {
			ServerSocket tomcat = new ServerSocket(9090);
			System.out.println("服务器启动");
			//
			Socket s = tomcat.accept();
			//
			byte[] buf = new byte[1024];
			InputStream in = s.getInputStream();
			//
			int length = in.read(buf);
			String request = new String(buf, 0, length);
			//
			System.out.println(request);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
