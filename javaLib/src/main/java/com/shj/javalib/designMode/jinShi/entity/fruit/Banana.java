package com.shj.javalib.designMode.jinShi.entity.fruit;

import com.shj.javalib.designMode.jinShi.action.visit.Visit;
import com.shj.javalib.designMode.jinShi.entity.Fruit;
import com.shj.javalib.designMode.jinShi.entity.bag.BananaBag;

/**
 * 桔子
 * Created by Peter on 10/9 009.
 */
public class Banana implements Fruit {
    private int price = 60;
    @Override
    public int price() {
        return price;
    }

    public void pack(BananaBag bag){
        bag.pack();
    }
    @Override
    public void draw() {
        System.out.print("仙人蕉");
    }

    public int accept(Visit visit){
        return visit.sell(this);
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
