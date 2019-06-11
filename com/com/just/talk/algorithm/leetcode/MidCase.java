package com.just.talk.algorithm.leetcode;


import com.just.talk.algorithm.imp.SingleNumber;

/**
 * Created by Liu On 2019/6/10
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class MidCase {

    /**
     * https://leetcode.com/problems/add-two-numbers/
     * 思路：按节点顺序进行加法操作，直到所有节点都计算完毕或者进位完毕
     */
    public SingleNumber.ListNode addTwoNumbers(SingleNumber.ListNode l1, SingleNumber.ListNode l2) {
        SingleNumber.ListNode header = null;
        SingleNumber.ListNode cursor = null;
        int bit = 0;
        //这里遍历都时候可以把条件设置为l1或l2有一个不为空，可以取消后面一个while，使代码更整洁
        while (l1 != null && l2 != null) {
            int add = l1.val + l2.val + bit;
            if (header == null) {
                header = new SingleNumber.ListNode(add  % 10);
                cursor = header;
            }else {
                cursor.next = new SingleNumber.ListNode(add  % 10);
                cursor = cursor.next;
            }
            bit = add / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 != null) {
            cursor.next =l1;
        }
        if (l2 != null) {
            cursor.next = l2;
        }
        while (bit != 0 && cursor.next != null){
            int add = cursor.next.val + bit;
            cursor.next.val = add % 10;
            bit = add / 10;
            cursor = cursor.next;
        }
        if (bit != 0) {
            cursor.next = new SingleNumber.ListNode(bit);
        }
        return header;
    }

    /**
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/
     * 找到字符串的最大不重复子串长度
     */
    public int lengthOfLongestSubstring(String s) {

    }
}
