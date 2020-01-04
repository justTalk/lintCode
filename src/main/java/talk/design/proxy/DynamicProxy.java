package src.main.java.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by Liu On 2019/7/1
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class DynamicProxy<T> {

    /*
     * Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
     * 方法的第一个参数的作用就是 获取当前类的类加载器,作用是用来生成类的
     * 第二个参数是获取真实对象的所有接口    获取所有接口的目的是用来生成代理的,因为代理要实现所有的接口
     * 第三个参数是 调用处理器  这里传入调用处理器，是因为生成代理实例需要 调用处理器    为什么需要调用处理器，因为生成的代理不能直接调用真实对象的方法,
     * 而是通过调用处理器来调用真实对象的方法，具体就是通过上面定义的DynamicProxyHandler重写父类InvocationHandler的invoke方法
     */
    public static <T> T newProxyInstance(ClassLoader classLoader, InvocationHandler handler, Class<?>[] classes){
        return (T) Proxy.newProxyInstance(classLoader, classes, handler);
    }
}
