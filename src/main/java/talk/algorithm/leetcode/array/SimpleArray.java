package talk.algorithm.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;

public class SimpleArray{

    /*
     * @Author Andy
     * @Description https://leetcode-cn.com/problems/two-sum/
     * @Date 16:13 2020-01-04
     * @Param TODO 暴力法：O(1) T(n*n)
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
     * TODO 解决思路：游标法 统计左边不重复数据都最后一个下标，找到不重复数据时，该下标++并填充数据
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
     * TODO 解决思路：从左到右 每个位置都计算出来包含当前位置数据都最大值，直到最后一个数据
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


    public int maxSubArrayOpt(int[] nums, int startIndex, int endIndex){
        if (startIndex == endIndex){
            return nums[startIndex];
        }
        int mid = (startIndex + endIndex) / 2;
        int leftMax = maxSubArrayOpt(nums, startIndex, mid);
        int rightMax = maxSubArrayOpt(nums, mid + 1, endIndex);
        int midMax = crossSum(nums, startIndex, endIndex, mid);
        return Math.max(midMax, Math.max(leftMax, rightMax));
    }

    public int crossSum(int[] nums, int left, int right, int p) {
        if (left == right) {
            return nums[left];
        }

        int leftSubsum = Integer.MIN_VALUE;
        int currSum = 0;
        for(int i = p; i > left - 1; --i) {
            currSum += nums[i];
            leftSubsum = Math.max(leftSubsum, currSum);
        }

        int rightSubsum = Integer.MIN_VALUE;
        currSum = 0;
        for(int i = p + 1; i < right + 1; ++i) {
            currSum += nums[i];
            rightSubsum = Math.max(rightSubsum, currSum);
        }

        return leftSubsum + rightSubsum;
    }

    /*
     * @Author Andy
     * @Description https://leetcode-cn.com/problems/search-insert-position/
     * @Date 20:13 2020-01-13
     * @Param
     * @return
     **/
    public int searchInsert(int[] nums, int target) {
        int i = 0;
        for (;i < nums.length; i++) {
            if (target == nums[i] || target < nums[i]) {
                return i;
            }
        }
        return i;
    }

    /*
     * @Author Andy
     * @Description https://leetcode-cn.com/problems/merge-sorted-array/
     * @Date 20:17 2020-01-13
     * @Param
     * @return
     * 合并有序数组：倒序遍历数组：nums数据剩余数据 > nums2剩余数据 || nums数据剩余数据 < nums2剩余数据
     * eg: nums1 = [1,2,3,0,0,0], m = 3
     *     nums2 = [2,5,6],       n = 3
     **/
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int offset = 0;
        int j = n - 1;
        int i = m - 1;
        while (i >= 0 && j >= 0){
            if (nums1[i] >= nums2[j]) {
                // 如果nums1 >= nums2元素  如果之前offset标记了需要移动的数据，则移动数据
                if (offset > 0){
                    //将i之后的数据都向后移动offset距离 n - j - offset表示本次之前已经移动了多少元素到nums1素组中
                    for (int k = m + n - j - 1 - offset; k > i; k--) {
                        nums1[k + offset] = nums1[k];
                    }
                    for (int k = 1; k <= offset; k++) {
                        nums1[i + k] = nums2[j + k];
                    }
                    offset = 0;
                }
                //移动到下一个数据
                i--;
                continue;
            }
            // 如果nums1 < nums2元素 标记下一次移动的步幅，同时移动到nums2下一个下标
            offset++;
            j--;
        }

