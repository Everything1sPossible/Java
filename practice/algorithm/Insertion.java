package com.sjh.algorithm;

/**
 * 插入排序：
 *
 * 在计算机的实现中，为了给要插入的元素腾出空间，我们需要把其余所有元素
 * 在插入之前都向右移动一位。与选择排序一样，当前索引左边的所有元素都是
 * 有序的，但它们的最终位置还不确定，为了给更小的元素腾出空间，它们可能
 * 会被移动。但是当索引到达数组的右端时，数组顺序就完成了。
 */
public class Insertion extends Example {
    @Override
    public void sorted(Comparable[] a) {
        int N = a.length;
        //外层循环移动数组下标
        for (int i = 1; i < N; i++) {
            //内层循环开始循环比较,比前一个值小的话就替换
            for (int j = i; j > 0 && less(a[j], a[j - 1]) ; j--) {
                exch(a, j, j - 1);
            }
        }
    }
}
