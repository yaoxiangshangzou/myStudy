package com.shj.javalib.designMode.jinShi.action.command.command;

import com.shj.javalib.designMode.jinShi.action.command.Command;
import com.shj.javalib.designMode.jinShi.action.command.handler.NewerHandler;

public class NewerCommand extends Command {
    private NewerHandler handler = new NewerHandler();

    @Override
    public String execute() {
        return handler.getNewers();
    }
}
