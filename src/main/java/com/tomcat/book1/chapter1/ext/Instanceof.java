package com.tomcat.book1.chapter1.ext;

/**
 * 类名: Instanceof.java <br>
 * 描述: 这个类是用来 <br>
 * 作者: honourx@foxmail.com <br>
 * 时间: 2018-07-19 22:58 <br>
 */
public class Instanceof {

	public static void main(String[] args) {
		People p = new People(); //实现类
		Honourx h = new Honourx(); //子类
		System.out.println(p instanceof Person); //true
		System.out.println(p instanceof Honourx); //false p不是Honourx的子类
		System.out.println(h instanceof Person); //true
		System.out.println(h instanceof People); //true
	}

}
