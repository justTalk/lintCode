package talk.algorithm.leetcode.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import talk.algorithm.object.ListNode;

/**
 * @Description: 字节跳动过题库
 * @Author: Andy
 * @CreateDate: 2020/4/19 10:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/19 10:52
 * @Warn: 更新说明
 * @Version: 1.0
 */
public class Train {

  //给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
  //方案：双指针+数组坐标缓存
  public int lengthOfLongestSubstring(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    int[] chars = new int[128];
    int start = 0;
    int end = 0;
    int max = 1;
    chars[s.charAt(start)] = 1;
    for (int i = 1; i < s.length(); i++) {
      char c = s.charAt(i);
      if (chars[c] > 0 && chars[c] - 1 >= start) {
        start = chars[c];
        chars[c] = i + 1;
      }else {
        chars[c] = i + 1;
        end = i;
      }
      if (end - start + 1 > max) {
        max = end - start + 1;
      }
    }
    return max;
  }

  //反转链表：迭代法 || 递归法
  public ListNode reverseList(ListNode head) {
    ListNode cousor = null;
    while (head != null){
      ListNode next = head.next;
      if (cousor != null) {
        head.next = cousor;
        cousor = head;
      }else {
        cousor = head;
        cousor.next = null;
      }
      head = next;
    }
    return cousor;
  }

  //给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
  public String longestPalindrome(String s) {
    if (s == null || s.length() < 2) {
      return s;
    }
    int strLen = s.length();
    int maxStart = 0;  //最长回文串的起点
    int maxEnd = 0;    //最长回文串的终点
    int maxLen = 1;  //最长回文串的长度

    boolean[][] dp = new boolean[strLen][strLen];

    for (int r = 1; r < strLen; r++) {
      for (int l = 0; l < r; l++) {
        if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
          dp[l][r] = true;
          if (r - l + 1 > maxLen) {
            maxLen = r - l + 1;
            maxStart = l;
            maxEnd = r;

          }
        }

      }

    }
    return s.substring(maxStart, maxEnd + 1);
  }

  public List<List<Integer>> permute(int[] nums) {
    // 首先是特判
    int len = nums.length;
    // 使用一个动态数组保存所有可能的全排列
    List<List<Integer>> res = new LinkedList<>();

    if (len == 0) {
      return res;
    }

    boolean[] used = new boolean[len];
    List<Integer> path = new LinkedList<>();

    dfs(nums, len, 0, path, used, res);
    return res;
  }

  private void dfs(int[] nums, int len, int depth, List<Integer> path,boolean[] used, List<List<Integer>> res){
    if (depth == len) {
      res.add(new LinkedList<>(path));
    }
    for (int i = 0; i < nums.length; i++) {
      if (!used[i]) {
        used[i] = true;
        path.add(nums[i]);
        dfs(nums, len, depth + 1, path, used, res);
        used[i] = false;
        path.remove(path.size() - 1);
      }
    }
  }

  public List<List<Integer>> permuteMayRepeat(int[] nums){
    // 首先是特判
    int len = nums.length;
    // 使用一个动态数组保存所有可能的全排列
    List<List<Integer>> res = new LinkedList<>();

    if (len == 0) {
      return res;
    }
    Arrays.sort(nums);
    boolean[] used = new boolean[len];
    List<Integer> path = new LinkedList<>();

    dfsEx(nums, len, 0, path, used, res);
    return res;
  }

  private void dfsEx(int[] nums, int len, int depth, List<Integer> path,boolean[] used, List<List<Integer>> res){
    if (depth == len) {
      res.add(new LinkedList<>(path));
    }
    for (int i = 0; i < nums.length; i++) {
      if (used[i]) {
        continue;
      }

      // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
      // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
      if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
        continue;
      }

      path.add(nums[i]);
      used[i] = true;

      dfsEx(nums, len, depth + 1, path, used, res);
      // 回溯部分的代码，和 dfs 之前的代码是对称的
      used[i] = false;
      path.remove(path.size() -1);
      }
  }

  //给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
  //给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
  public List<String> letterCombinations(String digits) {
    if (digits == null || digits.length() == 0) {
      return new ArrayList<>();
    }
    List<String> allComb = new ArrayList<>();
    StringBuilder str = new StringBuilder();
    dfsLetter(0, digits.length(), str, allComb, digits);
    return allComb;
  }

  public void dfsLetter(int depth, int strLen, StringBuilder str, List<String> allComb, String digits){
    if (depth == strLen) {
      allComb.add(new String(str));
      return;
    }
    int index = digits.charAt(depth) - '2';
    for (int i = 0; i < keyValue[index].length; i++) {
      str.append(keyValue[index][i]);
      dfsLetter(depth + 1, strLen, str, allComb, digits);
      str.deleteCharAt(str.length() - 1);
    }
  }
  static char[][] keyValue = new char[8][];
  static {

    int asscii = 97;
    for (int i = 0; i < keyValue.length; i++) {
      if (i != 7 && i != 5){
        keyValue[i] = new char[3];
        for (int j = 0; j < keyValue[i].length; j++) {
          keyValue[i][j] = (char) asscii++;
        }
      }else {
        keyValue[i] = new char[4];
        for (int j = 0; j < keyValue[i].length; j++) {
          keyValue[i][j] = (char) asscii++;
        }
      }
    }
  }

}
