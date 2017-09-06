package com.lin.java.proxy;

/**
 * 创建实现类
 * @author 
 *
 */
public class ZooImpl implements Zoo {

	@Override
	public void queryAnimal() {
		System.out.println("查询动物园中的动物。。。。。");
	}

	@Override
	public void addAnimal() {
		System.out.println("向动物园中添加动物。。。。。");
	}

}
