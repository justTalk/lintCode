package src.main.java.design.chain;

/**
 * Created by Liu On 2019/6/26
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class ContentCheckHander extends IRequestHandler {
    @Override
    public boolean handler(Request request) {
        return !request.mContent.contains("黄赌毒");
    }
}
