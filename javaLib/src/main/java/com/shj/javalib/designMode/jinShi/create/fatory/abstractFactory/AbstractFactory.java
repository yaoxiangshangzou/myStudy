package com.shj.javalib.designMode.jinShi.create.fatory.abstractFactory;

import com.shj.javalib.designMode.jinShi.entity.Bag;
import com.shj.javalib.designMode.jinShi.entity.Fruit;

/**
 * 抽象水果工厂
 */
public abstract class AbstractFactory {

    public abstract  Fruit getFruit();

    public abstract Bag getBag();

}
