package talk.algorithm.train;

import java.util.Arrays;

/**
 * @Description: 初级算法：https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/21/
 * @Author: Andy
 * @CreateDate: 2020-02-22 14:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-02-22 14:47
 * @Warn: 更新说明
 * @Version: 1.0
 */
public class Primary {

    /*
     * @Author Andy
     * @Description 移除已排序数组中重复元素
     * @Date 14:48 2020-02-22
     * @Param
     * @return
     **/
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int size = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[size]) {
                nums[++size] = nums[i];
            }
        }
        return size + 1;
    }

    /*
     * @Author Andy
     * @Description 买卖股票获取最大利润
     * @Date 14:56 2020-02-22
     * @Param
     * @return
     **/
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int i = 0;
        int j = 1;
        int max = 0;
        for (;j < prices.length; j++){
            if (prices[i] < prices[j]) {
                max += prices[j] - prices[i];
                i = j;
            }else {
                i++;
            }
        }
        return max;
    }

    /*
     * @Author Andy
     * @Description 旋转数组
     * @Date 15:16 2020-02-22
     * @Param
     * @return
     **/
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length < 1 || k % nums.length == 0) {
            return;
        }
        for (int i = 0; i < k; i++) {
            int tmp = nums[nums.length -1];
            for (int j = nums.length -1; j > 0; j--) {
                nums[j] = nums[ j -1];
            }
            nums[0] = tmp;
        }
    }

    /*
     * @Author Andy
     * @Description 判断数组中是否存在重复元素   & ｜ ～ ^
     * @Date 15:28 2020-02-22
     * @Param
     * @return
     **/
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }

}
