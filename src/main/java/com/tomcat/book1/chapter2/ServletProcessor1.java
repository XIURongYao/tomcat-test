package com.tomcat.book1.chapter2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/** 
 * 类名: ServletProcessor1.java    
 * 描述: 这个类是用来
 * 作者: honourx@foxmail.com  
 * 时间: 2018-06-30 21:12  
 */
public class ServletProcessor1 {
	
	@SuppressWarnings("rawtypes")
	public void process(Request request, Response response){
		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf("/") + 1);
		URLClassLoader loader = null;
		try{
			//创建一个URLClassLoader
			URL[] urls = new URL[1];
			URLStreamHandler streamHandler = null;
			File classPath = new File(Constants.WEB_ROOT);
			//
			String repository = (new URL("file", null, classPath.getCanonicalPath() + 
					File.separator)).toString();
			//
			urls[0] = new URL(null, repository, streamHandler);
			loader = new URLClassLoader(urls);
		}catch(IOException e){
			System.out.println(e.toString());
		}
		Class myClass = null;
		try{
			myClass = loader.loadClass(servletName);
		}catch(ClassNotFoundException e){
			System.out.println(e.toString());
		}
		
		Servlet servlet = null;
		try{
			servlet = (Servlet) myClass.newInstance();
			servlet.service((ServletRequest) request, (ServletResponse) response);
		}catch(Exception e){
			System.out.println(e.toString());
		}catch(Throwable e){
			System.out.println(e.toString());
		}
	}

}
