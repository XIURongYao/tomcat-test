package com.tomcat.book1.chapter1.ext;

/**
 * 类名: People.java <br>
 * 描述: 实现类 <br>
 * 作者: honourx@foxmail.com <br>
 * 时间: 2018-07-19 23:03 <br>
 */
public class People implements Person {
	private int a = 0;

	public void eat() {
		System.out.println("我是实现类" + a);
	}

}
