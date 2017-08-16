package com.lin.java.proxy;
/**
 * 创建静态代理实现类
 * @author 
 *
 */
public class ZooStaticProxy implements Zoo{
	private ZooImpl zil;
	
	public ZooStaticProxy(ZooImpl zil) {
		this.zil = zil;
	}

	@Override
	public void queryAnimal() {
		System.out.println("queryAnimal before");
		zil.queryAnimal();
		System.out.println("queryAnimal after");
	}

	@Override
	public void addAnimal() {
		System.out.println("addAnimal before");
		zil.addAnimal();
		System.out.println("addAnimal after");
	}
}
