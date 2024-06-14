package com.project.pantry.entities;

public class MenusObject {
    private int id;
    private String menu_name;
    private String food_category;
    private String food_type;
    private String menu_image;

    public MenusObject(int id, String menu_name, String food_category, String food_type, String menu_image) {
        this.id = id;
        this.menu_name = menu_name;
        this.food_category = food_category;
        this.food_type = food_type;
        this.menu_image = menu_image;
    }

    public MenusObject(String menu_name, String food_category, String food_type, String menu_image) {
        this.menu_name = menu_name;
        this.food_category = food_category;
        this.food_type = food_type;
        this.menu_image = menu_image;
    }

    public int getId() {
        return id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public String getFood_category() {
        return food_category;
    }

    public String getFood_type() {
        return food_type;
    }

    public String getMenu_image() {
        return menu_image;
    }
}
