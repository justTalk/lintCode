package com.just.talk.design.factory;

/**
 * Created by Liu On 2019/6/20
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class BMWFactory extends FactoryCar {
    @Override
    Car create() {
        return new BMWCar();
    }
}
