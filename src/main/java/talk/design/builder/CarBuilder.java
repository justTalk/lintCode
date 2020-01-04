package src.main.java.design.builder;

import src.main.java.design.factory.Car;

/**
 * Created by Liu On 2019/6/21
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class CarBuilder extends IBuilder<Car> {
    Car mCar;


    @Override
    public Car builder() {
        return mCar;
    }
}
