package com.Proxy.StaticProxy;

public class UserProxy implements User {
    private User user=new UserImp();

    @Override
    public void eat(String s) {
        System.out.println("静态代理前置内容");
        user.eat(s);
        System.out.println("静态代理后置内容");
    }
}
