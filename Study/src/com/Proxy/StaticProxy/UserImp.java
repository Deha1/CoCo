package com.Proxy.StaticProxy;

public class UserImp implements User {
    @Override
    public void eat(String s) {
        System.out.println("吃"+s);
    }
}
