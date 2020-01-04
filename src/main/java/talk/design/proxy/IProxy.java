package src.main.java.design.proxy;

/**
 * Created by Liu On 2019/7/1
 * Description:
 * email: mingming.liu@quvideo.com
 */
public interface IProxy<T> {
    public T newProxy(T t);
}
