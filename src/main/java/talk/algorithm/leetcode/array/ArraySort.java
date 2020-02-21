package talk.algorithm.leetcode.array;

/**
 * @Description: 数组排序实现 该类统一升序排列
 * @Author: Andy
 * @CreateDate: 2020-02-09 11:20
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-02-09 11:20
 * @Warn: 更新说明
 * @Version: 1.0
 */
public class ArraySort {

    public static void sortInsert(int[] nums){
        if (nums == null) {
            return;
        }
        for (int i = 1; i < nums.length; i++) {
            int index = i - 1;
            int tmp = nums[i];
            while (index >= 0){
                if (tmp < nums[index]) {
                    nums[index + 1] = nums[index];
                    index--;
                }else {
                    break;
                }
            }
            if (index < i - 1) {
                nums[index + 1] = tmp;
            }
        }
    }

    public static void sortBubble(int[] nums){
        boolean hasSwap = true;
        for (int i = 0; i < nums.length; i++) {
            if (!hasSwap) {
                return;
            }
            hasSwap = false;
            for (int j = 0; j < nums.length - 1 - i; j++) {
                int next = j + 1;
                if (nums[j] > nums[next]) {
                    int tmp = nums[next];
                    nums[next] = nums[j];
                    nums[j] = tmp;
                    hasSwap = true;
                }
            }
        }
    }

    public static void sortSelect(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] <= nums[min]) {
                    min = j;
                }
            }
            if (min != i) {
              int tmp = nums[i];
              nums[i] = nums[min];
              nums[min] = tmp;
            }
        }
    }

    public static void sortQuick(int[] nums){
        recursive(nums, 0, nums.length - 1);
    }

    private static void recursive(int[] nums, int start, int end){
        if (start >= end) {
            return;
        }
        int q = partition(nums, start, end); // 获取分区点
        recursive(nums, start, q - 1);
        recursive(nums, q + 1, end);
    }

    /*
     * @Author Andy
     * @Description 分区函数 快速排序的核心实现：双指针原地排序
     * @Date 19:29 2020-02-13
     * @Param [nums, start, end]
     * @return int
     **/
    private static int partition(int[] nums, int start, int end){
        int pivot = end;
        int j = start;
        for (int i = start; i < end; i++) {
            if (nums[i] < nums[pivot]) {
                int tmp = nums[j];
                nums[j++] = nums[i];
                nums[i] = tmp;
            }
        }
        int tmp = nums[j];
        nums[j] = nums[pivot];
        nums[end] = tmp;
        return j;
    }

    public static void sortMerge(int[] nums, int start, int end){
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        sortMerge(nums, start, mid);
        sortMerge(nums, mid + 1, end);
        merge(nums, start, end, mid);
    }

    /*
     * @Author Andy
     * @Description  数组合并，类似于插入排序
     * @Date 11:07 2020-02-21
     * @Param
     * @return
     **/
    public static void merge(int[] nums, int start, int end, int mid){
        for (int i = mid + 1; i <= end; i++) {
            int value = nums[i];
            int j = i -1;
            for (;j >= start ; j--) {
                if (value < nums[j]) {
                    nums[j+1] = nums[j];
                }else {
                    nums[j + 1] = value;
                    break;
                }
            }
            // 不要写成 j < 0
            if (j < start) {
                nums[j + 1] = value;
            }
        }
    }

    public static void sortHeap(){

    }


}
