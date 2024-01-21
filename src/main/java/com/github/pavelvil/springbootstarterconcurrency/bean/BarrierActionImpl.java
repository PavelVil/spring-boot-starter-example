package com.github.pavelvil.springbootstarterconcurrency.bean;

public class BarrierActionImpl implements BarrierAction {

    @Override
    public void action() {
        System.out.println("Барьер сработал");
    }

}
