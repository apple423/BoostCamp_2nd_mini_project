package com.example.han.boostcamp2;

import java.util.ArrayList;

/**
 * Created by Han on 2017-07-12.
 */
/*
가게정보를 담고 있는 클래스
가게이름, 가게이미지, 가게내용, 체크박스 체크유무, 거리, 인기, 최근의 정보를 가지고 있습니다.*/

public class Shop {

    private String shopName;
    private int shopImageID;
    private String shopArticle;
    private boolean isChecked;
    private int distance,popularity,recent;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getRecent() {
        return recent;
    }

    public void setRecent(int recent) {
        this.recent = recent;
    }


    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getShopName() {
        return shopName;
    }

    public int getShopImageID() {
        return shopImageID;
    }

    public String getShopArticle() {
        return shopArticle;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;

    }

    public void setShopImageID(int shopImageID) {
        this.shopImageID = shopImageID;
    }

    public void setShopArticle(String shopContent) {
        this.shopArticle = shopContent;
    }

    public Shop(String shopName, int shopImageID, String shopContent,
                boolean isChecked, int distance, int popularity, int recent) {

        this.shopName = shopName;
        this.shopImageID = shopImageID;
        this.shopArticle = shopContent;
        this.isChecked = isChecked;
        this.distance = distance;
        this.popularity = popularity;
        this.recent = recent;

    }


}
