package com.shj.javalib.designMode.jinShi.structure.bridge;

import com.shj.javalib.designMode.jinShi.structure.bridge.bag.BagAbstraction;
import com.shj.javalib.designMode.jinShi.structure.bridge.bag.SmallBag;
import com.shj.javalib.designMode.jinShi.structure.bridge.material.Material;
import com.shj.javalib.designMode.jinShi.structure.bridge.material.Paper;

/**
 * 园丁采摘水果　　　　　　　　　　　　　　　　　　　　　　 　
 */
public class BridgeClient {

    public static void main(String[] args) {

        //袋子型号
        BagAbstraction bag = new SmallBag();

        //袋子材质
        Material material = new Paper();
        bag.setMaterial(material);

        //开始采摘
        bag.pick();
    }
}
