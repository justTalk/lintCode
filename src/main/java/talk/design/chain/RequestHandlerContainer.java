package src.main.java.design.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liu On 2019/6/26
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class RequestHandlerContainer extends IRequestHandler {

    List<IRequestHandler> handlers = new ArrayList<>();

    public RequestHandlerContainer addHandler(IRequestHandler handler){
        handlers.add(handler);
        return this;
    }

    @Override
    public boolean handler(Request request) {
        for (IRequestHandler handler:
             handlers) {
            if (!handler.handler(request)) {
                return false;
            }
        }
        return true;
    }
}
