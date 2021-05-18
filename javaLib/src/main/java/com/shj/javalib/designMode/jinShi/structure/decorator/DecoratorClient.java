package com.shj.javalib.designMode.jinShi.structure.decorator;

import com.shj.javalib.designMode.jinShi.create.fatory.abstractFactory.AbstractFactory;
import com.shj.javalib.designMode.jinShi.create.fatory.abstractFactory.AppleFactory;
import com.shj.javalib.designMode.jinShi.entity.Bag;
import com.shj.javalib.designMode.jinShi.entity.Fruit;

/**
 * 装饰器模式测试
 */
public class DecoratorClient {
    public static void main(String[] args){
        sendFruit();
    }

    public static void sendFruit(){
        AbstractFactory factory = new AppleFactory();

        //得到水果
        Fruit fruit = factory.getFruit();
        fruit.draw();
        //得到包装
        Bag bag = factory.getBag();

        //现需要增加防伪标识
        bag = new CheckedBagDecorator(bag);//防伪功能
        bag = new ReinforceBagDecorator(bag);//加固功能
        bag = new SpeedDecorator(bag);//加急功能

        bag.pack();

        //以下物流运输业务。。。。

    }



}
