package com.hateapple.myspringboot.pattern.simpleFactory;

public class SimpleFactory {
    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();
        System.out.println(carFactory.productCar());
    }
}
