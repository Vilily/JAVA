package com.example;
public class Hello{
	public int add(int n1, int n2){
		return n1 + n2;
	}
	
	public static void main(String args[]){
		Hello h = new Hello();
		int res = h.add(10, 20);
		System.out.println("10 + 20="+res);
	}
}
