package com.just.talk.algorithm.imp;

import java.util.List;

/**
 * Created by Liu On 2019/5/4
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class SingleNumber {
    /**
     * @param A: An integer array
     * @return: An integer
     */
    public int singleNumber(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return -1;
        }
        int onlyNum = 0;
        for (int i = 0; i < A.length; i++) {
            onlyNum ^= A[i];
        }
        return onlyNum;
    }

    /**
     * 优化分区函数后的实现 通过两个指针来进行分区 两个指针分别指向已分区的两个分区的尾部
     */
    public ListNode partitionOpt(ListNode head, int x){
        if (head == null) {
            return null;
        }
        ListNode smallNodeTail = head.val < x ? head : null;
        ListNode bigerNodeTail = head.val < x ? null : head;
        ListNode curNode = head.next;
        while (curNode != null){
            if (curNode.val < x) {
                if (bigerNodeTail != null) {
                    //swrap node
                    if (smallNodeTail == null) {
                        bigerNodeTail.next = curNode.next;
                        curNode.next = head;
                        smallNodeTail = curNode;
                        head = smallNodeTail;
                    }else {
                        bigerNodeTail.next = curNode.next;
                        curNode.next = smallNodeTail.next;
                        smallNodeTail.next = curNode;
                        smallNodeTail = curNode;
                    }
                    curNode = bigerNodeTail;
                }else {
                    smallNodeTail = curNode;
                }
            }else {
                bigerNodeTail = curNode;
            }
            curNode = curNode.next;
        }
        return head;
    }

    /**
     * 分区 保持数据原顺序 通过两个链表来分区
     * @param head: The first node of linked list
     * @param x:    An integer
     * @return: A ListNode
     */
    public ListNode partition(ListNode head, int x) {
        // write your code here
        ListNode smallHeader = null, smallTail = null;
        ListNode bigerHeader = null, bigerTail = null;
        if (head == null) {
            return null;
        }
        ListNode curNode = head;
        while (curNode != null){
            ListNode next = curNode.next;
            curNode.next = null;
            if (curNode.val < x) {
                if (smallTail == null) {
                    smallHeader = curNode;
                    smallTail = curNode;
                }else {
                    smallTail.next = curNode;
                    smallTail = smallTail.next;
                }
            }else {
                if (bigerTail == null) {
                    bigerHeader = curNode;
                    bigerTail = curNode;
                }else {
                    bigerTail.next = curNode;
                    bigerTail = bigerTail.next;
                }
            }
            curNode = next;
        }
        if (smallTail != null) {
            smallTail.next = bigerHeader;
        }
        return smallHeader != null ? smallHeader : bigerHeader;
    }


    /*
     * 移出有序数组中重复的元素 并返回剩余的元素个数
     * 正向思维 ：检查出相同的次数 不相同的数 = 总次数 - 相同的次数 中间产生了过多的交换数据操作
     * @param nums: An ineger array
     * @return: An integer
     */
    public int removeDuplicates(int[] nums) {
        // write your code here
        if (nums == null) {
            return 0;
        }
        int size = nums.length;
        if (nums.length <= 1){
            return size;
        }
        for (int i = 1; i < size; ) {
            if (nums[i - 1] == nums[i]) {
                size--;
                int tmp = nums[i];
                for (int j = i; j < size; j++) {
                    nums[j] = nums[j + 1];
                }
                nums[size] = tmp;
            }else {
                i++;
            }
        }
        return size;
    }

    /**
     * 逆向思维：直接找出不相同的数据
     */
    public int removeDuplicatesOpt(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[size] != nums[i]) {
                nums[++size] = nums[i];
            }
        }
        return size + 1;
    }



    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
