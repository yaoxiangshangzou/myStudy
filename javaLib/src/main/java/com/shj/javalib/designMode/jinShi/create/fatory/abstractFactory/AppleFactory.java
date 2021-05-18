package com.shj.javalib.designMode.jinShi.create.fatory.abstractFactory;

import com.shj.javalib.designMode.jinShi.entity.Bag;
import com.shj.javalib.designMode.jinShi.entity.Fruit;
import com.shj.javalib.designMode.jinShi.entity.bag.AppleBag;
import com.shj.javalib.designMode.jinShi.entity.fruit.Apple;

/**
 * 水果工厂
 */
public class AppleFactory extends AbstractFactory{

    @Override
    public Fruit getFruit() {
        return new Apple();
    }

    @Override
    public Bag getBag() {
        return new AppleBag();
    }
}
