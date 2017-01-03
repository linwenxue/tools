package com.lin.study.java.patterns.decorator;

/**
 * Created by linwenxue on 2015/1/21.
 */
public class ManagerB extends Manager {
    private Person person;

    public ManagerB(Person person) {
        this.person = person;
    }

    @Override
    public void doCoding() {
        this.person.doCoding();
        doEndWork();
    }

    public void doEndWork() {
        System.out.println("项目经理B 在做收尾工作");
    }
}
