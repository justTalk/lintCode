package talk.algorithm.train;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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

    /*
     * @Author Andy
     * @Description 找出不重复的数字
     * @Date 10:35 2020-02-23
     * @Param
     * @return
     **/
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int single = nums[0];
        for (int i = 1; i < nums.length; i++) {
            single ^= nums[i];
        }
        return single;
    }

    /*
     * @Author Andy
     * @Description 求两个数组中的交集
     * @Date 10:42 2020-02-23
     * @Param
     * @return
     **/
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] smaller = nums1.length > nums2.length ? nums2 : nums1;
        int[] biger = nums1.length > nums2.length ? nums1 : nums2;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] tmp = new int[smaller.length];
        int size = 0;
        int j = 0;
        for (int i = 0; i < biger.length; i++) {
            for (;j < smaller.length; j++) {
                if (smaller[j] > biger[i]) {
                    break;
                }
                if (smaller[j] == biger[i]){
                    tmp[size++] = smaller[j];
                    j++;
                    break;
                }
            }
        }
        if (size != tmp.length) {
            int[] trimValue = new int[size];
            System.arraycopy(tmp, 0, trimValue, 0, size);
            return trimValue;
        }
        return tmp;
    }

    public int[] plusOne(int[] digits) {
        int preBit = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            preBit = (digits[i] + 1) / 10;
            if (preBit < 1) {
                digits[i] = digits[i] + 1 + preBit;
                return digits;
            }else {
                digits[i] = (digits[i] + 1) % 10;
            }
        }
        if (preBit > 0) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = preBit;
            System.arraycopy(digits, 0, newDigits, 1, digits.length);
            return newDigits;
        }

        return digits;
    }

    /*
     * @Author Andy
     * @Description 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * @Date 11:01 2020-02-23
     * @Param
     * @return
     **/
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0){
            return;
        }
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                int tmp = nums[i];
                nums[i++] = nums[j];
                nums[j] = tmp;
            }
        }
    }

    /*
     * @Author Andy
     * @Description 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * @Date 11:24 2020-02-23
     * @Param
     * @return 
     **/
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int needValue = target - nums[i];
            Integer index = map.get(needValue);
            if (index == null) {
                map.put(nums[i], i);
            }else {
             return new int[]{index, i};
            }
        }
        return null;
    }

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < board[i].length; j++) {
                if (set.contains(board[i][j])) {
                    return false;
                }else if (board[i][j] != '.') {
                    set.add(board[i][j]);
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < board[i].length; j++) {
                if (set.contains(board[j][i])) {
                    return false;
                }else if (board[j][i] != '.'){
                    set.add(board[j][i]);
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int startX = i * 3;
                int startY = j * 3;
                Set<Character> set = new HashSet<>();
                for (int k = startX; k < startX + 3; k++) {
                    for (int l = startY; l < startY + 3; l++) {
                        if (set.contains(board[k][l])) {
                            return false;
                        }else if (board[k][l] != '.'){
                            set.add(board[k][l]);
                        }
                    }
                }
            }
        }

        return true;
    }

    /*
     * @Author Andy
     * @Description 给定一个 n × n 的二维矩阵表示一个图像。将图像顺时针旋转 90 度。
     * @Date 12:06 2020-02-23
     * @Param
     * @return
     **/
    public static void rotate(int[][] matrix) {
        int sideLength = matrix.length;
        int maxRaduis = matrix.length / 2;
        for (int i = 0; i < maxRaduis; i++) {
            int step = sideLength - i * 2 - 1;
            for (int j = i; j < sideLength - i - 1; j++) {
                int x = j;
                int y = i;
                int direction = 1;
                int curValue = matrix[y][x];
                do{
                    if (direction == 1) {
                        int nextX = x + step;
                        if (nextX < sideLength - i) {
                            int tmp = matrix[y][nextX];
                            matrix[y][nextX] = curValue;
                            curValue = tmp;
                            x = nextX;
                        }else {
                            int nextY = x;
                            nextX = sideLength - i - 1;
                            int tmp = matrix[nextY][nextX];
                            matrix[nextY][nextX] = curValue;
                            curValue = tmp;
                            x = nextX;
                            y = nextY;
                        }
                    }else if (direction == 2) {
                        int nextY = y + step;
                        if (nextY < sideLength - i) {
                            int tmp = matrix[nextY][x];
                            matrix[nextY][x] = curValue;
                            curValue = tmp;
                            y = nextY;
                        }else {
                            nextY = sideLength - i - 1;
                            int nextX = sideLength - y - 1;
                            int tmp = matrix[nextY][nextX];
                            matrix[nextY][nextX] = curValue;
                            curValue = tmp;
                            y = nextY;
                            x = nextX;
                        }
                    }else if (direction == 3){
                        int nextX = x - step;
                        if (nextX >= i) {
                            int tmp = matrix[y][nextX];
                            matrix[y][nextX] = curValue;
                            curValue = tmp;
                            x = nextX;
                        }else {
                            nextX = i;
                            int nextY = x;
                            int tmp = matrix[nextY][nextX];
                            matrix[nextY][nextX] = curValue;
                            curValue = tmp;
                            x = nextX;
                            y = nextY;
                        }
                    }else {
                        int nextY = y - step;
                        if (nextY >= i) {
                            int tmp = matrix[nextY][x];
                            matrix[nextY][x] = curValue;
                            curValue = tmp;
                            y = nextY;
                        }else {
                            nextY = i;
                            int nextX = sideLength - y - 1;
                            int tmp = matrix[nextY][nextX];
                            matrix[nextY][nextX] = curValue;
                            curValue = tmp;
                            x = nextX;
                            y = nextY;
                        }

                    }
                    direction++;
                }while (direction <= 4);
            }
        }
    }
}
