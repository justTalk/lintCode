package com.just.talk.design.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liu On 2019/6/23
 * Description:
 * email: mingming.liu@quvideo.com
 */
public abstract class ElementGroup extends Element {

    List<Element> mChildren = new ArrayList<>();

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public abstract boolean onIntercepted(MotionEvent motionEvent);

    public abstract boolean dispatchTouchEvent(MotionEvent motionEvent);

    public abstract void drawChildren(Canvas canvas);

    public abstract Element getChildren(int position);

    public abstract void justOrder();
}
