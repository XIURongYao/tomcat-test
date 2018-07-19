package com.tomcat.book1.chapter1.ext2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/** 
 * 类名: SystemProperty.java <br>   
 * 描述: 这个类是用来     <br>
 * 作者: honourx@foxmail.com  <br>
 * 时间: 2018-07-19 23:18  <br>
 */
public class SystemProperty {
	
	public void getSystemKey(){

	    File sysFile = new File("C:/Users/lyz/Desktop","sysFile.properties");
	    Properties sysPro = System.getProperties();
	    try {
	        sysPro.store(new FileOutputStream(sysFile), "Properties ClASS");
	    } catch (FileNotFoundException e1) {
	        e1.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	}
	
	SystemProperty(){
		this.getSystemKey();
	}
	
	public static void main(String[] args) {
		new SystemProperty();
	}

}
