package com.shj.javalib.designMode.jinShi.create.fatory.fatoryMethod;

import com.shj.javalib.designMode.jinShi.entity.fruit.Apple;
import com.shj.javalib.designMode.jinShi.entity.Fruit;

/**
 * 工厂方法模式
 */
public class AppleFactory implements FruitFactory{
    public Fruit getFruit(){
        return new Apple();
    }
}
