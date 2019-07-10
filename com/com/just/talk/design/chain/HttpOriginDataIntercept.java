package com.just.talk.design.chain;

/**
 * Created by Liu On 2019/6/26
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class HttpOriginDataIntercept extends IRequestHandler {
    @Override
    public boolean handler(Request request) {
        if (!request.mBaseUrl.startsWith("https")) {
            return false;
        }
        return true;
    }
}
