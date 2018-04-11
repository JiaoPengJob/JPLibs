package com.jiaop.libs.entities;

/**
 * 网络状态实体类
 */
public class NetworkChange {

    private int state;

    public NetworkChange() {
    }

    public NetworkChange(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
