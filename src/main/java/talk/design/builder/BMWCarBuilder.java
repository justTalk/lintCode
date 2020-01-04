package src.main.java.design.builder;

import src.main.java.design.builder.CarBuilder;
import src.main.java.design.factory.BMWCar;
import src.main.java.design.factory.Car;

/**
 * Created by Liu On 2019/6/21
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class BMWCarBuilder extends CarBuilder {

    public BMWCarBuilder(){
        mCar = new BMWCar();
    }


    @Override
    public final Car builder() {
        mCar.createEngine();
        mCar.createWheel();
        mCar.createBody();
        mCar.createElectricalEquipment();
        return super.builder();
    }
}
