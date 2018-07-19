package com.tomcat.book1.chapter2;

import java.io.IOException;

/** 
 * 类名: StaticResourceProcessor.java    
 * 描述: 这个类是用来
 * 作者: honourx@foxmail.com  
 * 时间: 2018-06-30 21:10  
 */
public class StaticResourceProcessor {
	
	public void process(Request request, Response response){
		try{
			response.sendStaticResource();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
