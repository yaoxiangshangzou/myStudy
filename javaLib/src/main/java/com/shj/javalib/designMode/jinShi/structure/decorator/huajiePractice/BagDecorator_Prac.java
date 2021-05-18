package com.shj.javalib.designMode.jinShi.structure.decorator.huajiePractice;

import com.shj.javalib.designMode.jinShi.entity.Bag;

/**
 * @author SHJ
 * @date 2021/1/28
 * email：1214495199@qq.com
 * Description:
 */
public class BagDecorator_Prac implements Bag {
    private Bag bag;

    public BagDecorator_Prac(Bag bag) {
        this.bag = bag;
    }

    @Override
    public void pack() {

    }
}
