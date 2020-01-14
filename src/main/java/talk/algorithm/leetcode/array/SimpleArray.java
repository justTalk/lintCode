package talk.algorithm.leetcode.array;

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
}