package com.tomcat.book1.chapter2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

/** 
 * 类名: Response.java    
 * 描述: 这个类是用来
 * 作者: honourx@foxmail.com  
 * 时间: 2018-06-30 16:42  
 */
public class Response implements ServletResponse{
	
	private static final int BUFFER_SIZE = 1024;
	Request request;
	OutputStream output;
	PrintWriter writer;
	
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
			File file = new File(Constants.WEB_ROOT, request.getUri());
			fis = new FileInputStream(file);
			
			int ch = fis.read(bytes, 0, BUFFER_SIZE);
			while(ch != -1){ 
				output.write(bytes, 0, ch);
				ch = fis.read(bytes, 0, BUFFER_SIZE);
			}
		}catch(FileNotFoundException e){
			String errorMessage = "HTTP/1.1 404 File Not Found\r\n" + 
					"Content-Type: text/html\r\n" + 
					"Content-Length: 23\r\n" + 
					"\r\n" +
					"<h1> File Not Found</h1>";
			output.write(errorMessage.getBytes());
		}
		finally{
			if(fis != null){
				fis.close();
			}
		}
	}

	//实现自ServletResponse
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		
	}

	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public PrintWriter getWriter() throws IOException {
		writer = new PrintWriter(output, true);
		return writer;
	}

	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public void resetBuffer() {
		// TODO Auto-generated method stub
		
	}

	public void setBufferSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setCharacterEncoding(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setContentLength(int arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setContentLengthLong(long arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setContentType(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setLocale(Locale arg0) {
		// TODO Auto-generated method stub
		
	}

}