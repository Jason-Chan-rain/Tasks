package com.software.t5;

public class Card {
    int color;//图片id
    String size;//面值

    public Card(int color, String size) {
        this.color = color;
        this.size = size;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    //用于调式，返回card字符属性
    @Override
    public String toString() {
        return this.color + this.size;
    }
}
