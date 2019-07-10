package com.just.talk.design.proxy;

import javax.naming.Context;

/**
 * Created by Liu On 2019/7/1
 * Description:
 * email: mingming.liu@quvideo.com
 */
public interface ILoginService {
    public void init(Context context);
    public void auth();
    public void unAuth();
}
