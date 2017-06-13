package com.example.mhasan.bonmeal;

import java.util.ArrayList;

/**
 * Created by mhasan on 6/12/2017.
 * Recipe
 */

public class Recipe {
    public String adImage;
    public String adUrl;
    public String howToPrepare;
    public String image;
    public String  ingredientCount;
    public ArrayList<Ingredients> ingredients = new ArrayList<Ingredients>();

    public Recipe() {

    }

    public Recipe(String adImage, String adUrl, String howToPrepare, String image,String ingredientCount, ArrayList<Ingredients> ingredients) {
        this.adImage = adImage;
        this.adUrl = adUrl;
        this.howToPrepare = howToPrepare;
        this.image = image;
        this.ingredients = ingredients;
        this.ingredientCount=ingredientCount;
    }

    public String getAdImage() {
        return adImage;
    }

    public String getIngredientCount() {
        return ingredientCount;
    }

    public void setIngredientCount(String ingredientCount) {
        this.ingredientCount = ingredientCount;
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

    public void setAdImage(String adImage) {
        this.adImage = adImage;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public void setHowToPrepare(String howToPrepare) {
        this.howToPrepare = howToPrepare;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

}
