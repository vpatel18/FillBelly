package com.developer.nikhil.fillbelly.model;

import java.io.Serializable;


public class DrawerBean implements Serializable {

    public String tvCuisine;
    private boolean isSelected;


    public DrawerBean() {
    }

    public DrawerBean(String tvCuisine, boolean isSelected) {
        this.tvCuisine = tvCuisine;
        this.isSelected = isSelected;

    }


    public String getTvCuisine() {
        return tvCuisine;
    }

    public void setTvCuisine(String tvCuisine) {
        this.tvCuisine = tvCuisine;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
