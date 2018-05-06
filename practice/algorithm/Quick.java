package com.sjh.algorithm;

/**
 * 快速排序
 */
public class Quick extends Example{

    @Override
    public void sorted(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    public void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = portition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    /**
     * 对数组进行切分（快排的核心方法）
     * 策略：先随意的选取a[lo]作为切分元素，然后从数组的左端开始向右扫描直到找到一个大于它
     * 的元素，再从数组的右端开始扫描直到找到一个小于或等于它的元素，这两个元素显然是没有排
     * 定的，因此交换它们的位置，如此继续，就可以保证左指针i的左侧元素都不大于切分元素，右
     * 指针j的右侧元素都不小于切分元素，当两个指针相遇时，只需要将切分元素a[lo]和左子数组最
     * 右侧的元素（a[j]）交换并返回j即可。
     */
    public int portition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;  //左右扫描指针
        Comparable v = a[lo];    //切分元素
        while (true) {
            //扫描左右，检查扫描是否结束并交换元素
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
}
