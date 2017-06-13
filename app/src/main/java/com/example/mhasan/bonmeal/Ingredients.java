package com.example.mhasan.bonmeal;

/**
 * Created by mhasan on 6/12/2017.
 *
 */

public class Ingredients {
    public String ingIcon;
    public String type;

    public Ingredients(String ingIcon, String type) {
        this.ingIcon = ingIcon;
        this.type = type;
    }

    public Ingredients() {
    }

    public String getIngIcon() {
        return ingIcon;
    }

    public String getType() {
        return type;
    }

    public void setIngIcon(String ingIcon) {
        this.ingIcon = ingIcon;
    }

    public void setType(String type) {
        this.type = type;
    }
}
