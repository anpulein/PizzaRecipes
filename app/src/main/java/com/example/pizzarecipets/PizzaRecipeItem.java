package com.example.pizzarecipets;

public class PizzaRecipeItem {
    private final int imageResource;
    private final String title;
    private final String description;
    private final String text;

    public PizzaRecipeItem(int imageResource, String title, String description, String text) {
        this.imageResource = imageResource;
        this.title = title;
        this.description = description;
        this.text = text;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getText() {
        return text;
    }
}
