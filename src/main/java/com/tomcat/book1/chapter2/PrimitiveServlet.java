package com.tomcat.book1.chapter2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/** 
 * 类名: PrimitiveServlet.java    
 * 描述: 代码清单2-1
 * 作者: honourx@foxmail.com  
 * 时间: 2018-06-30 16:07  
 */
public class PrimitiveServlet implements Servlet{

	public void destroy() {
		System.out.println("destroy");
	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public String getServletInfo() {
		return null;
	}

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init");
	}

	public void service(ServletRequest request, ServletResponse response) 
			throws ServletException, IOException {
		System.out.println("from service");
		PrintWriter out = response.getWriter();
		out.println("Hello. Roses are red.");
		out.println("Violets are blue.");
	}
	
	

}
