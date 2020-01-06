package talk.algorithm;

import talk.algorithm.imp.BackPack;

/**
 * Created by Liu On 2019/5/12
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main (String[] args)
    {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        BackPack.maxProduct(nums);
    }
}
