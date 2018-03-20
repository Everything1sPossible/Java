package com.sjh.thinkinginjava.innerclasses;

public class MNA {
    private void f() {
        System.out.println("MNA.f()..");
    }
    class A {
        private void g() {
            System.out.println("A.g()..");
        }
        class B {
            void h() {
                g();
                f();
                System.out.println("B.h()..");
            }
        }
    }
    public static void main(String[] args) {
        MNA mna = new MNA();
        MNA.A mnaa = mna.new A();
        MNA.A.B mnaab = mnaa.new B();
        mnaab.h();
    }
}
