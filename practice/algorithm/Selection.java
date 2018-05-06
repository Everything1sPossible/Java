package com.sjh.algorithm;

/**
 * 选择排序（冒泡）:
 *
 * 首先，找到数组中最小的元素，其次，将它和数组的第一个元素交换位置（如果
 * 第一个元素就是最小元素那么它就和自己交换）。再次，在剩下的元素中找到最
 * 小的元素，将它与数组的第二个元素交换位置。如此往复，直到将整个数组排序。
 */
public class Selection extends Example{
    public void sorted(Comparable[] a) {
        //数组长度
        int N = a.length;
        for (int i = 0; i < N; i++) {
            //最小值下标
            int min = i;
            for (int j = i + 1; j < N; j++) {
                //每当内层循环发现比最小值还小的值的时候就替换min值
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            //外层循环每循环一次就把当前循环最小值移到i的位置
            exch(a, i, min);
        }
    }
}
