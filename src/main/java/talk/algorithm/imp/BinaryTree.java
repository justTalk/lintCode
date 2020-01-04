package talk.algorithm.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

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

    public List<Integer> postorderTraversalRecursive(TreeNode treeNode){
        if (treeNode == null) {
            return null;
        }
        List<Integer> list = new LinkedList<>();
        List<Integer> left = postorderTraversalRecursive(treeNode.left);
        if (left != null) {
            list.addAll(left);
        }
        List<Integer> right = postorderTraversalRecursive(treeNode.right);
        if (right != null) {
            list.addAll(right);
        }
        list.add(treeNode.val);
        return list;
    }

    /**
     * 参考了九章算法
     * 第一次没有做出来的原因时 没有准确区分重复遍历以至于产生了过多状态变量
     *
     */
    public List<Integer> postOrdertraversalNonRecursive(TreeNode treeNode){
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> traversaled = new ArrayList<>();
        if (treeNode == null) {
            return traversaled;
        }
        TreeNode curNode;
        TreeNode prev = null;
        stack.push(treeNode);
        while (!stack.empty()){
            curNode = stack.peek();
            //这里是遍历 因为遍历时 父节点总是子节点的前一个节点
            if (prev == null || prev.left == curNode || prev.right == curNode) {
                if (curNode.left != null) {
                    stack.push(curNode.left);
                }else if (curNode.right != null) {
                    stack.push(curNode.right);
                }
            }else if (curNode.left == prev) {
                //这里的入站需要考虑重复入站的问题 所以必须要保证前一个节点必须时当前节点的左子节点
                if (curNode.right != null ){
                    stack.push(curNode.right);
                }
            }else {
                traversaled.add(curNode.val);
                stack.pop();
            }

            prev =curNode;
        }
        return traversaled;
    }

    /**
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // write your code here
        List<List<Integer>> orderTraversal = new ArrayList<>();
        if (root == null) {
            return orderTraversal;
        }
        TreeNode emptyNode = new TreeNode(0);//设计一个空节点来区分层级 连续两个空节点则标示遍历结束了
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(emptyNode);
        nodes.offer(root);
        orderTraversal.add(new ArrayList<>());
        int index = -1;
        while (!nodes.isEmpty()){
            TreeNode treeNode = nodes.poll();
            boolean isNewLevel =  (treeNode == emptyNode);
            if (isNewLevel){
                if (index >= 0 && orderTraversal.get(index).isEmpty()) {
                    orderTraversal.remove(orderTraversal.size() - 1);
                    orderTraversal.remove(orderTraversal.size() - 1);
                    break;
                }
                index ++;
                orderTraversal.add(new ArrayList<>());
                nodes.offer(emptyNode);
                continue;
            }
            List<Integer> curLevel = orderTraversal.get(index);
            curLevel.add(treeNode.val);
            TreeNode left = treeNode.left;
            if (left != null) {
                nodes.offer(treeNode.left);
            }
            TreeNode right = treeNode.right;
            if (right != null) {
                nodes.offer(right);
            }
        }
        return orderTraversal;
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
