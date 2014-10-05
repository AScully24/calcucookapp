package com.example.calcucookapp;


import java.util.Comparator;


public class FoodTimeComparator implements Comparator<Food>
{
    @Override
    public int compare(Food p1, Food p2)
    {
        return p2.getTime() - p1.getTime();
    }
}
