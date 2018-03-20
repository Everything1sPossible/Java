package com.sjh.thinkinginjava.innerclasses;

public class Parcel6 {
    private void internalTracking(boolean b) {
        /**
         * 4.2.定义在作用域内的类,此作用域在方法内
         */
        if(b) {
            class TrackingSlip{
                private String id;
                public TrackingSlip(String id) {
                    this.id = id;
                }
                String getSlip() {
                    return id;
                }
            }
            TrackingSlip t = new TrackingSlip("slip");
            String s = t.id;
            System.out.println(s);
        }
    }
    public void track(){internalTracking(true);}
    public static void main(String[] args) {
        Parcel6 p = new Parcel6();
        p.track();
    }
}
