package com.just.talk.algorithm.imp;

/**
 * Created by Liu On 2019/5/6
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class BackPack {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // write your code here
        if (m == 0 || A == null || A.length == 0) {
            return 0;
        }
        return dynicOpt(m, A);
    }

    /**
     * 回溯思想的实现 但是出现了死循环
     */
    public int minUnUsed(int m, int[] A, int i){
        if (m == 0 || i == A.length) {
            return m;
        }
        int unUse = minUnUsed(m, A, i+1);
        if (m < A[i]) {
            return unUse;
        }else{
            int use = minUnUsed(m - A[i], A, i+1);
            return  use < unUse ? use:unUse;
        }
    }

    /**
     * 动态规划的设计思路
     * 事件复杂度 m * n
     * 空间复杂度 m
     */
    public int dynic(int m, int[] A){
        boolean[] store = new boolean[m+1];
        boolean[] tmp = new boolean[m+1];
        store[0] = true;
        if (m >= A[0]) {
            store[A[0]] = true;
        }
        for(int i = 1; i < A.length;i++){
            for(int j = 0; j <= m; j++){
                if (store[j]) {
                    int a = j + A[i];
                    if (a <= m) {
                        tmp[j+A[i]] = true;
                    }
                }
            }
            for(int j = 0; j <= m; j++){
                if (tmp[j]) {
                    store[j] = true;
                    tmp[j] = false;
                }
            }
        }
        for(int k = m; k>=0;k--){
            if (store[k]) {
                return k;
            }
        }
        return 0;
    }

    /**
     * 事件复杂度 m * n
     * 空间复杂度 m
     */
    public int dynicOpt(int m, int[] A){
        int[] store = new int[m+1];
        store[0] = 1;
        if (m >= A[0]) {
            store[A[0]] = 1;
        }

        for(int i = 1; i< A.length; i++){
            for(int j = 0; j<=m; j++){
                if (store[j] == 1) {
                    int a = j + A[i];
                    if (a <= m) {
                        if (store[a] == 0) {
                            store[a] = -1;
                        }
                    }
                }
            }
            for(int j = 0; j <= m; j++){
                if (store[j] == -1) {
                    store[j] = 1;
                }
            }
        }
        for(int k = m; k>=0;k--){
            if (store[k] == 1) {
                return k;
            }
        }
        return 0;
    }

    /**
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */
    public int uniquePaths(int m, int n) {
        // write your code here
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] table = new int[n][m];
        for (int i = 0; i < m; i++){
            table[0][i] = 1;
        }
        for (int i = 0; i < n; i++){
            table[i][0] = 1;
        }

        for(int i = 1; i < m; i++){
            for(int j =1; j< n; j++){
                table[j][i] = table[j - 1][i] + table[j][i-1];
            }
        }
        return table[n-1][m-1];
    }

    /*
     * @param A: A list of integers
     * @param elem: An integer
     * @return: The new length after remove
     */
    public int removeElement(int[] A, int elem) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        int size = -1;
        for (int i = 0;i < A.length ; i++){
            if (A[i] != elem) {
                A[++size] = A[i];
            }
        }
        return ++size;
    }

    /**
     *   A B C D C D
     * B # * # # # #
     * C # # * # * #
     * D # # # * # *
     * 将问题转换为单个字符对比，斜线的是子串
     * 最大公有子串
     * @param A: A string
     * @param B: A string
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        // write your code here
        if (A.length() == 0 || B.length() == 0) {
            return 0;
        }
        int maxCommonSub = 0;
        int[][] store = new int[A.length() + 1][B.length() + 1];
        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < B.length(); j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    store[i+1][j+1] = (i == 0 || j == 0) ? 1 : 1 + store[i][j];
                }
            }
        }
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (store[i][j] > maxCommonSub) {
                    maxCommonSub = store[i][j];
                }
            }
        }
        return maxCommonSub;
    }

    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        // write your code here
        if (triangle == null || triangle.length == 0){
            return 0;
        }
        boolean[][] state = new boolean[triangle.length][];
        for(int i = 0; i< triangle.length; i++){
            state[i] = new boolean[triangle[i].length];
        }
        return minTotalRecursive(triangle, 0, 0, state);
    }

    /**
     * 回溯 递归
     */
    public int minTotalRecursive(int[][] triangle, int level, int index, boolean[][] state){
        if (level == triangle.length - 1) {
            return triangle[level][index];
        }
        if (state[level][index]) {
            return triangle[level][index];
        }
        int[] curLevel = triangle[level];
        int left = minTotalRecursive(triangle, level + 1, index, state);
        int right = minTotalRecursive(triangle, level + 1, index + 1, state);
        curLevel[index] = left < right ? left + curLevel[index] : right + curLevel[index];
        state[level][index] = true;
        return curLevel[index];
    }

    public int minTotal(int[][] triangle){
        for (int i = triangle.length - 1; i > 0; i--) {
            for (int j = 0; j < triangle[i].length - 1; j++) {
                triangle[i - 1][j] = triangle[i][j] < triangle[i][j+1] ? triangle[i][j] + triangle[i - 1][j] : triangle[i][j + 1] + triangle[i - 1][j];
            }
        }
        return triangle[0][0];
    }

    /**
     * 最小路径和
     */
    public int minPathSum(int[][] grid){
        if (grid == null || grid.length == 0) {
            return 0;
        }
        for(int i = 1; i < grid[0].length; i++){
            grid[0][i] = grid[0][i-1] + grid[0][i];
        }
        for(int i = 1; i < grid.length; i++){
            grid[i][0] = grid[i - 1][0] + grid[i][0];
        }

        for(int i = 1; i< grid.length; i++){
            for(int j = 1; j < grid[i].length; j++){
                grid[i][j] = grid[i - 1][j] < grid[i][j-1] ?  grid[i - 1][j] + grid[i][j] : grid[i][j-1] + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[grid.length-1].length-1];
    }

    /**
     * 这种算法会超时，当长度唱过18后，目前不知道为什么，尴尬
     */
    public static int maxProductRecursive(int[] nums, int index, int lastIndex, int maxProduct){
        if (index == nums.length - 1) {
            if (index == lastIndex) {
                return maxProduct * nums[index] > maxProduct ? (maxProduct * nums[index] > nums[index] ? maxProduct * nums[index] : nums[index])  : maxProduct > nums[index] ? maxProduct: nums[index] ;
            }else{
                return maxProduct > nums[index] ? maxProduct : nums[index];
            }
        }
        int a = maxProductRecursive(nums, index + 1, index + 1, maxProduct * nums[index]);
        int b = maxProductRecursive(nums, index + 1, index, nums[index]);
        return a > b ? (a > maxProduct ? a : maxProduct) : (b > maxProduct ? b : maxProduct);
    }

    /**
     * 求乘积最大的连续子序列长度
     * @param nums: An array of integers
     * @return: An integer
     */
    public static int maxProduct(int[] nums) {
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
}
