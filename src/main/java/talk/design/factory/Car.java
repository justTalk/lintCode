package src.main.java.design.factory;

/**
 * Created by Liu On 2019/6/19
 * Description:
 * email: mingming.liu@quvideo.com
 */
public abstract class Car {
    public abstract void createWheel();
    public abstract void createEngine();
    public abstract void createElectricalEquipment();
    public abstract void createBody();
}
