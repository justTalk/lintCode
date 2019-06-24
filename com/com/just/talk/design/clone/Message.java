package com.just.talk.design.clone;

/**
 * Created by Liu On 2019/6/22
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class Message implements ICloneable<Message>{

    int what;
    String descriotion;

    @Override
    public Message copy() {
        Message clone = null;
        try {
            clone = (Message) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }
}
