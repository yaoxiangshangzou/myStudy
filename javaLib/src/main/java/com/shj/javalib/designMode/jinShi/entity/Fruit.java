package com.shj.javalib.designMode.jinShi.entity;

import com.shj.javalib.designMode.jinShi.action.visit.Visit;

/**
 * 水果接口
 */
public interface Fruit {

    int price();

    void draw();

    int accept(Visit visit);

}
