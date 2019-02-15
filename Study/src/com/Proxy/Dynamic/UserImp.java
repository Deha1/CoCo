package com.Proxy.Dynamic;

import com.Proxy.StaticProxy.User;

public class UserImp implements User{
    @Override
    public void eat(String s) {
        System.out.println("吃"+s);
    }
}
