package src.main.java.design.factory;

/**
 * Created by Liu On 2019/6/19
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class StaticFactory {

    /**
     * 静态工厂方法
     * 当新增一个车种时必然会修改当前工厂方法
     */
    public static Car createCar(int type){
        switch (type){
            case 1:
                return new BMWCar();
            case 2:
                default:
                    return new JeepCar();
        }
    }
}
