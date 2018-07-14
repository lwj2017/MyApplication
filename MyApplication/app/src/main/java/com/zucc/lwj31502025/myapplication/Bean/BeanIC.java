package com.zucc.lwj31502025.myapplication.Bean;

import java.io.Serializable;

public class BeanIC implements Serializable{
    private int id;
    private String icDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcDate() {
        return icDate;
    }

    public void setIcDate(String icDate) {
        this.icDate = icDate;
    }

    public int getIcType() {
        return icType;
    }

    public void setIcType(int icType) {
        this.icType = icType;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    private int icType;
    private float money;
    private String source;


}


