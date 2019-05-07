package com.just.talk.algorithm.imp;

/**
 * Created by Liu On 2019/5/6
 * Description:
 * email: mingming.liu@quvideo.com
 */
public class BackPack {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // write your code here
        if (m == 0 || A == null || A.length == 0) {
            return 0;
        }
        return m - minUnUsed(m, A, 0);
    }

    public int minUnUsed(int m, int[] A, int i){
        if (m == 0 || i == A.length) {
            return m;
        }
        int unUse = minUnUsed(m, A, i+1);
        if (m < A[i]) {
            return unUse;
        }else{
            int use = minUnUsed(m - A[i], A, i+1);
            return  use < unUse ? use:unUse;
        }
    }

    /**
     * 事件复杂度 m * n
     * 空间复杂度 m
     */
    public int dynic(int m, int[] A){
        boolean[] store = new boolean[m+1];
        boolean[] tmp = new boolean[m+1];
        store[0] = true;
        if (m >= A[0]) {
            store[A[0]] = true;
        }
        for(int i = 1; i < A.length;i++){
            for(int j = 0; j <= m; j++){
                if (store[j]) {
                    int a = j + A[i];
                    if (a <= m) {
                        tmp[j+A[i]] = true;
                    }
                }
            }
            for(int j = 0; j <= m; j++){
                if (tmp[j]) {
                    store[j] = true;
                    tmp[j] = false;
                }
            }
        }
        for(int k = m; k>=0;k--){
            if (store[k]) {
                return k;
            }
        }
        return 0;
    }

    /**
     * 事件复杂度 m * n
     * 空间复杂度 m
     */
    public int dynicOpt(int m, int[] A){
        int[] store = new int[m+1];
        store[0] = 1;
        if (m >= A[0]) {
            store[A[0]] = 1;
        }

        for(int i = 1; i< A.length; i++){
            for(int j = 0; j<=m; j++){
                if (store[j] == 1) {
                    int a = j + A[i];
                    if (a <= m) {
                        if (store[a] == 0) {
                            store[a] = -1;
                        }
                    }
                }
            }
            for(int j = 0; j <= m; j++){
                if (store[j] == -1) {
                    store[j] = 1;
                }
            }
        }
        for(int k = m; k>=0;k--){
            if (store[k] == 1) {
                return k;
            }
        }
        return 0;
    }

    /**
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */
    public int uniquePaths(int m, int n) {
        // write your code here
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] table = new int[n][m];
        for (int i = 0; i < m; i++){
            table[0][i] = 1;
        }
        for (int i = 0; i < n; i++){
            table[i][0] = 1;
        }

        for(int i = 1; i < m; i++){
            for(int j =1; j< n; j++){
                table[j][i] = table[j - 1][i] + table[j][i-1];
            }
        }
        return table[n-1][m-1];
    }

    /*
     * @param A: A list of integers
     * @param elem: An integer
     * @return: The new length after remove
     */
    public int removeElement(int[] A, int elem) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        int size = -1;
        for (int i = 0;i < A.length ; i++){
            if (A[i] != elem) {
                A[++size] = A[i];
            }
        }
        return ++size;
    }
}
