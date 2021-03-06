package talk.algorithm.train;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import Node;
import talk.algorithm.object.ListNode;
import talk.algorithm.object.TreeNode;

/**
 * @Description: https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/32/
 * @Author: Andy
 * @CreateDate: 2020-03-01 12:38
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-03-01 12:38
 * @Warn: 更新说明
 * @Version: 1.0
 */
public class PrimaryString {

  public void reverseString(char[] s) {
    if (s == null || s.length <= 1){
      return;
    }
    int mid = s.length / 2;
    for (int i = 0; i < mid; i++) {
      int targetIndex = s.length - i - 1;
      char tmp = s[targetIndex];
      s[targetIndex] = s[i];
      s[i] = tmp;
    }
  }

  /*
   * @Author Andy
   * @Description 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转
   * @Date 16:22 2020-03-01
   * @Param
   * @return
   **/
  public int reverse(int x) {
    String str = x + "";
    char[] reverse = new char[str.length()];
    int size = 0;
    for (int i = str.length() - 1; i >= 0; i--) {
       char c = str.charAt(i);
       if (c <= 9) {
         if (c != 0 || size != 0) {
           reverse[size++] = c;
         }
       }else {
         int j = ++size - 1;
         for (; j > 0; j--) {
           reverse[j] = reverse[j-1];
         }
         reverse[j] = c;
       }
    }
    long value = Long.valueOf(String.valueOf(reverse));
    if (value < Math.pow(-2, 31) || value > Math.pow(2, 31)) {
      return 0;
    }
    return Integer.valueOf(String.valueOf(reverse));
  }


  /*
   * @Author Andy
   * @Description 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
   * @Date 19:35 2020-03-01
   * @Param
   * @return
   **/
  public static int strStr(String haystack, String needle) {
    if (needle == null || haystack == null || needle.length() > haystack.length()) {
      return -1;
    }
    for (int i = 0; i < haystack.length(); i++) {
      int compareIndex = i;
      for (int j = 0; j < needle.length() && compareIndex < haystack.length(); j++) {
        if (needle.charAt(j) == haystack.charAt(compareIndex)) {
          compareIndex++;
        }else {
          break;
        }
      }
      if (compareIndex - i == needle.length()) {
        return i;
      }
    }
    return needle.length() == 0 && haystack.length() == 0 ? 0 : -1;

  }

  /*
   * @Author Andy
   * @Description 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1
   * @Date 07:55 2020-03-02
   * @Param
   * @return
   **/
  public int firstUniqChar(String s) {
    if (s == null || s.length() == 0) {
      return -1;
    }
    for (int i = 0; i < s.length(); i++) {
      char a = s.charAt(i);
      if (s.lastIndexOf(a) == i && s.indexOf(a) == i) {
        return i;
      }
    }
    return -1;
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode head = null, cursor = null;
    int lastValue = 0;
    do {
      ListNode newNode = new ListNode(l1.val + l2.val + lastValue);
      if (newNode.val >= 10) {
        lastValue = newNode.val / 10;
        newNode.val = newNode.val % 10;
      }else {
        lastValue = 0;
      }
      if (head == null)  {
        head = newNode;
        cursor = head;
      }else {
        cursor.next = newNode;
        cursor = cursor.next;
      }
      l1 = l1.next;
      l2 = l2.next;
    }while (l1 != null && l2 != null);

    cursor.next = l1 == null ? l2 : l1;
    if (cursor.next == null && lastValue > 0) {
      ListNode lastNode = new ListNode(lastValue);
      lastValue = 0;
      cursor.next = lastNode;
    }
    cursor = cursor.next;
    while (cursor != null && lastValue > 0){
      int count = lastValue + cursor.val;
      lastValue = count / 10;
      cursor.val = count % 10;
      if (cursor.next == null){
        break;
      }
      cursor = cursor.next;
    }
    if (lastValue > 0) {
      ListNode lastNode = new ListNode(lastValue);
      cursor.next = lastNode;
    }
    return head;
  }

  /**
   * @Author Andy
   * @Description 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
   * @Date
   * @Param
   * @return
   */
  public boolean isAnagram(String s, String t) {
    //TODO
    return false;
  }

