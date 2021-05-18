package com.shj.javalib.designMode.jinShi.create.fatory.fatoryMethod;

import com.shj.javalib.designMode.jinShi.entity.Bag;
import com.shj.javalib.designMode.jinShi.entity.bag.BananaBag;

/**
 * 工厂方法模式
 */
public class BananaBagFactory implements BagFactory{
    public Bag getBag(){
        return new BananaBag();
    }
}
