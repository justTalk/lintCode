package talk.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liu On 2019/6/22
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class Day1 {

    /**
     * https://leetcode.com/problems/3sum/
     * 三数之和等0
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> one = new ArrayList<>();
                        one.add(nums[i]);
                        one.add(nums[j]);
                        one.add(nums[k]);
                        result.add(one);
                    }
                }
            }
        }
        return result;
    }
}
