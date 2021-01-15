package com.example;
import org.junit.Assert;
import org.junit.Test;

public class TestHello{
	
	@Test
	public void testAdd(){
		System.out.println("maven Test");
		Hello h = new Hello();
		int res = h.add(10, 20);
		// assertEquals(期望值，实际值)
		Assert.assertEquals(30, res);
		}
}