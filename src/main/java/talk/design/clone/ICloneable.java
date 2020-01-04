package src.main.java.design.clone;

/**
 * Created by Liu On 2019/6/22
 * Description:
 * email: mingming.liu@quvideo.com
 */
public interface ICloneable<T> extends Cloneable {
    public T copy();
}
