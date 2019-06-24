package com.just.talk.design.builder;

import com.just.talk.design.factory.Car;

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
