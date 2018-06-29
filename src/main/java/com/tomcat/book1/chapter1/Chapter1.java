package com.tomcat.book1.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 深入剖析tomcat第一章示例01。
 * 作者: honourx
 * 时间: 2018年6月27日  下午9:57:24
 */

public class Chapter1 {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("192.168.199.191",8085);
			OutputStream os = socket.getOutputStream();
			boolean autoflush = true;
			PrintWriter out = new PrintWriter(os,autoflush);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()) );
			out.println("GET /index.jsp HTTP/1.1");
			out.println("HOST: localhost:8080");
			out.println("Connection: Close");
			out.println();
			
			//读取返回
			boolean loop = true;
			StringBuffer sb = new StringBuffer(8096);
			while(loop){
				if(in.ready()){
					int i = 0;
					while(i != -1){
						i = in.read();
						sb.append((char)i);
					}
					loop = false;
				}
				Thread.currentThread().sleep(50);
			}
			System.out.print(sb.toString());
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