       if (offset > 0){
           //nums1中数据比nums2中数据小
           for (int k = m + n - offset - 1; k > i ; k--) {
               nums1[k + offset] = nums1[k];
           }
           for (int k = 1; k <= offset; k++) {
               nums1[i + k] = nums2[j + k];
           }
       }
       if (i < 0 && j >= 0) {
           //nums2中还剩余数据未添加到nums1中
           for (int k = m + n - j - 1 - 1; k >= 0; k--) {
               nums1[k + j + 1] = nums1[k];
           }
           for (int k = 0; k <= j; k++) {
               nums1[k] =nums2[k];
           }
       }
    }

    public void mergeOpt(int[] nums1, int m, int[] nums2, int n) {
        int len1 = m - 1;
        int len2 = n - 1;
        int len = m + n - 1;
        while(len1 >= 0 && len2 >= 0) {
            // 注意--符号在后面，表示先进行计算再减1，这种缩写缩短了代码
            nums1[len--] = nums1[len1] > nums2[len2] ? nums1[len1--] : nums2[len2--];
        }
        // 表示将nums2数组从下标0位置开始，拷贝到nums1数组中，从下标0位置开始，长度为len2+1
        System.arraycopy(nums2, 0, nums1, 0, len2 + 1);
    }

    /*
     * @Author Andy
     * @Description 乘最多水的容器 https://leetcode-cn.com/problems/container-with-most-water/
     * @Date 11:58 2020-01-14
     * @Param
     * @return T(n) O(1)
     * 游标法：游标比较后 变动的时候 能帮助定位到下一个更大或者更小的数据项 故能减少遍历次数
     **/
    public int maxArea(int[] height) {
        int start = 0, end = height.length - 1;
        int maxArea = 0;
        while (start <= end){
            int tempHeight = 0;
            int tempWidth = end - start;
            if (height[start] < height[end]) {
                tempHeight = height[start];
                start++;
            }else {
                tempHeight = height[end];
                end--;
            }
            int tmpArea = tempWidth * tempHeight;
            if (tmpArea > maxArea){
                maxArea = tmpArea;
            }

        }
        return maxArea;
    }

    /*
     * @Author Andy
     * @Description 接雨水 https://leetcode-cn.com/problems/trapping-rain-water/
     * @Date 11:56 2020-01-23
     * @Param
     * @return
     * 求每一列的值:每一列能收集的水 = Math.min(left, right) - height[i]
     **/
    public int trap(int[] height) {
        int trap = 0;
        int[] maxRight = new int[height.length];
        for (int j = height.length - 2; j >= 0; j--) {
            maxRight[j] = Math.max(height[j + 1], maxRight[j + 1]);
        }
        int maxLeft = 0;
        for (int i = 1; i < height.length; i++) {
            maxLeft = Math.max(maxLeft, height[i - 1]);

            int maxHeight = Math.min(maxLeft, maxRight[i]);
            trap += Math.max(maxHeight - height[i], 0);
        }
        return trap;
    }

    /*
     * @Author Andy
     * @Description 接雨水 https://leetcode-cn.com/problems/trapping-rain-water/
     * @Date 11:12 2020-02-02
     * @Param
     * @return
     * 双指针遍历 减少求左边和右边最大值的计算
     **/
    public int trap2(int[] height) {
        int sum = 0;
        int max_left = 0;
        int max_right = 0;
        int left = 1;
        int right = height.length - 2; // 加右指针进去
        for (int i = 1; i < height.length - 1; i++) {
            //从左到右更: TODO 这里最关键的是转变一个思想:求左右边的最大值的目标是为了找出其相对较小值 所以不用一定求出两边最大 只需要求出一边有值大于另一边的最大值
            if (height[left - 1] < height[right + 1]) {
                max_left = Math.max(max_left, height[left - 1]);
                int min = max_left;
                if (min > height[left]) {
                    sum = sum + (min - height[left]);
                }
                left++;
                //从右到左更
            } else {
                max_right = Math.max(max_right, height[right + 1]);
                int min = max_right;
                if (min > height[right]) {
                    sum = sum + (min - height[right]);
                }
                right--;
            }
        }
        return sum;
    }

    /*
     * @Author Andy
     * @Description https://leetcode-cn.com/problems/jump-game/
     * @Date 11:42 2020-02-02
     * @Param 递归思路：会超出时间限制
     * @return
     **/
    @Deprecated
    public boolean canJump(int[] nums) {
        return nums.length > 0 && recursiveJump(nums, 0, nums.length - 1);
    }

    private boolean recursiveJump(int[] nums, int start, int end){
        if (start == end) {
            return true;
        }
        for (int i = 1; i <= nums[start]; i++) {
            if (recursiveJump(nums, start + i, end)) {
                return true;
            }
        }
        return false;
    }

    /*
     * @Author Andy
     * @Description 优化递归。保存已递归的case 减少递归次数 => 能通过 但是效率不高
     * @Date 09:43 2020-02-08
     * @Param
     * @return
     **/
    private boolean recursiveJumpOpt(int[] nums, int start, int end, int[] state){
        if (start == end) {
            return true;
        }
        if (state[start] != 0) {
            return false;
        }
        for (int i = 1; i <= nums[start]; i++) {
            if (recursiveJumpOpt(nums, start + i, end, state)) {
                return true;
            }
        }
        state[start] = 1;
        return false;
    }

    /*
     * @Author Andy
     * @Description 思路：只要满足 能到达的位置上的值大于1 就可以跳转到该位置
     * @Date 09:57 2020-02-08
     * @Param T(N) O(1)
     * @return
     **/
    public boolean canJumpOpt(int[] nums){
        int maxReachIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReachIndex) {
                return false;
            }
            int curMaxReachIndex = nums[i] + i;
            if (curMaxReachIndex > maxReachIndex) {
                maxReachIndex = curMaxReachIndex;
            }
        }
        return true;
    }
    

    /*
     * @Author Andy
     * @Description https://leetcode-cn.com/problems/3sum-closest/ 找最接近的三数之和
     * @Date 10:00 2020-02-08
     * @Param
     * @return 
     **/
    public int threeSumClosest(int[] nums, int target) {
        int sumClosest = target - (nums[0] + nums[1] + nums[2]);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int closest = target - (nums[i] + nums[j] + nums[k]);
                    sumClosest = Math.abs(sumClosest) > Math.abs(closest) ? closest:sumClosest;
                }
            }
        }
        return target - sumClosest;

    }

    public int threeSumClosestOpt(int[] nums, int target){
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for(int i=0;i<nums.length;i++) {
            int start = i+1, end = nums.length - 1;
            while(start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if(Math.abs(target - sum) < Math.abs(target - ans)) {
                    ans = sum;
                }
                if(sum > target) {
                    end--;
                } else if(sum < target) {
                    start++;
                } else {
                    return ans;
                }
            }
        }
        return ans;
    }

    /*
     * @Author Andy
     * @Description https://leetcode-cn.com/problems/maximum-product-subarray/ 找出一个序列中乘积最大的连续子序列
     * @Date 10:38 2020-02-09
     * @Param
     * @return
     **/
    public int maxProduct(int[] nums) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];

        min[0] = max[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = max[i] = nums[i];
            if (nums[i] > 0) {
                max[i] = Math.max(max[i], max[i - 1] * nums[i]);
                min[i] = Math.min(min[i], min[i - 1] * nums[i]);
            } else if (nums[i] < 0) {
                max[i] = Math.max(max[i], min[i - 1] * nums[i]);
                min[i] = Math.min(min[i], max[i - 1] * nums[i]);
            }

            result = Math.max(result, max[i]);
        }

        return result;
    }

    /*
     * @Author Andy
     * @Description https://leetcode-cn.com/problems/longest-consecutive-sequence/
     * @Date 11:01 2020-02-09 给定一个未排序的整数数组，找出最长连续序列的长度
     * @Param
     * @return
     **/
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int longest = nums.length == 0 ? 0 : 1,temp = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - 1 == nums[i-1]) {
                temp++;
                if (temp > longest) {
                    longest = temp;
                }
            }else if (nums[i] != nums[i-1]){
                temp = 1;
            }
        }
        return longest;
    }
}