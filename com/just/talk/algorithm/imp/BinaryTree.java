package com.just.talk.algorithm.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Liu On 2019/4/30
 * Description:二叉树相关排序
 * email: mingming.liu@quvideo.com
 */
public class BinaryTree {
    /**
     * @param root: A Tree
     * @return: Inorder in ArrayList which contains node values.
     */
    public List<Integer> inorderTraversalNonRecursive(TreeNode root) {
        List<Integer> inOrder = new LinkedList<>();
        TreeNode curNode = root;
        Stack<TreeNode> stack = new Stack<>();
        boolean isback = false;
        while (curNode != null){
            if (!isback && curNode.left != null) {
                stack.push(curNode);
                curNode = curNode.left;
                continue;
            }
            inOrder.add(curNode.val);
            if (curNode.right == null && !stack.empty()){
                curNode = stack.pop();
                isback = true;
            }else {
                curNode = curNode.right;
                isback = false;
            }
        }
        return inOrder;
    }

    /**
     * @param root: A Tree
     * @return: Inorder in ArrayList which contains node values.
     */
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        // write your code here
        if (root == null) {
            return null;
        }
        List<Integer> inOrder = new LinkedList<>();
        List<Integer> leftNodes = inorderTraversalRecursive(root.left);
        if (leftNodes != null) {
            inOrder.addAll(leftNodes);
        }
        inOrder.add(root.val);
        List<Integer> rightNodes = inorderTraversalRecursive(root.right);
        if (rightNodes != null) {
            inOrder.addAll(rightNodes);
        }
        return inOrder;
    }


    public static class TreeNode {
       public int val;
       public TreeNode left, right;
       public TreeNode(int val) {
           this.val = val;
           this.left = this.right = null;
       }
    }
}
