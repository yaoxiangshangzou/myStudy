package com.shj.javalib.designMode.jinShi.structure.decorator.huajiePractice;

import com.shj.javalib.designMode.jinShi.entity.Bag;

/**
 * @author SHJ
 * @date 2021/1/28
 * email：1214495199@qq.com
 * Description:
 */
public class CheckedBagDecorator_Prac extends BagDecorator_Prac{
    public CheckedBagDecorator_Prac(Bag bag) {
        super(bag);
    }

    @Override
    public void pack() {
        super.pack();
        checked();
    }

    //添加到操作
    private void checked() {
        System.out.println("------");
        System.out.println("打印上防伪标识");
    }
}
