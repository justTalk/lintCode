package com.just.talk.design.proxy;

import javax.naming.Context;

/**
 * Created by Liu On 2019/7/1
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class ILoginServiceProxy implements IProxy<ILoginService>, ILoginService{
    ILoginService mLoginService;

    @Override
    public ILoginService newProxy(ILoginService loginServiceImpl) {
        mLoginService = loginServiceImpl;
        return this;
    }

    @Override
    public void init(Context context) {
        if (mLoginService != null) {
            mLoginService.init(context);
        }
    }

    @Override
    public void auth() {
        if (mLoginService != null) {
            mLoginService.auth();
        }
    }

    @Override
    public void unAuth() {
        if (mLoginService != null) {
            mLoginService.unAuth();
        }
    }
}
