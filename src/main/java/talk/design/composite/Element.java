package src.main.java.design.composite;

import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.Rect;

/**
 * Created by Liu On 2019/6/23
 * Description:
 * email: mingming.liu@quvideo.com
 */
public abstract class Element {
    Rect mLayout;
    Element mParent;
    Element mFocusElement;

    public abstract void draw(Canvas canvas);
    public abstract void move(int x, int y);
    public abstract boolean onTouchEvent(MotionEvent motionEvent);

    public Element getParent(){
        return mParent;
    }

    public Element getFocusElement(){
        return mFocusElement;
    }

}
