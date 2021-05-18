package com.shj.javalib.designMode.jinShi.action.command.command;

import com.shj.javalib.designMode.jinShi.action.command.Command;
import com.shj.javalib.designMode.jinShi.action.command.handler.HotHandler;

public class HotCommand extends Command {
    private HotHandler handler = new HotHandler();

    @Override
    public String execute() {
        return handler.getHots();
    }
}
