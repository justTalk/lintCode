package com.just.talk.algorithm.leetcode;

import com.just.talk.algorithm.object.ListNode;

import java.util.HashMap;
import java.util.Stack;

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
                if (shouldRemove > 3) {
                    reman += shouldRemove;
                    shouldRemove = 0;
                }
                shouldRemove += 1;
                continue;
            }else if ('V' == s.charAt(i)){
                if (shouldRemove > 5) {
                    reman += shouldRemove + 5;
                }else {
                    reman += 5 - shouldRemove;
                }
            }else if('X' == s.charAt(i)){
                if (shouldRemove > 0 && shouldRemove < 10) {
                    reman += 10 - shouldRemove;
                }else {
                    reman += shouldRemove;
                    shouldRemove = 10;
                    continue;
                }
            }else if ('L' == s.charAt(i)){
                if (shouldRemove > 50) {
                    reman += shouldRemove + 50;
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

    /**
     * https://leetcode.com/problems/longest-common-prefix/
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < minLength) {
                minLength = strs[i].length();
            }
        }
        if (minLength == 0) {
            return "";
        }
        int i = 0;
        for (;i < minLength; i++) {
            char first = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (first != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0].substring(0, i);
    }

    /**
     * https://leetcode.com/problems/valid-parentheses/
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(')');
                continue;
            }
            if (s.charAt(i) == '[') {
                stack.push(']');
                continue;
            }
            if (s.charAt(i) == '{') {
                stack.push('}');
                continue;
            }
            if (stack.empty() || s.charAt(i) != stack.peek()) {
                return false;
            }else{
                stack.pop();
            }
        }
        return stack.empty();
    }

    /**
     * https://leetcode.com/problems/merge-two-sorted-lists/
     */
    public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode curNode1 = l1;
        ListNode curNode2 = l2;
        ListNode preHead = new ListNode(0);
        ListNode flowNode = preHead;
        while (curNode1 != null && curNode2 != null){
            if (curNode1.val < curNode2.val) {
                flowNode = flowNode.next = curNode1;
                curNode1 = curNode1.next;
            }else {
                flowNode = (flowNode.next = curNode2);
                curNode2 = curNode2.next;
            }
        }
        ListNode more;
        if (curNode1 != null) {
            more = curNode1;
        }else {
            more = curNode2;
        }
        while (more != null){
            flowNode = flowNode.next = more;
            more = more.next;
        }
        return preHead.next;
    }

    /**
     * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int validIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[validIndex] != nums[i]) {
                nums[++validIndex] = nums[i];
            }
        }
        return validIndex + 1;
    }

    /**
     * https://leetcode.com/problems/remove-element/
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null  || nums.length == 0) {
            return 0;
        }
        int validIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[++validIndex] = nums[i];
            }
        }
        return validIndex + 1;
    }
}
