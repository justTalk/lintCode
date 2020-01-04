package talk.algorithm.leetcode.array;

public class SimpleArray{

    /*
     * @Author Andy
     * @Description https://leetcode-cn.com/problems/two-sum/ 
     * @Date 16:13 2020-01-04
     * @Param O(1) T(n*n)
     * @return
     **/
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }



}