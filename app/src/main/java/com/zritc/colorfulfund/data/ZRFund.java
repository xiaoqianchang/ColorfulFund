package com.zritc.colorfulfund.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gufei on 16/8/15.
 */
public class ZRFund implements Serializable {
    private static final long serialVersionUID = -6306771797624555411L;

    private int id;
    private String name;
    private String money;
    private List<ZRFund> datas = new ArrayList<>();

    public List<ZRFund> getDatas() {
        return datas;
    }

    public void setDatas(List<ZRFund> datas) {
        this.datas = datas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ZRFund{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
