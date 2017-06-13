package com.example.mhasan.bonmeal;

/**
 * Created by mhasan on 6/12/2017.
 *
 */

public class Ingredients {
    public String ingIcon;
    public int type;

    public Ingredients(String ingIcon, int type) {
        this.ingIcon = ingIcon;
        this.type = type;
    }

    public Ingredients() {
    }

    public String getIngIcon() {
        return ingIcon;
    }

    public int getType() {
        return type;
    }
}
