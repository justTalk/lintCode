package talk.algorithm.imp;

/**
 * Created by Liu On 2019/5/18
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class Sort {

    /**
     * 归并排序
     * 事件复杂度：nlog(n)
     * 空间复杂度 0
     */
    private void sortByMerge(int[] A, int start, int end){
        if (start >= end) {
            return;
        }
        sortByMerge(A, start, (start + end) / 2);
        sortByMerge(A, (start + end) / 2 + 1, end);
        merge(A, start, end);
    }

    /**
     * 利用插入排序来合并
     */
    private void merge(int[] A, int start, int end){
        int index = (start + end) / 2 + 1;
        for (; index <= end; index++) {
            int tmp = A[index];
            int i = index - 1;
            for(; i>=start; i--) {
                if (A[i] > tmp){
                    A[i+1] = A[i];
                }else{
                    break;
                }
            }
            A[i+1] = tmp;
        }
    }


    /**
     * 归并思想统计数组中逆序对的个数
     * @param A: an array
     * @return: total of reverse pairs
     */
    public int reversePairs(int[] A, int start, int end) {
        // write your code here
        if (start >= end) {
            return 0;
        }
        int left = reversePairs(A, start, (start + end) / 2);
        int right = reversePairs(A, (start + end) / 2 + 1, end);
        int other = 0;
        for (int i = start; i <= (start + end) / 2; i++) {
            for (int j = (start + end) / 2 + 1; j <= end; j++) {
                if (A[i] > A[j]) {
                    other++;
                }
            }
        }
        return left + right + other;
    }

}
