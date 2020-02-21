package talk.algorithm;

import talk.algorithm.imp.BackPack;
import talk.algorithm.leetcode.array.ArraySort;

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
        int[] nums = {100,4,200,1,3,2};
        ArraySort.sortInsert(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(i);
        }
    }
}
