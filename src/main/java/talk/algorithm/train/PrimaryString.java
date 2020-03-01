package talk.algorithm.train;

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
}
