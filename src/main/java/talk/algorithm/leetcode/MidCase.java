package talk.algorithm.leetcode;


import talk.algorithm.imp.SingleNumber;

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
     * 思路1：
     * 双重循环对比新的字符在子串中是否重复，如果重复，则将字符串的起始位置调整为重复的下一个坐标
     * 思路2：
     * 采用数组来保存字符上一次出现的位置，实现直接查找而不用循环
     * https://github.com/Blankj/awesome-java-leetcode/blob/master/note/003/README.md
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int start = 0;
        int max = 1;
        for (int i = 1; i < s.length(); i++) {
            int j = i - 1;
            int tmpMax = 1;
            while (j >= start){
                if (s.charAt(j) != s.charAt(i)) {
                    tmpMax++;
                }else {
                    start = j + 1;
                    break;
                }
                j--;
            }
            if (tmpMax > max) {
                max = tmpMax;
            }
        }
        return max;
    }

    /**
     * https://leetcode.com/problems/longest-palindromic-substring/
     * 求字符串的最大回文子串长度
     * 思路：
     * 以每一个字符为回文中心，填充#使回文子串长度成为奇数
     * 时间复杂度：n^2
     * 空间复杂度：n
     */
    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        int start = 0;
        int end = 0;
        int max = 0;
        String handleStr = preHandleStr(s);
        for (int i = 0; i < handleStr.length(); i++) {
            int step = 1;
            while (i - step >= 0 && i + step < handleStr.length()){
                if (handleStr.charAt(i - step) == handleStr.charAt(i + step)) {
                    if (step * 2 + 1 > max) {
                        max = step * 2 + 1;
                        start = i - step;
                        end = i + step;
                    }
                    step++;
                }else {
                    if (handleStr.charAt(start) == '#') {
                        start++;
                        end--;
                        max-=2;
                    }

                   break;
                }
            }
        }
        return handleStr.substring(start, end + 1).replaceAll("#","");
    }

    private String preHandleStr(String s){
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder handleStr = new StringBuilder();
        handleStr.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            handleStr.append("#").append(s.charAt(i));
        }
        return handleStr.toString();
    }

    /**
     *
     */
    public String longestPalindromeII(String s){
        String handleStr = preHandleStr(s);
        if (s.length() == 0 || s.length() == 1) {
            return s;
        }
        int rightEdge = 0;
        int rightCenter = 0;
        int[] stepForALl = new int[handleStr.length()];
        for (int i = 0; i < handleStr.length(); i++) {
            boolean needByStep = true;
            int leftCenter = 2 * rightCenter - i;
            int step = 1;
            if (leftCenter > 0) {
                step = stepForALl[leftCenter];
                if (rightEdge > step + i) {
                    stepForALl[i] = step;
                    needByStep = false;
                    continue;
                }
            }
            if (needByStep) {
                step = rightEdge - i + 1 > 1 ? rightEdge - i + 1 : 1;
                while (i - step >= 0 && i + step < handleStr.length()){
                    if (handleStr.charAt(i - step) == handleStr.charAt(i + step)) {
                        step++;
                    }else {
                        break;
                    }
                }
                int tmpEdge = i + step - 1;
                if (stepForALl[rightCenter] < step || (stepForALl[rightCenter] == step && tmpEdge < handleStr.length() && handleStr.charAt(tmpEdge) != '#')) {
                    rightCenter = i;
                    rightEdge = i + step - 1;
                }
                stepForALl[i] = step;
            }
        }

        return handleStr.substring(rightCenter - stepForALl[rightCenter] + 1, rightEdge + 1).replaceAll("#","");
    }

    public String optLongestStr(String s){
        // 先预处理字符串
        String str = preHandleStr(s);
        // 处理后的字串长度
        int len = str.length();
        // 右边界
        int rightSide = 0;
        // 右边界对应的回文串中心
        int rightSideCenter = 0;
        // 保存以每个字符为中心的回文长度一半（向下取整）
        int[] halfLenArr = new int[len];
        // 记录回文中心
        int center = 0;
        // 记录最长回文长度
        int longestHalf = 0;
        for(int i = 0; i < len; i++) {
            // 是否需要中心扩展
            boolean needCalc = true;
            // 如果在右边界的覆盖之内
            if(rightSide > i) {
                // 计算相对rightSideCenter的对称位置
                int leftCenter = 2 * rightSideCenter - i;
                // 根据回文性质得到的结论
                halfLenArr[i] = halfLenArr[leftCenter];
                // 如果超过了右边界，进行调整
                if(i + halfLenArr[i] > rightSide) {
                    halfLenArr[i] = rightSide - i;
                }
                // 如果根据已知条件计算得出的最长回文小于右边界，则不需要扩展了
                if(i + halfLenArr[leftCenter] < rightSide) {
                    // 直接推出结论
                    needCalc = false;
                }
            }
            // 中心扩展
            if(needCalc) {
                while(i - 1 - halfLenArr[i] >= 0 && i + 1 + halfLenArr[i] < len) {
                    if(str.charAt(i + 1 + halfLenArr[i]) == str.charAt(i - 1 - halfLenArr[i])) {
                        halfLenArr[i]++;
                    } else {
                        break;
                    }
                }
                // 更新右边界及中心
                rightSide = i + halfLenArr[i];
                rightSideCenter = i;
                // 记录最长回文串
                if(halfLenArr[i] > longestHalf) {
                    center = i;
                    longestHalf = halfLenArr[i];
                }
            }
        }
        // 去掉之前添加的#
        StringBuffer sb = new StringBuffer();
        for(int i = center - longestHalf + 1; i <= center + longestHalf; i += 2) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    /**
     * n^3
     */
    public String longestPalindromeIII(String s){
        if (s.length() == 0 || s.length() == 1) {
            return s;
        }
        int longestStr = 0;
        int longStart = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                int start = j;
                int end = i;
                while (start <= end){
                    if (s.charAt(start) == s.charAt(end)) {
                        start++;
                        end--;
                    }else {
                        break;
                    }
                }
                if (start > end) {
                    if (i - j + 1 > longestStr) {
                        longestStr = i - j + 1;
                        longStart = j;
                    }
                }
            }
        }
        return s.substring(longStart, longestStr +longStart);
    }

}
