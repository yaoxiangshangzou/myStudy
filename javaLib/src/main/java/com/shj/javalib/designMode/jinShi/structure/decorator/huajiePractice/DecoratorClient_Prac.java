package com.shj.javalib.designMode.jinShi.structure.decorator.huajiePractice;

import com.shj.javalib.designMode.jinShi.create.fatory.abstractFactory.AbstractFactory;
import com.shj.javalib.designMode.jinShi.create.fatory.abstractFactory.AppleFactory;
import com.shj.javalib.designMode.jinShi.entity.Bag;
import com.shj.javalib.designMode.jinShi.entity.Fruit;

/**
 * @author SHJ
 * @date 2021/1/28
 * email：1214495199@qq.com
 * Description:
 */
public class DecoratorClient_Prac {
    public static void main(String[] args) {
        AbstractFactory factory=new AppleFactory();

        Fruit fruit=factory.getFruit();
        fruit.draw();

        Bag bag = factory.getBag();
        bag=new CheckedBagDecorator_Prac(bag);
        bag=new ReinforceBagDecorator_Prac(bag);

        bag.pack();
    }
}
