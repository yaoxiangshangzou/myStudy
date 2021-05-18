package com.shj.javalib.designMode.jinShi.create.fatory.abstractFactory;

import com.shj.javalib.designMode.jinShi.entity.Bag;
import com.shj.javalib.designMode.jinShi.entity.Fruit;
import com.shj.javalib.designMode.jinShi.entity.bag.OrangeBag;
import com.shj.javalib.designMode.jinShi.entity.fruit.Orange;

/**
 * 水果工厂
 */
public class OrangeFactory extends AbstractFactory{

    @Override
    public Fruit getFruit() {
        return new Orange("Peter",50);
    }

    @Override
    public Bag getBag() {
        return new OrangeBag();
    }
}
