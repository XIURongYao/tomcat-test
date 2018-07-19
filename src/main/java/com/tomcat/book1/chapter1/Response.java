package com.tomcat.book1.chapter1;
/** 
 * 类名: Response.java    
 * 描述: 这个类是用来处理响应的类
 * 作者: honourx@foxmail.com  
 * 时间: 2018-06-28 23:35  
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {
	
	private static final int BUFFER_SIZE = 1024;
	Request request;
	OutputStream output;
	
	public Response(OutputStream output){
		this.output = output;
	}
	
	public void setRequest(Request request){
		this.request = request;
	}
	
	public void sendStaticResource() throws IOException{
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		try{
			File file = new File(HttpServer.WEB_ROOT, request.getUri());
			if(file.exists()){
				fis = new FileInputStream(file);
				int ch = fis.read(bytes, 0, BUFFER_SIZE);
				while(ch != -1){
					output.write(bytes, 0, ch);
					ch = fis.read(bytes, 0, BUFFER_SIZE);
				}
			}
			else{
				// 没有此文件
				String errorMessage = "HTTP/1.1 404 File Not Found\r\n" + 
				"Content-Type: text/html\r\n" +
				"Content-Length: 23\r\n" +
				"\r\n" +
				"<h1>File Not Found</h1>";
				output.write(errorMessage.getBytes());
			}
		}catch(Exception e){
			System.out.println(e.toString());
		}
		finally{
			if(fis != null){
				fis.close();
			}
		}
	}

}
