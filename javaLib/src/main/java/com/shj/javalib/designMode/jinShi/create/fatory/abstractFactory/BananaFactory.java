package com.shj.javalib.designMode.jinShi.create.fatory.abstractFactory;

import com.shj.javalib.designMode.jinShi.entity.Bag;
import com.shj.javalib.designMode.jinShi.entity.Fruit;
import com.shj.javalib.designMode.jinShi.entity.bag.BananaBag;
import com.shj.javalib.designMode.jinShi.entity.fruit.Banana;

/**
 * 水果工厂
 */
public class BananaFactory extends AbstractFactory{

    @Override
    public Fruit getFruit() {
        return new Banana();
    }

    @Override
    public Bag getBag() {
        return new BananaBag();
    }
}
