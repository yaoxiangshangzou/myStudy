package com.shj.javalib.designMode.jinShi.create.fatory.fatoryMethod;

import com.shj.javalib.designMode.jinShi.entity.Bag;
import com.shj.javalib.designMode.jinShi.entity.bag.AppleBag;

/**
 * 工厂方法模式
 */
public class AppleBagFactory implements BagFactory{
    public Bag getBag(){
        return new AppleBag();
    }
}
