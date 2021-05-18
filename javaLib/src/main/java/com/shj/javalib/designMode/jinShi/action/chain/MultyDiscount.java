package com.shj.javalib.designMode.jinShi.action.chain;

import com.shj.javalib.designMode.jinShi.action.strategy.Discount;

/**
 * Created by Peter on 10/29 029.
 */
public abstract class MultyDiscount implements Discount{
    protected MultyDiscount nextMultyDiscount;

    public MultyDiscount(MultyDiscount nextMultyDiscount){
        this.nextMultyDiscount = nextMultyDiscount;
    }

    public int calculate(int money){
        if (this.nextMultyDiscount != null){
            return this.nextMultyDiscount.calculate(money);
        }
        return money;
    }

}
