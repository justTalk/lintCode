package com.just.talk.algorithm.leetcode;

import com.just.talk.algorithm.object.ListNode;
import com.just.talk.algorithm.object.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
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

    /**
     * https://leetcode.com/problems/search-insert-position/
     */
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums == null ? 0 : nums.length;
    }

    /**
     * https://leetcode.com/problems/count-and-say/
     */
    public String countAndSay(int n) {
        String say = "1";
        for (int i = 1; i < n; i++) {
            int count = 1;
            StringBuffer tmp = new StringBuffer();
            int j = 0;
            for (; j < say.length() - 1; j++) {
                if (say.charAt(j) != say.charAt(j+1)) {
                    tmp.append(count).append(say.charAt(j));
                    count = 1;
                    continue;
                }
                count++;
            }
            say = tmp.append(count).append(say.charAt(j)).toString();
        }
        return say;
    }

    /**
     * https://leetcode.com/problems/maximum-subarray/
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int max= nums[0];
        int dp = nums[0];
        for (int i = 0; i < len; i++) {
            dp = nums[i] + (dp > 0 ? dp : 0);
            if (dp > max) {
                max = dp;
            }
        }
        return max;
    }

    /**
     * 最大连续子序列 分之方法
     */
    private int helper(int[] nums, int left, int right) {
        if (left >= right) return nums[left];
        int mid = (left + right) >> 1;
        int leftAns = helper(nums, left, mid);
        int rightAns = helper(nums, mid + 1, right);
        int leftMax = nums[mid], rightMax = nums[mid + 1];
        int temp = 0;
        for (int i = mid; i >= left; --i) {
            temp += nums[i];
            if (temp > leftMax) leftMax = temp;
        }
        temp = 0;
        for (int i = mid + 1; i <= right; ++i) {
            temp += nums[i];
            if (temp > rightMax) rightMax = temp;
        }
        return Math.max(Math.max(leftAns, rightAns), leftMax + rightMax);
    }

    /**
     * https://leetcode.com/problems/length-of-last-word/submissions/
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            if (s.charAt(i) != ' ') {
                len++;
                continue;
            }
            if (len > 0) {
                break;
            }
        }
        return len;
    }

    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int tmp = 0;
        for(int i = len - 1; i >= 0; i--){
            tmp = digits[i] + 1;
            if (tmp < 10) {
                digits[i] = tmp;
                return digits;
            }else {
                digits[i] = tmp % 10;
                tmp = tmp / 10;
            }
        }
        int[] data = new int[len + 1];
        data[0] = tmp;
        for(int i = 1; i <= len; i++){
            data[i] = digits[i-1];
        }
        return data;
    }

    public String addBinary(String a, String b) {
        int offset = Math.abs(a.length() - b.length());
        String max = a;
        String min = b;
        if (a.length() < b.length()) {
            max = b;
            min = a;
        }
        int tmp = 0;
        int[] data = new int[max.length() + 1];
        for (int i = max.length() - 1; i >= 0; i--) {
            if (i - offset >= 0) {
                int sum = max.charAt(i) + min.charAt(i - offset) + tmp - 48 * 2;
                if (sum > 1) {
                    tmp = sum / 2;
                    data[i+1] = sum % 2;
                }else {
                    data[i+1] = sum;
                    tmp = 0;
                }
            }else {
                int sum = max.charAt(i) + tmp - 48;
                if (sum > 1) {
                    tmp = sum / 2;
                    data[i+1] = sum % 2;
                }else {
                    data[i+1] = sum;
                    tmp = 0;
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(tmp > 0 ? tmp : "");
        for (int i = 1; i < data.length; i++) {
            stringBuffer.append(data[i]);
        }
        return stringBuffer.toString();
    }


    /**
     * https://leetcode.com/problems/sqrtx/
     */
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        for(long i = 1; i <= x; i++){
            long tmp = i * i;
            if (tmp > x) {
                return (int) (i - 1);
            }
        }
        return 0;
    }

    /**
     * 牛顿迭代法求平方根
     */
    public int sqrt(int x) {
        long n = x;
        while (n * n > x) {
            n = (n + x / n) >> 1;
        }
        return (int) n;
    }

    /**
     * https://leetcode.com/problems/climbing-stairs/
     * 爬台阶 f(n) = f(n-1) + f(n-2)
     */
    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        int f = 1;
        int s = 1;
        for (int i = 3; i < n; i++) {
            f = f + s;
            s = f - s;
        }
        return 2 * f + s;
    }

    /**
     * 爬楼梯 广度优先算法 很容易栈溢出
     */
    public int climbStairsBreadthFirst(int n){
        if (n < 3) {
            return n;
        }
        Queue queue = new LinkedList();
        queue.offer(n);
        int count = 1;
        while (!queue.isEmpty()){
            int a = (int) queue.poll();
            if (a > 1) {
                count++;
                queue.offer(a-1);
                queue.offer(a-2);
            }
        }
        return count;
    }

    /**
     * 爬楼梯 深度优先算法
     */
    public int climbStairsDeepFirst(int n, int[] cache){
        if (n < 3) {
            return n;
        }
        int n1 = cache[n-1];
        if (n1 == 0) {
            n1 = climbStairsDeepFirst(n-1, cache);
            cache[n-1] = n1;
        }
        int n2 = cache[n-2];
        if (n2 == 0) {
            n2 = climbStairsDeepFirst(n-2, cache);
            cache[n-2] = n2;
        }
        return  n1 + n2;
    }

    /**
     * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null){
            if (pre.val != cur.val) {
                pre = pre.next = cur;
            }
            cur = cur.next;
        }
        pre.next = null;
        return head;
    }

    /**
     * 参考归并排序中的合并过程==》归并排序：
     * https://leetcode.com/problems/merge-sorted-array/
     * space n+m
     * time n+m
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] sorted = new int[n+m];
        int i = 0;
        int j = 0;
        for (int k = 0; k < sorted.length; k++) {
            if (i >= m) {
                sorted[k] = nums2[j];
                j++;
                continue;
            }
            if (j >= n) {
                sorted[k] = nums1[i];
                i++;
                continue;
            }

            if (nums1[i] < nums2[j]) {
                sorted[k] = nums1[i];
                i++;
            }else {
                sorted[k] = nums2[j];
                j++;
            }
        }
        for (int k = 0; k < sorted.length; k++) {
            nums1[k] = sorted[k];
        }
    }

    /**
     * 比较两棵树是否相等
     * 思路：
     * 1.深度遍历优先 递归实现
     * 2.广度遍历优先 队列实现
     * https://leetcode.com/problems/same-tree/
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p !=  null && q != null) {
            return q.val == p.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }

        return p == q;
    }

    /**
     * https://leetcode.com/problems/symmetric-tree/
     */
    public boolean isSymmetric(TreeNode root) {
        return false;
    }
}
