package com.just.talk.design.builder;

import com.just.talk.design.builder.CarBuilder;
import com.just.talk.design.factory.BMWCar;
import com.just.talk.design.factory.Car;

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
