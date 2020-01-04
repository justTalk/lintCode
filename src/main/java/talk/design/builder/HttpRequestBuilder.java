package src.main.java.design.builder;

/**
 * Created by Liu On 2019/6/21
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class HttpRequestBuilder extends IBuilder<String> {
    String mBaseUrl;
    StringBuilder mParam = new StringBuilder();;

    public HttpRequestBuilder(String baseUrl){
        this.mBaseUrl = baseUrl;
    }

    public HttpRequestBuilder append(String key, String value){
        if (mParam.length() != 0) {
            mParam.append("&");
        }
        mParam.append(key).append(value);
        return this;
    }

    @Override
    public String builder() {
        return mBaseUrl + mParam.toString();
    }
}
