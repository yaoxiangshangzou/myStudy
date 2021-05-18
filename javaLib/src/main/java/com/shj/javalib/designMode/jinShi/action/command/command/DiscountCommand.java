package com.shj.javalib.designMode.jinShi.action.command.command;

import com.shj.javalib.designMode.jinShi.action.command.Command;
import com.shj.javalib.designMode.jinShi.action.command.handler.DiscountHandler;

public class DiscountCommand extends Command {

    private DiscountHandler handler = new DiscountHandler();

    @Override
    public String execute() {
        return handler.getDiscounts();
    }
}
