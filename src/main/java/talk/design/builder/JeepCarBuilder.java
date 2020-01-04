package src.main.java.design.builder;

import src.main.java.design.factory.Car;
import src.main.java.design.factory.JeepCar;

/**
 * Created by Liu On 2019/6/21
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class JeepCarBuilder extends CarBuilder {

    public JeepCarBuilder(){
        mCar = new JeepCar();
    }

    @Override
    public final Car builder() {
        mCar.createBody();
        mCar.createElectricalEquipment();
        mCar.createEngine();
        mCar.createWheel();
        return super.builder();
    }
}
