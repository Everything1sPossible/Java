package com.sjh.algorithm;

public abstract class Example {
    public abstract void sorted(Comparable[] a);
    //比较v是否比w小
    protected final boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    //调换位置
    protected final void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    //打印
    protected final void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    //判断是否进行了排序(从小到大)
    protected final boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
//        Example s = new Selection();
//        Example s = new Insertion();
//        Example s = new Quick();
        Example s = new Quick3way();
        Integer[] a = {15, 45, 89, 3, 0, 15, 100, 33, 56, 201};
        s.sorted(a);
        s.show(a);
    }
}
