package src.main.java.design.factory;

/**
 * Created by Liu On 2019/6/20
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class JeepFacory extends FactoryCar {
    @Override
    Car create() {
        return new JeepCar();
    }
}
