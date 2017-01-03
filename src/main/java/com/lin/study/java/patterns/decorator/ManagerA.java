package com.lin.study.java.patterns.decorator;

/**
 * Created by linwenxue on 2015/1/21.
 */
public class ManagerA extends Manager {
    private Person person;

    public ManagerA(Person person) {
        this.person = person;
    }

    @Override
    public void doCoding() {
        doEarlyWork();
        person.doCoding();
    }

    public void doEarlyWork() {
        System.out.println("项目经理A做需求分析");
        System.out.println("项目经理A做架构设计");
        System.out.println("项目经理A做详细设计");
    }
}
