/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package model;

import java.util.ArrayList;

/**
 * Created by Olivine on 5/28/2017.
 */

public class ListWrapper {
    ArrayList<AccommodationRoom> accommodationRooms;
    ArrayList<AccommodationProvider> providers;
    ArrayList<Food> foods;

    public ArrayList<AccommodationRoom> getAccommodationRooms() {
        return accommodationRooms;
    }

    public ArrayList<Food> getFoods() {return foods;}

    public ListWrapper setAccommodationRooms(ArrayList<AccommodationRoom> accommodationRooms) {
        this.accommodationRooms = accommodationRooms;
        return this;
    }

    public ListWrapper setFoods(ArrayList<Food> foods) {
        this.foods = foods;
        return this;
    }

    public ArrayList<AccommodationProvider> getProviders() {
        return providers;
    }

    public ListWrapper setProviders(ArrayList<AccommodationProvider> providers) {
        this.providers = providers;
        return this;
    }
}
