package com.example.mhasan.bonmeal;

import java.util.ArrayList;

/**
 * Created by mhasan on 6/12/2017.
 * Recipe
 */

public class Recipe {
    public String adImage ;
    public String adUrl;
    public  String howToPrepare;
    public String image;
    public ArrayList<Ingredients> ingredients= new ArrayList<Ingredients>();

    public Recipe(String adImage, String adUrl, String howToPrepare, String image, ArrayList<Ingredients> ingredients) {
        this.adImage = adImage;
        this.adUrl = adUrl;
        this.howToPrepare = howToPrepare;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getAdImage() {
        return adImage;
    }


    public String getAdUrl() {
        return adUrl;
    }


    public String getHowToPrepare() {
        return howToPrepare;
    }



    public String getImage() {
        return image;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

}
