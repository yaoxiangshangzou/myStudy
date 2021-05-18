package com.shj.javalib.designMode.jinShi.create.fatory.fatoryMethod;

import com.shj.javalib.designMode.jinShi.entity.Fruit;
import com.shj.javalib.designMode.jinShi.entity.fruit.Orange;

/**
 * 工厂方法模式
 */
public class OrangeFactory implements FruitFactory{
    public Fruit getFruit(){
        return new Orange("Peter",80);
    }
}
