package com.lin.study.java.patterns.decorator;

/**
 * Created by linwenxue on 2015/1/21.
 */
public class DecoratorTest {

    public static void main(String[] args) {
        Person person = new Employee();
        person = new ManagerA(person);//赋予项目经理A的职责
        person = new ManagerB(person);//赋予项目经理B的职责
        person.doCoding();
    }
}
