package com.just.talk.algorithm.leetcode;

import java.util.HashMap;

/**
 * Created by Liu On 2019/5/13
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class EasyCase {
    /**
     * https://leetcode.com/problems/two-sum/
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public int[] twoSum2(int[] nums, int target){
        HashMap<Integer, Integer> cached = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer key = target - nums[i];
            if (!cached.containsKey(key)) {
                cached.put(nums[i], i);
            }else {
                return new int[]{i, cached.get(key)};
            }
        }
        return nums;
    }

    /**
     * https://leetcode.com/problems/reverse-integer/
     */
    public int reverse(int x) {
        long res = 0;
        for (; x != 0; x/=10){
            res = res * 10 + x % 10;
        }
        return res > Integer.MAX_VALUE || res < Integer.MIN_VALUE ? 0 : (int) res;
    }

    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int tmp = x;
        long res = 0;
        for(; tmp != 0; tmp/=10){
            res = res * 10 + tmp%10;
        }
        return x == res;
    }

    /**MCMXCIV
     * https://leetcode.com/problems/roman-to-integer/
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     */
    public int romanToInt(String s) {
        int shouldRemove = 0;
        int reman = 0;
        for (int i = 0; i < s.length(); i++) {
            if ('I' == s.charAt(i)) {
                shouldRemove += 1;
                continue;
            }else if ('V' == s.charAt(i)){
                if (shouldRemove > 5) {
                    reman += shouldRemove;
                }else {
                    reman += 5 - shouldRemove;
                }
            }else if('X' == s.charAt(i)){
                if (shouldRemove > 0 && shouldRemove < 10) {
                    reman += 10 - shouldRemove;
                }else {
                    shouldRemove += 10;
                    continue;
                }
            }else if ('L' == s.charAt(i)){
                if (shouldRemove > 50) {
                    reman += shouldRemove;
                }else {
                    reman += 50 - shouldRemove;
                }
            }else if('C' == s.charAt(i)){
                if (shouldRemove > 0 && shouldRemove < 100) {
                    reman += 100 - shouldRemove;
                }else {
                    shouldRemove += 100;
                    continue;
                }
            }else if('D' == s.charAt(i)){
                reman += 500 - shouldRemove;
            }else if('M' == s.charAt(i)){
                reman += 1000 - shouldRemove;
            }
            shouldRemove = 0;
        }
        return reman + shouldRemove;
    }
}
