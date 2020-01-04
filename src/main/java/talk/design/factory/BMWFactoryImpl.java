package src.main.java.design.factory;

/**
 * Created by Liu On 2019/6/20
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class BMWFactoryImpl extends AbstractFactory {
    @Override
    Engine createEngine() {
        return new Engine() {};
    }

    @Override
    Wheel createWheels() {
        return new Wheel() {};
    }
}
