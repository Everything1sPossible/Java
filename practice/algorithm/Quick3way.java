package com.sjh.algorithm;

/**
 * 三向切分快速排序:
 * 将数组切分为三部分：分别对应小于、等于、大于切分元素的数组元素。
 * 从左到右遍历数组一次，维护一个指针lt使得a[lo..lt-1]中的元素都小于v，一个
 * 指针gt使得a[gt+1..hi]中的元素都大于v，一个指针i使得a[lt..i-1]中的元素都
 * 等于v，a[i..gt]中的元素都还未确定。
 * 1.a[i]小于v，将a[lt]和a[i]交换，然后将lt和i都加1；
 * 2.a[i]大于v，将a[gt]和a[i]交换，然后将gt减1；
 * 3.a[i]等于v，将i加1。
 */
public class Quick3way extends Example{
    @Override
    public void sorted(Comparable[] a) {
        sorted(a, 0, a.length - 1);
    }

    public void sorted(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int temp = a[i].compareTo(v);
            if (temp < 0) exch(a, lt++, i++);
            if (temp > 0) exch(a, i, gt--);
            if (temp == 0) i++;
        }
        sorted(a, lo, lt - 1);
        sorted(a, gt + 1, hi);
    }
}
