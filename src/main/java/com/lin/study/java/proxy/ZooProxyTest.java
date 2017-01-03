package com.lin.study.java.proxy;


public class ZooProxyTest {

	public static void main(String[] args) {
		/*静态代理测试*/
		ZooStaticProxy zsp = new ZooStaticProxy(new ZooImpl());
		zsp.queryAnimal();
		zsp.addAnimal();
		
		/*动态代理测试*/
		ZooDynamicProxy cdp = new ZooDynamicProxy();
		Zoo zoo = (Zoo)cdp.bind(new ZooImpl());
		zoo.queryAnimal();
		zoo.addAnimal();
	}
}
