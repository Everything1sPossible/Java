package com.sjh.thinkinginjava.generic;

public class Manipulator<T>{
    private T obj;

    public Manipulator(T obj) {
        this.obj = obj;
    }
    public void manipulator() {
//        obj.f();
    }
    public static<T> Manipulator<T> instance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return new Manipulator<T>(clazz.newInstance());
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
//        Manipulator<HasF> manipulator = new Manipulator<HasF>(new HasF());
        Manipulator<HasF> manipulator = Manipulator.instance(HasF.class);
        manipulator.obj.f();
    }
}
