package com.sjh.practice.other;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Java四种引用
 */
public class ReferenceTest {

    public static void main(String[] args) {
//        softRef();
        weakRef();
    }

    /**
     * 强引用:在程序代码中普遍存在,类似"Object obj = new Object()"这类的引用.
     * 只要某个对象有强引用与之关联，JVM必定不会回收这个对象，即使在内存不足的情况下，JVM宁愿抛出OutOfMemory错误也不会回收这种对象.
     * 如果想中断强引用和某个对象之间的关联，可以显示地将引用赋值为null，这样一来的话，JVM在合适的时间就会回收该对象。
     */

    /**
     * 软引用:用来描述一些还有用但并非必须的对象.
     * 当JVM进行垃圾回收时，只有当内存不足时,才会回收这些对象.
     * 可用于实现缓存:比如网页图片缓存等.
     */
    public static void softRef() {
        SoftReference sref = new SoftReference(new String("hello"));
        System.out.println(sref.get()); //hello
        System.gc(); //GC
        System.out.println(sref.get()); //hello
    }

    /**
     * 弱引用:用来描述非必须对象,强度比软引用更弱一些.
     * 当JVM进行垃圾回收时，无论内存是否充足，都会回收被弱引用关联的对象.
         * 弱引用能用来在回调函数中防止内存泄露。因为回调函数往往是匿名内部类，隐式保存有对外部类的引用，
         * 所以如果回调函数是在另一个线程里面被回调，而这时如果需要回收外部类，那么就会内存泄露，
         * 因为匿名内部类保存有对外部类的强引用。
     */
    public static void weakRef() {
        WeakReference wref = new WeakReference(new String("hello"));
        System.out.println(wref.get()); //hello
        System.gc(); //GC
        System.out.println(wref.get()); //null
    }
    /**
     * 虚引用:最弱的一种引用关系,不影响对象的生命周期.
     * 为一个对象设置虚引用关联的唯一目的就是能在这个对象被收集器回收时收到一个系统通知.
     */
}
