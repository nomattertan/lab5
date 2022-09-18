package com.example.lab5;


import java.io.Serializable;
import java.util.ArrayList;

public class Word implements Serializable {

    public ArrayList<String> badwords = new ArrayList<String>();
    public ArrayList<String> goodswords = new ArrayList<String>();


    public ArrayList<String> getBadwords() {
        return badwords;
    }

    public void setBadwords(ArrayList<String> badwords) {
        this.badwords = badwords;
    }

    public ArrayList<String> getGoodswords() {
        return goodswords;
    }

    public void setGoodswords(ArrayList<String> goodswords) {
        this.goodswords = goodswords;
    }
}
