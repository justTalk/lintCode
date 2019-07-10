package com.just.talk.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Liu On 2019/7/1
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class MyInvocationHandler implements InvocationHandler {
    Object target = null;

    MyInvocationHandler(Object o){
        this.target = o;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return method.invoke(target, objects);
    }
}
