package com.just.talk.design.factory;

/**
 * Created by Liu On 2019/6/20
 * Description:
 * email: mingming.liu@quvideo.com
 */
public abstract class AbstractFactory {
    abstract Engine createEngine();
    abstract Wheel createWheels();
}
