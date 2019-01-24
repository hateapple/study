package com.hateapple.myspringboot.pattern.simpleFactory;

public class CarFactory implements CarFactoryInf {

    @Override
    public Car productCar() {
        Car car = new Car();
        return car;
    }
}
