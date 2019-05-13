package com.just.talk.algorithm;

import com.just.talk.algorithm.imp.BackPack;

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
    public static void main (String[] args) throws java.lang.Exception
    {
        int[] nums = {0,-1,4,-4,5,-2,-1,-1,-2,-3,0,-3,0,1,-1,-4,4,6,2,3,0,-5,2,1,-4,-2,-1,3,-4,-6,0,2,2,-1,-5,1,1,5,-6,2,1,-3,-6,-6,-3,4,0,-2,0,2};
        BackPack.maxProduct(nums);
    }
}
