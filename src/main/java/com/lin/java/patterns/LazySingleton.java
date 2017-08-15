package com.lin.study.java.patterns;

/**
 * Created by linwenxue on 2015/7/17.
 */
public class LazySingleton implements Runnable{
    private LazySingleton(){
        System.out.println("LazySingleton is create");
    }
    private static LazySingleton instance = null;
    public static synchronized LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
    public static void createString(){
        System.out.println("create String");
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        long beginTime = System.currentTimeMillis();
        for(int i=0;i<1000000;i++){
            LazySingleton.getInstance();
        }
        System.out.println(System.currentTimeMillis() - beginTime);
    }

    public static void main(String[] args){
        //LazySingleton.createString();
        for(int i=0;i<5;i++){
            new Thread(new LazySingleton()).start();
        }
    }

    //为了解决同步关键字降低系统性能的缺陷，做了一定改进
    /*private StaticSingleton(){
        System.out.println("StaticSingleton is create");
    }
    private static class SingletonHolder{
        private static StaticSingleton instance = new StaticSingleton();
    }
    public static StaticSingleton getInstance(){
        return SingletonHolder.instance;
    }*/
}
