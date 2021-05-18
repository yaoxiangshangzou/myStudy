package com.shj.javalib.designMode.jinShi.action.chain;

import com.shj.javalib.designMode.jinShi.action.template.OtherPayShopping;
import com.shj.javalib.designMode.jinShi.action.template.ShoppingCart;
import com.shj.javalib.designMode.jinShi.create.fatory.simple.StaticFactory;
import com.shj.javalib.designMode.jinShi.entity.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板方法模式
 * 订单费用结算过程
 */
public class ChainCartClient {

    //初始化满减优惠券
    private static MultyDiscount multyDiscount = new FullMultyDiscount(null);
    static {
        multyDiscount = new com.shj.javalib.designMode.jinShi.action.chain.NewerMultyDiscount(multyDiscount);
        multyDiscount = new com.shj.javalib.designMode.jinShi.action.chain.SecondMultyDiscount(multyDiscount);
        multyDiscount = new com.shj.javalib.designMode.jinShi.action.chain.HolidayMultyDiscount(multyDiscount);


    }

    public static void main(String[] args) {
        List<Fruit> products = new ArrayList();

        products.add(StaticFactory.getFruitApple());
        products.add(StaticFactory.getFruitBanana());
        products.add(StaticFactory.getFruitOrange());

        ShoppingCart cart = new OtherPayShopping(products);

        //注入优惠方案
        cart.setDiscount(multyDiscount);

        cart.submitOrder();
    }


}
