package talk.algorithm.leetcode.array;

public class SimpleArray{

    /*
     * @Author Andy
     * @Description https://leetcode-cn.com/problems/two-sum/
     * @Date 16:13 2020-01-04
     * @Param 暴力法：O(1) T(n*n)
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

    /*
     * @Author Andy
     * @Description https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
     * @Date 18:20 2020-01-05
     * @Param
     * @return
     * 解决思路：游标法 统计左边不重复数据都最后一个下标，找到不重复数据时，该下标++并填充数据
     **/
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 1){
            return 1;
        }
        int endIndex = 0;
        for (int i = endIndex + 1; i <nums.length ; i++) {
            if (nums[i] == nums[endIndex]){
                continue;
            }else {
                nums[++endIndex] = nums[i];
            }
        }

        return endIndex + 1;
    }

    /*
     * @Author Andy
     * @Description https://leetcode-cn.com/problems/maximum-subarray/
     * @Date 18:44 2020-01-05
     * @Param
     * @return O(n) T(n)
     * 解决思路：从左到右 每个位置都计算出来包含当前位置数据都最大值，直到最后一个数据
     **/
    public int maxSubArray(int[] nums) {
        //-2,1,-3,4,-1,2,1,-5,4
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int[] added = new int[nums.length];
        int maxValue = added[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMax =  added[i-1] + nums[i];
            added[i] = curMax > nums[i] ? curMax : nums[i];
            if (maxValue < added[i]) {
                maxValue = added[i];
            }
        }
        return maxValue;
    }



}