  /**
   * @Author Andy
   * @Description 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字
   * 来源：力扣（LeetCode）
   * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
   * @Date
   * @Param
   * @return
   */
  public int findRepeatNumber(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] == nums[j]) {
          return nums[i];
        }
      }
    }
    return 0;
  }

  public int findRepeatNumberOtt(int[] nums) {
    boolean[] hasExited = new boolean[nums.length];
    for (int i = 0; i < nums.length; i++) {
      if (hasExited[nums[i]]) {
        return nums[i];
      }else {
        hasExited[nums[i]] = true;
      }
    }
    return 0;
  }

  /**
   * @Author Andy
   * @Description 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
   * @Date
   * @Param
   * @return
   */
  public boolean findNumberIn2DArray(int[][] matrix, int target) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (matrix[i][j] == target) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean findNumberIn2DArrayOpt(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return false;
    }
    int endI = matrix[0].length;
    int endJ = matrix.length;
    int firstBigerI = 0;
    boolean findFirstBiger = false;
    for (int i = 0; i < endI; i++) {
      if (matrix[0][i] == target) {
        return true;
      }
      if (matrix[0][i] > target) {
        firstBigerI = i;
        findFirstBiger = true;
        break;
      }
    }
    if (!findFirstBiger) {
      firstBigerI = endI - 1;
    }
    for (int i = endJ - 1; i >= 0; i--) {
      for (int j = firstBigerI; j >= 0 ; j--) {
        if (matrix[i][j] == target) {
          return true;
        }
      }
    }
    return false;
  }

  //请实现一个函数，把字符串 s 中的每个空格替换成"%20"
  public String replaceSpace(String s) {
    if (s == null || s.length() == 0) {
      return "";
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == ' ') {
        stringBuilder.append("%20");
      }else {
        stringBuilder.append(s.charAt(i));
      }
    }
    return stringBuilder.toString();
  }

  /**
   * @Author Andy
   * @Description 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
   * @Date
   * @Param
   * @return
   */
  public int[] reversePrint(ListNode head) {
    if (head == null) {
      return new int[0];
    }
    ListNode newHead = null;
    int size = 0;
    while (head != null){
      ListNode curNode = head;
      head = head.next;
      if (newHead == null) {
        newHead = curNode;
      }else {
        curNode.next = newHead;
        newHead = curNode;
      }
      size++;
    }
    ListNode curNode = newHead;
    int[] values = new int[size];
    int index = 0;
    while (index < size){
      values[index++] = curNode.val;
      curNode = curNode.next;
    }
    return values;
  }

  //输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder == null || preorder.length == 0) {
      return null;
    }
    int start = 0;
    int end = preorder.length - 1;
    return buildTree(preorder, inorder, start, end, start);
  }

  public TreeNode buildTree(int[] preorder, int[] inorder, int start, int end, int curIndex){
    if (start > end || curIndex >= preorder.length) {
      return null;
    }
    TreeNode curNode = new TreeNode(preorder[curIndex]);
    int index = indexOf(inorder, start, end, curNode.val);
    curNode.left = buildTree(preorder, inorder, start, index - 1, curIndex + 1);
    int rightFirstIndex = curIndex + index - start + 1;
    curNode.right = buildTree(preorder, inorder, index + 1, end, rightFirstIndex);
    return curNode;
  }

  public int indexOf(int[] array, int start, int end, int target){
    end = Math.min(array.length - 1, end);
    for (int i = start; i <= end; i++) {
      if (array[i] == target) {
        return i;
      }
    }
    return -1;
  }

  HashMap<Integer, Integer> cached = new HashMap<Integer, Integer>();
  public int fib(int n) {
    if (n == 0 || n == 1)
      return n;
    int result = 0;
    Integer a = cached.get(n - 1);
    if (a != null) {
      result = (result + a) % 1000000007;
    }else {
      int b = fib(n-1);
      cached.put(n-1, b);
      result = (result +  b)%1000000007;
    }
    a = cached.get(n - 2);
    if (a != null) {
      result =(result +  a)%1000000007;
    }else {
      int b = fib(n-2);
      cached.put(n-2, b);
      result = (result + b)%1000000007;
    }
    return result;
  }

  //把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
  //输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  

  public int minArray(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
      return -1;
    }
    for (int i = 1; i < numbers.length; i++) {
      if (numbers[i] < numbers[i-1]) {
        return numbers[i];
      }
    }
    return numbers[0];
  }

  //请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
  //
  //[["a","b","c","e"],
  //["s","f","c","s"],
  //["a","d","e","e"]]
  //
  //但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
  public boolean exist(char[][] board, String word) {
    if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) {
      return false;
    }
    boolean[][] steps = new boolean[board.length][board[0].length];
    Stack<Point> back = new Stack<>();
    Point lastPoint = null;
    for (int i = 0; i < word.length(); i++) {
      if (lastPoint == null) {
        FIND:
        for (int j = 0; j < board.length; j++) {
          for (int k = 0; k < board[j].length; k++) {
            if (board[j][k] == word.charAt(i) && !steps[j][k]) {
              lastPoint = new Point(j,k);
              back.push(lastPoint);
              steps[j][k] = true;
              break FIND;
            }
          }
        }
        if (back.isEmpty()) {
          return false;
        }
      }else {
        Point nextPoint = findNextPoint(lastPoint, back, board, steps, word.charAt(i));
        if (nextPoint != null) {
          steps[nextPoint.x][nextPoint.y] = true;
          lastPoint = nextPoint;
          back.push(lastPoint);
          continue;
        }

        if (back.isEmpty()) {
          return false;
        }
        while (!back.isEmpty()){
          Point point = back.pop();
          if (back.isEmpty()) {
            FIND:
            for (int j = 0; j < board.length; j++) {
              for (int k = 0; k < board[j].length; k++) {
                if (board[j][k] == board[point.x][point.y] && !steps[j][k]) {
                  lastPoint = new Point(j,k);
                  i = back.size();
                  back.push(lastPoint);
                  steps[j][k] = true;
                  break FIND;
                }
              }
            }
            if (back.isEmpty()) {
              return false;
            }
            break;
          }else {
            Point backLastPoint = back.peek();
            nextPoint = findNextPoint(backLastPoint, back, board, steps, board[point.x][point.y]);
            if (nextPoint == null){
              clearUseFlag(backLastPoint, board, steps, board[point.x][point.y]);
            }else {
              steps[point.x][point.y] = true;
              lastPoint = nextPoint;
              i = back.size();
              back.push(lastPoint);
              break;
            }
          }
        }
      }
    }
    return true;
  }

  private void clearUseFlag(Point lastPoint, char[][] board, boolean[][] steps, char c){
    int x1 = lastPoint.x - 1;
    int x2 = lastPoint.x + 1;
    int y1 = lastPoint.y - 1;
    int y2 = lastPoint.y + 1;
    if (x1 >= 0 && board[x1][lastPoint.y] == c) {
      steps[x1][lastPoint.y] = false;
    }
    if (x2 < board.length && board[x2][lastPoint.y] == c) {
      steps[x2][lastPoint.y] = false;
    }
    if (y1 >= 0 && board[lastPoint.x][y1] == c){
      steps[lastPoint.x][y1] = false;
    }
    if (y2 < board[lastPoint.x].length && board[lastPoint.x][y2] == c) {
      steps[lastPoint.x][y2] = false;
    }
  }

  private Point findNextPoint(Point lastPoint, Stack<Point> useStack, char[][] board, boolean[][] steps, char c){
    int x1 = lastPoint.x - 1;
    int x2 = lastPoint.x + 1;
    int y1 = lastPoint.y - 1;
    int y2 = lastPoint.y + 1;
    if (x1 >= 0 && board[x1][lastPoint.y] == c && (!steps[x1][lastPoint.y] || !useStack.contains(new Point(x1, lastPoint.y)))) {
      return new Point(x1, lastPoint.y);
    }
    if (x2 < board.length && board[x2][lastPoint.y] == c && (!steps[x2][lastPoint.y] || ! useStack.contains(new Point(x2, lastPoint.y)))) {
      return new Point(x2, lastPoint.y);
    }
    if (y1 >= 0 && board[lastPoint.x][y1] == c && (!steps[lastPoint.x][y1] || !useStack.contains(new Point(lastPoint.x, y1)))){
      return new Point(lastPoint.x, y1);
    }
    if (y2 < board[lastPoint.x].length && board[lastPoint.x][y2] == c && (!steps[lastPoint.x][y2] || !useStack.contains(new Point(lastPoint.x, y2)))) {
      return new Point(lastPoint.x, y2);
    }
    return null;
  }

  private class Point{

    Point(int x, int y){
      this.x = x;
      this.y = y;
    }
    int x;
    int y;

    @Override public boolean equals(Object o) {
      return o instanceof Point ? x == ((Point) o).x && y == ((Point) o).x : false;
    }
  }

  public boolean existA(char[][] board, String word) {
    int rowNum = board.length;
    if(rowNum < 1 || word.length() < 1) {
      return false;
    }
    int colNum = board[0].length;
    if(colNum * rowNum < word.length()) {
      return false; // 总个数小于单词数量，没必要遍历了，这个优化很有必要
    }
    for(int i = 0; i < board.length; i++) {
      for(int j = 0; j < board[0].length; j++) {
        if(board[i][j] == word.charAt(0)) {
          char temp = board[i][j];
          board[i][j] = '.'; // 修改临时的值，避免重复访问
          if(doSelect(board, word, 1, i, j, rowNum, colNum, word.length())) {
            return true;
          }
          board[i][j] = temp;
        }
      }
    }

    return false;
  }

  /**
   * 递归思想：每遍历一个之后，分别遍历前后上下的，并且把访问过的元素改成特定的元素值，避免后续搜索的时候重复访问
   * 这里顺序也挺重要的可能跟测试用例有关吧，但是通常也建议右下在前面
   * @param board
   * @param word
   * @param searchIndex 要搜索的元素所在字符串的下标
   * @param startRow 从哪个行开始
   * @param startCol 从哪个列开始
   * @param rowNum 二维数组行数
   * @param colNum 二维数组列数
   * @param strLen 字符串长度
   * @return
   */
  private boolean doSelect(char[][] board, String word, int searchIndex, int startRow, int startCol, int rowNum, int colNum, int strLen){
    if(searchIndex > strLen - 1) {
      return true;
    }
    // 向右
    if(startCol < colNum - 1 && word.charAt(searchIndex) == board[startRow][startCol + 1]){
      char temp = board[startRow][startCol + 1];
      board[startRow][startCol + 1] = '.';
      if(doSelect(board, word, searchIndex + 1, startRow, startCol + 1, rowNum, colNum, strLen)) {
        return true;
      }
      board[startRow][startCol + 1] = temp;
    }
    // 向下
    if(startRow < rowNum - 1 && word.charAt(searchIndex) == board[startRow+1][startCol]){
      char temp = board[startRow+1][startCol];
      board[startRow+1][startCol] = '.';
      if(doSelect(board, word, searchIndex + 1, startRow + 1, startCol, rowNum, colNum, strLen)) {
        return true;
      }
      board[startRow+1][startCol] = temp;
    }
    // 向左
    if(startCol > 0 && word.charAt(searchIndex) == board[startRow][startCol-1]){
      char temp = board[startRow][startCol-1];
      board[startRow][startCol-1] = '.';
      if(doSelect(board, word, searchIndex + 1, startRow, startCol - 1, rowNum, colNum, strLen)) {
        return true;
      }
      board[startRow][startCol-1] = temp;
    }
    // 向上
    if(startRow > 0 && word.charAt(searchIndex) == board[startRow-1][startCol]){
      char temp = board[startRow-1][startCol];
      board[startRow-1][startCol] = '.';
      if(doSelect(board, word, searchIndex + 1, startRow - 1, startCol, rowNum, colNum, strLen)) {
        return true;
      }
      board[startRow-1][startCol] = temp;
    }

    return false;
  }


  //地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
  // 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
  // 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
  public int movingCount(int m, int n, int k) {
    boolean[][] track = new boolean[m][n];
    track[0][0] = true;
    doNextStep(0, 0, m, n, k, track);
    int count = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (track[i][j]) {
          count++;
        }
      }
    }
    return count;
  }

  public boolean inK(int m, int n, int k){
    int count = 0;
    while (m > 0){
      count += m % 10;
      m = m / 10;
    }
    while (n > 0){
      count += n % 10;
      n = n / 10;
    }
    return count <= k;
  }

  public void doNextStep(int startRow, int startCol, int rowNum, int colNum, int k, boolean[][] track){
    //left
    if (startCol > 0 && !track[startRow][startCol - 1] && inK(startRow, startCol - 1, k)) {
      track[startRow][startCol - 1] = true;
      doNextStep(startRow, startCol - 1, rowNum, colNum, k, track);
    }
    //right
    if (startCol < colNum - 1 && !track[startRow][startCol + 1] && inK(startRow, startCol +1, k)) {
      track[startRow][startCol + 1] = true;
      doNextStep(startRow, startCol + 1, rowNum, colNum, k, track);
    }
    //down
    if (startRow > 0 && !track[startRow - 1][startCol] && inK(startRow - 1, startCol, k)){
      track[startRow - 1][startCol] = true;
      doNextStep(startRow - 1, startCol, rowNum, colNum, k, track);
    }
    //top
    if (startRow < rowNum -1 && !track[startRow + 1][startCol] && inK(startRow + 1, startCol, k)){
      track[startRow + 1][startCol] = true;
      doNextStep(startRow + 1, startCol, rowNum, colNum, k, track);
    }
  }

  //给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m] 。
  // 请问 k[0]*k[1]*...*k[m] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
  public int cuttingRope(int n) {
    int max = 1;
    for (int i = 2; i < n; i++) {
      int maxForM = cutM(n, i, 1);
      if (maxForM > max){
        max = maxForM;
      }
    }
    return max;
  }

  public int cutM(int remain, int m, int product){
    if (remain == 0 || m == 0 || remain == m) {
      return product;
    }
    if (m == 1) {
      return remain * product;
    }
    int max = 0;
    for (int i = 1; i < remain / m + 1; i++) {
      int tempMax = cutM(remain - i, m -1, product * i);
      if (tempMax > max) {
        max = tempMax;
      }
    }
    return max;
  }

  public int cuttingRopeA(int n){
    if (n < 4) {
      return n - 1;
    }
    int a = n / 3;
    int b = n % 3;
    if (b == 0) {
      return (int) Math.pow(3, a);
    }else if (b == 1){
      return (int) Math.pow(3, a - 1) * 4;
    }else {
      return (int) Math.pow(3, a) *  2;
    }
  }

  //请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。
  // 例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
  public int hammingWeight(int n) {
    int k = n >= 0 ? 0 : 1;
    n = n & ((1 << 31) - 1);
    while (n > 0){
      int mod = n % 2;
      if (mod != 0) {
        k++;
      }
      n = n >> 1;
    }
    return k;
  }

  //实现函数double Power(double base, int exponent)，
  //求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
  public double myPow(double x, int n) {
    if (n == 0) {
      return 1;
    }
    if (n == 1) {
      return x;
    }
    if (n == -1) {
      return 1 / x;
    }
    if (n % 2 == 0) {
      double t = myPow(x, n / 2);
      return t * t;
    } else {
      double t = myPow(x, n / 2);
      if (n < 0) {
        x = (1 / x);
      }
      return t * t * x;
    }
  }

  //输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
  public int[] printNumbers(int n) {
    if (n <= 0) {
      return new int[0];
    }
    int count = (int) (Math.pow(10, n)) - 1;
    int[] out = new int[count];
    for (int i = 0; i < count; i++) {
      out[i] = i + 1;
    }
    return out;
  }

  //给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
  //返回删除后的链表的头节点。
  //注意：此题对比原题有改动
  public ListNode deleteNode(ListNode head, int val) {
    if (head == null) {
      return null;
    }
    if (head.val == val) {
      return head.next;
    }
    ListNode preNode = head;
    ListNode cousor = head.next;
    while (cousor != null){
      if (cousor.val == val) {
        preNode.next = cousor.next;
        break;
      }
      preNode = cousor;
      cousor = cousor.next;
    }
    return head;
  }

  //定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
  public ListNode reverseList(ListNode head) {
    ListNode reverseHead = null;
    ListNode cousor = head;
    while (cousor != null){
      if (reverseHead == null) {
        reverseHead = cousor;
        cousor = cousor.next;
        reverseHead.next = null;
      }else {
        ListNode curNode = cousor;
        cousor = cousor.next;
        curNode.next = reverseHead;
        reverseHead = curNode;
      }
    }
    return reverseHead;
  }

  //输入一个链表，输出该链表中倒数第k个节点。
  //为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
  //例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
  public ListNode getKthFromEnd(ListNode head, int k) {
    ListNode cousor = head;
    int size = 0;
    while (cousor != null){
      size++;
      cousor = cousor.next;
    }
    int index =  size - k;
    cousor = head;
    int i = 0;
    while (cousor != null){
      if (i++ == index) {
        return cousor;
      }
      cousor = cousor.next;
    }
    return null;
  }

  //输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
  public int[] exchange(int[] nums) {
    int firstEvenIndex = -1;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] % 2 != 0) {
        if (firstEvenIndex >= 0) {
          int temp = nums[firstEvenIndex];
          nums[firstEvenIndex] = nums[i];
          nums[i] = temp;
          firstEvenIndex++;
        }
      }else if(firstEvenIndex < 0){
        firstEvenIndex = i;
      }
    }
    return nums;
  }

  //输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }

    ListNode head = null, cousor = null;
    while (l1 != null && l2 != null){
      if (l1.val <= l2.val){
        if (head == null){
          head = l1;
          cousor = l1;
        }else {
          cousor.next = l1;
          cousor = cousor.next;
        }
        l1 = l1.next;
        cousor.next = null;
      }else {
        if (head == null) {
          head = l2;
          cousor = l2;
        }else {
          cousor.next = l2;
          cousor = cousor.next;
        }
        l2 = l2.next;
        cousor.next = null;
      }
    }
    if (l2 != null) {
      cousor.next = l2;
    }
    if (l1 != null) {
      cousor.next = l1;
    }
    return head;
  }

  //请完成一个函数，输入一个二叉树，该函数输出它的镜像。
  public TreeNode mirrorTree(TreeNode root) {
    if (root == null){
      return null;
    }
    TreeNode rightNode = root.right;
    root.right = root.left;
    root.left = rightNode;
    mirrorTree(root.right);
    mirrorTree(root.left);
    return root;
  }

  //请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的
  public boolean isSymmetric(TreeNode root) {
    List<TreeNode> rootNodes = new ArrayList<>();
    rootNodes.add(root);
    return isSymmetric(rootNodes);
  }

  public boolean isSymmetric(List<TreeNode> parentNodes) {
    if (parentNodes.isEmpty()) {
      return true;
    }
    int start = 0;
    int end = parentNodes.size() - 1;
    while (start < end){
      if (parentNodes.get(start) == null || parentNodes.get(end) == null) {
        if (parentNodes.get(start) != parentNodes.get(end)) {
          return false;
        }
      }else if(parentNodes.get(start).val != parentNodes.get(end).val) {
        return false;
      }
      start++;
      end--;
    }
    List<TreeNode> subNodes = new ArrayList<>();
    for (int i = 0; i < parentNodes.size(); i++) {
      TreeNode parent = parentNodes.get(i);
      if (parent != null) {
        subNodes.add(parent.left);
        subNodes.add(parent.right);
      }
    }
    parentNodes.clear();
    parentNodes = null;
    return isSymmetric(subNodes);
  }

  //输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
  public int[] spiralOrder(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return new int[0];
    }
    int padding = 0;
    int rowNums = matrix.length;
    int colNums = matrix[0].length;
    int maxPadding = rowNums > colNums ? colNums / 2 : rowNums / 2;
    int[] order = new int[rowNums * colNums];
    int index = 0;
    while (padding <= maxPadding && index < order.length){
      // right
      for (int i = padding; i < colNums - padding; i++) {
        order[index++] = matrix[padding][i];
      }
      // down
      for (int i = padding + 1; i < rowNums - padding; i++) {
        order[index++] = matrix[i][colNums - padding - 1];
      }
      // left
      if (padding != rowNums - padding - 1) {
        for (int i = colNums - padding - 2; i >= padding; i--) {
          order[index++] = matrix[rowNums - padding - 1][i];
        }
      }

      // top
      if (padding != colNums - padding - 1) {
        for (int i = rowNums - padding - 2; i > padding; i--) {
          order[index++] = matrix[i][padding];
        }
      }
      padding++;
    }
    return order;
  }

  //在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 "dabbcb"
  public char firstUniqCharOpt(String s) {
    if (s == null || s.length() == 0) {
      return ' ';
    }
    int[] chars = new int[128];
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (chars[c] == 0) {
        chars[c] = i + 1;
      }else {
        chars[c] = -1;
      }
    }
    int minIndex = s.length() + 1;
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] > 0){
        if (chars[i] < minIndex) {
          minIndex = chars[i];
        }
      }
    }
    if (minIndex == s.length() + 1) {
      return ' ';
    }else {
      return s.charAt(minIndex -1);
    }
  }

  //给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
  // 每段绳子的长度记为 k[0],k[1]...k[m] 。请问 k[0]*k[1]*...*k[m] 可能的最大乘积是多少？
  // 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
  //答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
  public int cuttingRope(int n) {
    if(n<2) {
      return 0;
    }
    if(n==2) {
      return 1;
    }
    if(n==3) {
      return 2;
    }
        /*
        d[i]表示长度为i的绳子剪完后各段乘积的最大值, 最终目标是dp[n]
        dp[i]可以看成是长度为i-k的绳子的最大值和长度为k的绳子的最大值的乘积, 子问题最优, 所以dp[i]也是最优
        状态转移方程: dp[i] = max(dp[i], dp[i-k]*dp[k])
        */
    //下面的初始值不同于上面的特殊情况, 上面是必须剪一刀, 下面的三个初始值不用再减了
    BigInteger[] dp = new BigInteger[n+1];
    dp[1] = new BigInteger("1");//内循环中会用到这个值
    dp[2] = new BigInteger("2");
    dp[3] = new BigInteger("3");
    for(int i=4; i<=n; i++){
      //初始化dp[i]
      dp[i] = new BigInteger("0");
      //长度为i的绳子有i-1个剪切位置; 不论i是奇数还是偶数, 只考虑前i/2个剪切位置即可, 后面的剪切位置是重复的
      for(int j=1; j<=i/2; j++){
        //因为j和i-j都小于i, 所以这是自底向上的计算方式
        dp[i] = dp[i].max(dp[j].multiply(dp[i-j]));
      }
    }
    return dp[n].mod(new BigInteger("1000000007")).intValue();
  }

  //请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
  //例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"0123"及"-1E-16"都表示数值，
  //但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
  public boolean isNumber(String s) {
    if(s == null || s.length() == 0){
      return false;
    }
    //标记是否遇到相应情况
    boolean numSeen = false;
    boolean dotSeen = false;
    boolean eSeen = false;
    char[] str = s.trim().toCharArray();
    for(int i = 0;i < str.length; i++){
      if(str[i] >= '0' && str[i] <= '9'){
        numSeen = true;
      }else if(str[i] == '.'){
        //.之前不能出现.或者e
        if(dotSeen || eSeen){
          return false;
        }
        dotSeen = true;
      }else if(str[i] == 'e' || str[i] == 'E'){
        //e之前不能出现e，必须出现数
        if(eSeen || !numSeen){
          return false;
        }
        eSeen = true;
        numSeen = false;//重置numSeen，排除123e或者123e+的情况,确保e之后也出现数
      }else if(str[i] == '-' || str[i] == '+'){
        //+-出现在0位置或者e/E的后面第一个位置才是合法的
        if(i != 0 && str[i-1] != 'e' && str[i-1] != 'E'){
          return false;
        }
      }else{//其他不合法字符
        return false;
      }
    }
    return numSeen;
  }

  //输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
  //B是A的子结构， 即 A中有出现和B相同的结构和节点值。
  public boolean isSubStructure(TreeNode A, TreeNode B, TreeNode cursorA, TreeNode cursorB) {
    if (A == null || B == null || cursorA == null || cursorB == null) {
      return false;
    }
    if (cursorA.val == cursorB.val){
      boolean sub = true;
      if (cursorB.left != null) {
        sub = sub && isSubStructure(A, B, cursorA.left, cursorB.left);
      }
      if (cursorB.right != null) {
        sub = sub && isSubStructure(A, B, cursorA.right, cursorB.right);
      }
      return sub;
    }
    return isSubStructure(A.left, B, A.left, B) || isSubStructure(A.right, B, A.right, B);
  }

  //我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
  public int nthUglyNumber(int n) {
    long[] dp = new long[n+1];
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      int minIndex = 1;
      MIN:
      for (; minIndex < i; minIndex++) {
        if (dp[minIndex] * 5 > dp[i-1]) {
          break MIN;
        }
      }
      int maxIndex = minIndex;
      MAX:
      for (; maxIndex < i; maxIndex++) {
        if (dp[maxIndex] * 2 > dp[i-1]) {
          break MAX;
        }
      }
      long nextUglyNumber = dp[i - 1] * 2;
      for (; minIndex <= maxIndex; minIndex++){
        if (dp[minIndex] * 2 > dp[i-1]) {
          if (dp[minIndex] * 2 < nextUglyNumber) {
            nextUglyNumber = dp[minIndex] * 2;
          }
          continue;
        }
        if (dp[minIndex] * 3 > dp[i-1]) {
          if (dp[minIndex] * 3 < nextUglyNumber) {
            nextUglyNumber = dp[minIndex] * 3;
          }
          continue;
        }
        if (dp[minIndex] * 5 > dp[i-1]) {
          if (dp[minIndex] * 5 < nextUglyNumber) {
            nextUglyNumber = dp[minIndex] * 5;
          }
          continue;
        }
      }
      dp[i] = nextUglyNumber;
    }
    return (int) dp[n];
  }


  //请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
  public int lengthOfLongestSubstring(String s) {
    int[] lastIndex = new int[128];
    int maxLength = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (lastIndex[c] == 0) {
        lastIndex[c] = i + 1;
        if (i == s.length() - 1) {
          int minIndex = i;
          for (int j = 1; j < lastIndex.length; j++) {
            if (lastIndex[j] != 0 && lastIndex[j] - 1 < minIndex) {
              minIndex = lastIndex[j] - 1;
            }
          }
          if (i + 1 - minIndex > maxLength) {
            maxLength = i + 1 - minIndex;
          }
        }
      }else {
        int minIndex = i;
        for (int j = 1; j < lastIndex.length; j++) {
          if (lastIndex[j] != 0 && lastIndex[j] - 1 < minIndex) {
            minIndex = lastIndex[j] - 1;
          }
          if (lastIndex[j] < lastIndex[c]) {
            lastIndex[j] = 0;
          }
        }
        if (i - minIndex > maxLength) {
          maxLength = i - minIndex;
        }
        lastIndex[c] = i + 1;
      }
    }
    return maxLength;
  }

  //在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
  //你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
  public int maxValue(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    int rowNums = grid.length;
    int colNums = grid[0].length;
    int[][] maxValues = new int[rowNums][colNums];
    for (int i = 0; i < rowNums; i++) {
      for (int j = 0; j < colNums; j++) {
        maxValues[i][j] = grid[i][j];
        if (i - 1 >= 0) {
          maxValues[i][j] += maxValues[i-1][j];
        }
        if (j - 1 >= 0) {
          if (maxValues[i][j -1] + grid[i][j] > maxValues[i][j]) {
            maxValues[i][j] = maxValues[i][j -1] + grid[i][j];
          }
        }
      }
    }
    return maxValues[rowNums - 1][colNums -1];
  }

  //给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
  //一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
  public int translateNum(int num) {
    String strNums = String.valueOf(num);
    int[] lastValues = new int[2];
    int lastNum = -1;
    for (int i = 0; i < strNums.length(); i++) {
      char c = strNums.charAt(i);
      if (lastNum == '1' || (lastNum == '2' && c <= '5')) {
        int oneBit = lastValues[0] + lastValues[1];
        lastValues[1] = lastValues[0];
        lastValues[0] = oneBit;
      }else if (i == 0){
        lastValues[0] = 1;
        lastValues[1] = 0;
      }else{
        lastValues[0] =   lastValues[0] + lastValues[1];
        lastValues[1] = 0;
      }
      lastNum = c;
    }
    return lastValues[0] + lastValues[1];
  }

  //输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
  public String minNumber(int[] nums) {
    String[] strs = new String[nums.length];
    for(int i = 0; i < nums.length; i++) {
      strs[i] = String.valueOf(nums[i]);
    }
    fastSort(strs, 0, strs.length - 1);
    StringBuilder res = new StringBuilder();
    for(String s : strs) {
      res.append(s);
    }
    return res.toString();
  }
  void fastSort(String[] strs, int l, int r) {
    if(l >= r) {
      return;
    }
    int i = l, j = r;
    String tmp = strs[i];
    while(i < j) {
      while((strs[j] + strs[l]).compareTo(strs[l] + strs[j]) >= 0 && i < j) {
        j--;
      }
      while((strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0 && i < j) {
        i++;
      }
      tmp = strs[i];
      strs[i] = strs[j];
      strs[j] = tmp;
    }
    strs[i] = strs[l];
    strs[l] = tmp;
    fastSort(strs, l, i - 1);
    fastSort(strs, i + 1, r);
  }

  //数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
  //请写一个函数，求任意第n位对应的数字。count = 10^i-1 * i * 9 (0<= i < ~)
  public int findNthDigit(int n) {
    if (n < 10) {
      return n;
    }
    long minMaxBitCount = 0;
    int bitCount = 0;
    while (minMaxBitCount <= n){
      minMaxBitCount += Math.pow(10, bitCount++) * bitCount * 9;
    }
    minMaxBitCount -= Math.pow(10, bitCount - 1) * bitCount * 9;
    int baseNum = (int) Math.pow(10, bitCount - 1) - 1;
    long number = baseNum + (n - minMaxBitCount) / bitCount;
    long offset = (n - minMaxBitCount) % bitCount;
    if (offset != 0){
      number++;
      int mod = (int) Math.pow(10, bitCount - offset);
      return (int) (number / mod % 10);
    }else {
      return (int) (number % 10);
    }
  }

  //输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
  //例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
  public int countDigitOne(int n) {
    if (n <= 0) {
      return 0;
    }
    String num = String.valueOf(n);
    int high = num.charAt(0) - '0';
    int pow = (int) Math.pow(10, num.length() - 1);
    int last = n - high * pow;
    if (high == 1) {
      return countDigitOne(pow - 1) + last + 1 + countDigitOne(last);
    }else {
      return pow + high * countDigitOne(pow - 1) + countDigitOne(last);
    }
  }

  //输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
  //要求时间复杂度为O(n)。
  public int maxSubArray(int[] nums) {
    if (nums == null || nums.length <= 0) {
      return 0;
    }
    for (int i = 1; i < nums.length; i++) {
      nums[i] = Math.max(nums[i - 1] + nums[i],nums[i]);
    }
    int maxSub = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (maxSub < nums[i]) {
        maxSub = nums[i];
      }
    }
    return maxSub;
  }

  //从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
  public void levelOrder(List<TreeNode> parent, List<List<TreeNode>> next) {
    List<TreeNode> current = new LinkedList<>();
    List<TreeNode> nextTreeNodes = new LinkedList<>();
    for (TreeNode r : parent) {
      current.add(r);
      if (r.left != null) {
        nextTreeNodes.add(r.left);
      }
      if (r.right != null) {
        nextTreeNodes.add(r.right);
      }
    }
    next.add(current);
    if (!nextTreeNodes.isEmpty()) {
      levelOrder(nextTreeNodes, next);
    }
  }

  //输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
  public int[] getLeastNumbers(int[] arr, int k) {
    quickSort(arr, 0, arr.length - 1);
    k = Math.min(k, arr.length);
    int[] leastNumbers = new int[k];
    System.arraycopy(arr, 0, leastNumbers, 0, k);
    return leastNumbers;
  }

  public void quickSort(int[] arr, int start, int end){
    if (start >= end) {
      return;
    }
    int povit = partion(arr, start, end);
    quickSort(arr, start, povit - 1);
    quickSort(arr, povit + 1, end);
  }

  public int partion(int[] arr, int start, int end){
    int povit = end;
    int lastSmallerPovitIndex = start - 1;
    for (int i = start; i < end; i++) {
      if (arr[i] < arr[povit]) {
        lastSmallerPovitIndex++;
        if (i == lastSmallerPovitIndex) {
          continue;
        }
        int tmp = arr[lastSmallerPovitIndex];
        arr[lastSmallerPovitIndex] = arr[i];
        arr[i] = tmp;
      }
    }
    lastSmallerPovitIndex++;
    int tmp = arr[lastSmallerPovitIndex];
    arr[lastSmallerPovitIndex] = arr[povit];
    arr[povit] = tmp;
    return lastSmallerPovitIndex;
  }

  //数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
  //你可以假设数组是非空的，并且给定的数组总是存在多数元素。
  //1.统计数字 2.排序去中位数 3.摩尔投票法
  public int majorityElement(int[] nums) {
    int x = 0, votes = 0;
    for(int num : nums){
      if(votes == 0) {
        x = num;
      }
      votes += num == x ? 1 : -1;
    }
    return x;
  }

  //输入一个字符串，打印出该字符串中字符的所有排列。
  //你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
  public String[] permutation(String s) {
    HashMap<String, Boolean> memory = new HashMap<>();
    Boolean hasCache = new Boolean(true);
    int[] count = new int[128];
    String[] per = new String[1];
    per[0] = String.valueOf(s.charAt(0));
    count[s.charAt(0)] = 1;
    for (int i = 1; i < s.length(); i++) {
      char curChar = s.charAt(i);
      int repeate = count[curChar];
      count[curChar] = repeate + 1;
      int curCount = per.length * (per[0].length() + 1 - repeate) - repeate;
      String[] curStr = new String[curCount];
      int index = 0;
      for (int j = 0; j < per.length; j++) {
        String lastString = per[j];
        for (int k = 0; k <= lastString.length(); k++) {
          char[] str = new char[lastString.length() + 1];
          str[k] = curChar;
          if (k >= 1) {
            System.arraycopy(lastString.toCharArray(), 0, str, 0, k);
          }
          System.arraycopy(lastString.toCharArray(), k, str, k + 1, lastString.length() - k);
          String string = new String(str);
          if (!memory.containsKey(string)) {
            curStr[index++] = string;
            memory.put(string, hasCache);
          }
        }
      }
      per = curStr;
      memory.clear();
    }
    return per;
  }

  //输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
  public Node treeToDoublyList(Node root) {
    if (root != null && root.left == null && root.right == null) {
      root.left = root;
      root.right = root;
      return root;
    }
    return treeToDoublyList(root, true);
  }

  public Node treeToDoublyList(Node root, boolean cycle) {
    if (root == null || (root.left == null && root.right == null)) {
      return root;
    }
    Node leftHead = treeToDoublyList(root.left, false);
    Node rightHead = treeToDoublyList(root.right, false);
    Node leftTail = leftHead, rightTail = rightHead;
    while (leftTail != null){
      if (leftTail.right == null) {
        break;
      }
      leftTail = leftTail.right;
    }
    while (rightTail != null){
      if (rightTail.right == null) {
        break;
      }
      rightTail = rightTail.right;
    }
    if (leftTail == null) {
      leftHead = leftTail = root;
    }else {
      leftTail.right = root;
      root.left = leftTail;
    }
    if (rightHead == null) {
      rightTail = rightHead = root;
    }else {
      root.right = rightHead;
      rightHead.left = root;
    }
    if (cycle) {
      leftHead.left = rightTail;
      rightTail.right = leftHead;
    }
    return leftHead;
  }

  class Node {
    public int val;
    public Node left;
    public Node right;
    public Node random;
    public Node next;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
      val = _val;
      left = _left;
      right = _right;
    }
  }

  //请实现 copyRandomList 函数，复制一个复杂链表。
  //在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
  public Node copyRandomList(Node head) {
    HashMap<Node, Node> relation = new HashMap<>();
    Node newHead = null;
    Node cousor = head;
    while (cousor != null){
      Node curNode = new Node(cousor.val);
      relation.put(cousor, curNode);
      cousor = cousor.next;
    }
    cousor = head;
    Node newCousor = null;
    while (cousor != null){
      Node newNode = relation.get(cousor);
      Node randowNode = cousor.random != null ? relation.get(cousor.random) : null;
      newNode.random = randowNode;
      if (newHead == null) {
        newHead = newNode;
        newCousor = newHead;
      }else {
        newCousor.next = newNode;
        newCousor = newCousor.next;
      }
      cousor = cousor.next;
    }

    return newHead;
  }

  //输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
  //从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> paths = new LinkedList<>();
    onePath(root, sum, paths, new LinkedList<>());
    return paths;
  }

  public void onePath(TreeNode root, int remain, List<List<Integer>> paths, List<Integer> path){
    if (root == null || root.val > remain) {
      return;
    }
    path.add(root.val);
    List<Integer> copyPath = new LinkedList<>();
    for (Integer i : path) {
      copyPath.add(i);
    }
    if (remain - root.val == 0 && root.left == null && root.right == null) {
      paths.add(path);
    }else {
      onePath(root.left, remain - root.val, paths, path);
      onePath(root.right, remain - root.val, paths, copyPath);
    }
  }

  //输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
  //如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
  public boolean verifyPostorder(int[] postorder) {
    if (postorder == null || postorder.length == 0) {
      return true;
    }
    return verifyPostorder(postorder, postorder.length - 1, 0);
  }

  public boolean verifyPostorder(int[] postorder, int root, int start) {
    if (postorder == null || postorder.length == 0) {
      return false;
    }
    int rootValue = postorder[root];
    int right  = start;
    for (; right <= root; right++) {
      if (postorder[right] >= rootValue) {
        break;
      }
    }
    int rightLength = root - right;
    int left = right - 1;
    boolean verifySubTree = true;
    if (rightLength > 0) {
      for (; right < root; right++) {
        if (postorder[right] < rootValue) {
          return false;
        }
      }
      right = root - 1;
      verifySubTree = verifyPostorder(postorder, right, right - rightLength + 1);
    }
    if (!verifySubTree) {
      return false;
    }

    if (left > start) {
      for (int i = start; i <= left; i++) {
        if (postorder[i] > rootValue) {
          return false;
        }
      }
      verifySubTree = verifyPostorder(postorder, left, start);
    }

    return verifySubTree;
  }

  //输入两个链表，找出它们的第一个公共节点。
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }
    HashMap<ListNode, ListNode> cache = new HashMap<>();
    while (headA != null){
      cache.put(headA, headA);
      headA = headA.next;
    }
    while (headB != null){
      if (cache.containsKey(headB)) {
        return headB;
      }
      headB = headB.next;
    }
    return null;
  }

  //统计一个数字在排序数组中出现的次数。
  public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == target){
        count++;
        continue;
      }
      if (count > 0) {
        break;
      }
    }
    return count;
  }

  //一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
  //在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
  public int missingNumber(int[] nums) {
    int i = 0;
    for (; i < nums.length; i++) {
      if (nums[i] != i) {
        return i;
      }
    }
    return i;
  }

  //给定一棵二叉搜索树，请找出其中第k大的节点
  int res, k;
  public int kthLargest(TreeNode root, int k) {
    this.k = k;
    dfs(root);
    return res;
  }
  void dfs(TreeNode root) {
    if(root == null) {
      return;
    }
    dfs(root.right);
    if(k == 0) {
      return;
    }
    if(--k == 0) {
      res = root.val;
    }
    dfs(root.left);
  }

  public int inOrderTree(TreeNode root, int[] values, int start){
    if (root == null) {
      return start;
    }
    int left = inOrderTree(root.left, values, start);
    values[left++] = root.val;
    return inOrderTree(root.right, values, left);
  }

  //输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度
  public int maxDepth(TreeNode root, int depth) {
    if (root == null) {
      return depth;
    }
    depth++;
    return Math.max(maxDepth(root.left, depth), maxDepth(root.right, depth));
  }

  //输入一棵二叉树的根节点，判断该树是不是平衡二叉树。
  //如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
  public boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }
    int leftDepth = maxDepth(root.left, 0);
    int rightDepth = maxDepth(root.right, 0);
    if (Math.abs(leftDepth - rightDepth) > 1) {
      return false;
    }
    return isBalanced(root.left) && isBalanced(root.right);
  }

  //一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
  //请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
  public int[] singleNumbers(int[] nums) {
    int xor = 0;
    for (int i = 0; i < nums.length; i++) {
      xor ^= nums[i];
    }
    int a = 0;
    int b = 0;
    int h = 1;
    while ((xor & h) == 0) {
      h<<=1;
    }
    for (int i = 0; i < nums.length; i++) {
      if ((nums[i] & h) == 0) {
        a ^= nums[i];
      }else {
        b ^= nums[i];
      }
    }
    return new int[]{a, b};
  }

  //在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字
  public int singleNumber(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int result = 0;

    for (int i = 0; i < 32; i++) {
      //统计该位1的出现次数情况
      int count = 0;
      int index = 1 << i;
      for (int j : nums) {
        //该位与操作后的结果不为0，则表示该位为1的情况出现了
        if ((index & j) != 0) {
          count++;
        }
      }//该位上出现1的次数mod3后为1，表示出现一次的数字该位为1
      if (count % 3 == 1) {
        result |= index;
      }
    }
    return result;
  }

  //输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
  public int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      int diff = target - nums[i];
      int start =  i + 1;
      int end = nums.length  - 1;
      while (start <= end){
        int mid = (start + end)  / 2;
        if (nums[mid] == diff) {
          return new int[]{nums[i], nums[mid]};
        }else if (nums[mid] > diff) {
          end = mid - 1;
        }else {
          start = mid + 1;
        }
      }
    }
    return new int[0];
  }

  public int[] twoSumOpt(int[] nums, int target) {
    int start = 0;
    int end = nums.length - 1;
    while (start <= end){
      int add = nums[start] + nums[end];
      if (add == target) {
        return new int[]{nums[start], nums[end]};
      }else if (add > target) {
        end--;
      }else {
        start++;
      }
    }
    return new int[0];
  }

  //输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
  //序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
  public int[][] findContinuousSequence(int target) {
    int[][] result = new int[target][];
    int startIndex = 0;
    int endIndex = 1;
    int add = startIndex + endIndex + 2;
    int size = 0;
    while (startIndex < endIndex && endIndex < target){
      if (add == target) {
        int[] item = new int[endIndex - startIndex + 1];
        for (int i = 0; i < item.length; i++) {
          item[i] = startIndex + i + 1;
        }
        result[size++] = item;
        endIndex++;
        add += endIndex + 1;
      }else if(add < target){
        endIndex++;
        add += endIndex + 1;
      }else {
        startIndex++;
        add -= startIndex;
      }
    }
    int[][] s = new int[size][];
    System.arraycopy(result, 0, s, 0, size);
    return s;
  }

  //输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
  //例如输入字符串"I am a student. "，则输出"student. a am I"。
  //"  hello world!  "
  public String reverseWords(String s) {
    if (s == null || s.length() == 0) {
      return "";
    }
    int size = 0;
    int length = s.length();
    char[] reverse = new char[length];
    int end = 0;
    for (int i = length - 1; i >= 0; i--) {
      if (s.charAt(i) != ' ') {
        end = i;
        break;
      }
    }

    for (int i = 0; i <= end; i++) {
      if (s.charAt(i) == ' ') {
        if (size == 0 || reverse[size - 1] == ' ') {
          continue;
        }
      }
      reverse[size++] = s.charAt(i);
    }
    char[] reversed = new char[size];
    int lastIndex = size;
    int start = 0;
    end = 0;
    for (; end < size; end++) {
      if (reverse[end] == ' '){
        System.arraycopy(reverse, start, reversed, lastIndex - (end - start), end - start);
        lastIndex -= (end - start);
        reversed[--lastIndex] = ' ';
        start = end + 1;
        continue;
      }
      if (end == size - 1){
        System.arraycopy(reverse, start, reversed, lastIndex - (end - start + 1), end - start + 1);
      }
    }
    return new String(reversed);
  }

  //字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
  //比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
  public String reverseLeftWords(String s, int n) {
    if (s == null || s.length() <= 1) {
      return s;
    }
    char[] chars = s.toCharArray();
    char[] reverse = new char[chars.length];
    System.arraycopy(chars, n, reverse, 0, chars.length - n);
    System.arraycopy(chars, 0, reverse, chars.length - n, n);
    return new String(reverse);
  }

  //给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums.length == 1) {
      return new int[]{nums[0]};
    }
    int[] max = new int[nums.length - k + 1];
    int size = 0;
    for (int i = 0; i <= nums.length - k; i++) {
      max[size] = nums[i];
      for (int j = 1; j < k; j++) {
        if (max[size] < nums[i + j]){
          max[size] = nums[i + j];
        }
      }
      size++;
    }
    return max;
  }

  public int[] maxSlidingWindowOpt(int[] nums, int k) {
    if (nums == null || k < 1 || nums.length < k) {
      return new int[0];
    }

    int index = 0;
    int[] res = new int[nums.length - k + 1];
    LinkedList<Integer> qMax = new LinkedList<>();

    for (int i = 0; i < nums.length; i++) {
      // 在队列不为空的情况下，如果队列尾部的元素要比当前的元素小，或等于当前的元素
      // 那么为了维持从大到小的原则，我必须让尾部元素弹出
      while (!qMax.isEmpty() && nums[qMax.peekLast()] <= nums[i]) {
        qMax.pollLast();
      }
      // 不走 while 的话，说明我们正常在队列尾部添加元素
      qMax.addLast(i);
      // 如果滑动窗口已经略过了队列中头部的元素，则将头部元素弹出
      if (qMax.peekFirst() == (i - k)) {
        qMax.pollFirst();
      }
      // 看看窗口有没有形成，只有形成了大小为 k 的窗口，我才能收集窗口内的最大值
      if (i >= (k - 1)) {
        res[index++] = nums[qMax.peekFirst()];
      }
    }
    return res;
  }

  //把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率
  //你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
  public double[] twoSum(int n) {
    int[][] dp = new int[n+1][6*n+1];
    //边界条件
    for(int s=1;s<=6;s++) {
      dp[1][s]=1;
    }
    for(int i=2;i<=n;i++){
      for(int s=i;s<=6*i;s++){
        //求dp[i][s]
        for(int d=1;d<=6;d++){
          if(s-d<i-1) {
            break;//为0了
          }
          dp[i][s]+=dp[i-1][s-d];
        }
      }
    }
    double total = Math.pow((double)6,(double)n);
    double[] ans = new double[5*n+1];
    for(int i=n;i<=6*n;i++){
      ans[i-n]=((double)dp[n][i])/total;
    }
    return ans;
  }

  //从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
  //2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
  public boolean isStraight(int[] nums) {
    int startValue = 14;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) {
        continue;
      }
      if (nums[i] < startValue) {
        startValue = nums[i];
      }
    }
    for (int i = 1; i < 5; i++) {
      int zeroIndex = -1;
      int j = 0;
      for (; j < nums.length; j++) {
        if (nums[j] == (startValue + i)) {
          nums[j] = -1;
          zeroIndex = -1;
          break;
        }
        if (nums[j] == 0){
          zeroIndex = j;
        }
      }
      if (zeroIndex >= 0) {
        nums[zeroIndex] = -1;
        continue;
      }
      if (j < nums.length) {
        continue;
      }
      return false;
    }
    return true;
  }

  //0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
  //例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
  public int lastRemaining(int n, int m) {
    Node cousor = buildNodes(n);
    int index = 1;
    if (m == 1) {
      return n -1;
    }
    while (n > 1){
      if ((index + 1) % m == 0) {
        cousor.next = cousor.next.next;
        cousor = cousor.next;
        index = 1;
        n--;
        continue;
      }
      cousor = cousor.next;
      index++;
    }
    return cousor.val;
  }

  private Node buildNodes(int n){
    Node head = new Node(0);
    Node cousor = head;
    for (int i = 1; i < n; i++) {
      cousor.next = new Node(i);
      cousor = cousor.next;
    }
    cousor.next = head;
    return head;
  }

  public int lastRemainingOpt(int n, int m) {
    int ans = 0;
    // 最后一轮剩下2个人，所以从2开始反推
    for (int i = 2; i <= n; i++) {
      ans = (ans + m) % i;
    }
    return ans;
  }

  //写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
  public int add(int a, int b) {
    int bit = 1;
    int add = 0;
    boolean lastCarryBit = false;
    while (bit <= 32){
      int bitA = a & bit;
      int bitB = b & bit;
      if (bitA != 0 && bitB != 0) {
        if (lastCarryBit) {
          add |= bit;
        }
        lastCarryBit = true;
        bit<<=1;
        continue;
      }
      if (bitA != 0 || bitB != 0) {
        if (!lastCarryBit) {
          add |= bit;
        }else {
          lastCarryBit = true;
        }
        bit<<=1;
        continue;
      }else {
        if (lastCarryBit) {
          lastCarryBit = false;
          add |= bit;
        }
        bit<<=1;
      }
    }
    return add;
  }

  public int addAnswer(int a, int b) {
    while (b != 0) {
      int plus = (a ^ b); // 求和（不计进位）. 相同位置0，相反位置1
      b = ((a & b) << 1); // 计算进位. 先保留同为1的位，都为1的位要向左进位，因此左移1位
      a = plus;
    }
    return a;
  }

  //给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
  public int[] constructArr(int[] a) {
    if(a==null||a.length==0)return new int[]{};
    if(a.length==1){//只有一个元素返回[1]
      int[] B={1};
      return B;
    }
    if(a.length==2){//有两个元素返回对调值
      int[] B={a[1],a[0]};
      return B;
    }
    int[] res=new int[a.length];//结果数组
    int[] dp=new int[a.length];//前dp数组
    int[] dph=new int[a.length];//后dp数组
    dp[0]=a[0];//初始化
    dph[a.length-1]=a[a.length-1];//初始化
    for(int i=a.length-2;i>=0;i--){//循环赋值
      dph[i]=dph[i+1]*a[i];
    }
    for(int i=1;i<a.length;i++){//循环赋值
      dp[i]=dp[i-1]*a[i];
    }
    res[0]=dph[1];
    res[a.length-1]=dp[a.length-2];
    for(int i=1;i<a.length-1;i++){//计算
      res[i]=dp[i-1]*dph[i+1];
    }
    return res;
  }

  //求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
  public int sumNums(int n) {
    int sum = n;
    boolean b = (n > 0) && ((sum += sumNums(n - 1)) > 0);
    return sum;
  }


  //给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
  //百度百科中最近公共祖先的定义为：
  //“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
  public TreeNode lowestSortCommonAncestorOpt(TreeNode root, TreeNode p, TreeNode q) {
    while (root!=null){
      if (p.val<root.val && q.val<root.val){
        root=root.left;
      }else if (p.val>root.val && q.val>root.val){
        root=root.right;
      }else{
        break;
      }
    }
    return root;
  }

  public TreeNode lowestSortCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    Stack<TreeNode> path = new Stack<>();
    findSortNode(root, p, path);
    while (!path.isEmpty()){
      TreeNode curRoot = path.pop();
      if (findSortNode(curRoot, q, null) != null) {
        return curRoot;
      }
    }
    return null;
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root.val == p.val || root.val == q.val) {
      return root;
    }
    TreeNode p1 = findNode(root.left, p);
    TreeNode q1 = findNode(root.left, q);
    if (p1 != null && q1 != null) {
      if (findNode(p1, q) != null) {
        return p1;
      }
      if (findNode(p1, q) != null) {
        return p1;
      }
      return lowestSortCommonAncestor(root.left, p, q);
    }
    if (p1 == null && q1 == null) {
      p1 = findNode(root.right, p);
      if (findNode(p1, q) != null) {
        return p1;
      }
      q1 = findNode(root.right, q);
      if (findNode(p1, q) != null) {
        return p1;
      }
      return lowestSortCommonAncestor(root.right, p, q);
    }
    return root;
  }

  public TreeNode findSortNode(TreeNode root, TreeNode target, Stack<TreeNode> path){
    if (path != null) {
      path.push(root);
    }
    if (root == null || target.val == root.val) {
      return root;
    }
    if (target.val > root.val) {
      return findSortNode(root.right, target, path);
    }
    return findSortNode(root.left, target, path);
  }

  public TreeNode findNode(TreeNode root, TreeNode target){
    if (root == null || target.val == root.val) {
      return root;
    }
    TreeNode node = findNode(root.right, target);
    if (node != null) {
      return node;
    }
    return findNode(root.left, target);
  }

  //假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
  //dp[i] = dp[i-1], prices[i] - min(prices[0：i])
  public int maxProfit(int[] prices) {
    int[] dp = new int[prices.length];
    int min = prices[0];
    for (int i = 1; i < prices.length; i++) {
      dp[i] = Math.max(dp[i - 1], prices[i] - min);
      if (min > prices[i]) {
        min = prices[i];
      }
    }
    return dp[prices.length -1];
  }

  //超出时间限制
  public int reversePairs(int[] nums) {
    if (nums.length==0) {
      return 0;
    }
    int left = 0, right = nums.length-1;
    int[] copy = Arrays.copyOf(nums, nums.length); //辅助空间
    return reversePairs(nums, copy, left, right); // 入口nums和copy是一致的，两个数组来回倒腾

  }
  int reversePairs(int[] nums, int[] copy, int left, int right) {
    if (left>= right) { //空或者单元素
      return 0;
    }
    int mid = left+(right-left)/2;
    int lpairs = reversePairs(copy, nums, left, mid); //排序nums
    int rpairs = reversePairs(copy, nums, mid+1, right);
    int li = mid,ri = right,ci=right;
    int count = 0;
    while (li>=left && ri>mid) {
      // 拷贝两边的较大值，仅当左边大于右边时计算两边之间的逆序对
      if (nums[li]>nums[ri]) {
        count += ri-mid;  //当前左边li可以构成 ri-mid个逆序对 比ri前面的都大
        copy[ci--] = nums[li--];
      } else {
        copy[ci--] = nums[ri--];
      }
    }
    while (li>=left) { //剩余小值都在左边，无需增加对数
      copy[ci--] = nums[li--];
    }
    while (ri>mid) { //说明左边所有值都比当前右边的大，每个右边的小值都有mid-left+1对,
      copy[ci--] = nums[ri--];
      //            count += mid-left+1; //已经在left比较时加过了
    }
    return lpairs+rpairs+count;
  }
}
