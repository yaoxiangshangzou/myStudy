package com.shj.javalib.designMode.jinShi.structure.decorator.huajiePractice;

import com.shj.javalib.designMode.jinShi.entity.Bag;

/**
 * @author SHJ
 * @date 2021/1/28
 * email：1214495199@qq.com
 * Description:
 */
public class ReinforceBagDecorator_Prac extends BagDecorator_Prac{
    public ReinforceBagDecorator_Prac(Bag bag) {
        super(bag);
    }

    @Override
    public void pack() {
        super.pack();
        reinforce();
    }

    private void reinforce() {
        System.out.println("------");
        System.out.println("加固了包装");
    }
}
