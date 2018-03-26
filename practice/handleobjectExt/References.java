package com.sjh.thinkinginjava.handleobjectExt;

import java.lang.ref.*;
import java.util.LinkedList;

public class References {
    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();
    public static void checkQueue(){
        Reference<? extends VeryBig> inq = rq.poll();
        if(inq!=null){
            System.out.println("In queue:"+inq.getClass().getSimpleName());
        }
    }

    public static void main(String[] args) {
        int size = 3;
            /*
             * SoftReference:在内存不足时才会回收这样软引用对象
             * */
        LinkedList<SoftReference<VeryBig>> sa = new  LinkedList<SoftReference<VeryBig>>();
        for(int i=0;i<size;i++){
            sa.add(new SoftReference(new VeryBig("Soft "+i),rq));
            System.out.println("Just created: "+sa.getLast());
            checkQueue();//一直为空
        }
            /*
             * WeakReference:在GC发现只具有弱引用的对象会立即对其会回收
             * */
        LinkedList<WeakReference<VeryBig>> wa = new  LinkedList<WeakReference<VeryBig>>();
        for(int i=0;i<size;i++){
            wa.add(new WeakReference(new VeryBig("Weak "+i),rq));
            System.out.println("Just created: "+wa.getLast());
            checkQueue();
        }

        System.gc();//显示的进行垃圾回收，什么时候执行就由JVM决定

        LinkedList<PhantomReference<VeryBig>> pa = new  LinkedList<PhantomReference<VeryBig>>();
        for(int i=0;i<size;i++){
            pa.add(new PhantomReference(new VeryBig("Phantom "+i),rq));
            System.out.println("Just created: "+pa.getLast());
            checkQueue();
        }
    }
}
