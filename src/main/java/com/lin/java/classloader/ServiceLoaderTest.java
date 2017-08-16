package com.lin.java.classloader;

import java.util.ServiceLoader;

/**
 * Created by wenxuelin on 2016/12/5.
 */
public class ServiceLoaderTest {
    public static void main(String[] args) throws Exception {
        ServiceLoader<IHello> serviceLoader = ServiceLoader.load(IHello.class);
        for (IHello service : serviceLoader) {
            service.sayHello();
        }
    }
}
