package com.Proxy.Dynamic;

import com.Proxy.StaticProxy.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        User user = new UserImp();
        InvocationHandler handler = new DynamicProxy(user);
        User proxy=(User)Proxy.newProxyInstance(User.class.getClassLoader(),new Class[]{User.class},handler);
        proxy.eat("苹果");
    }
}
