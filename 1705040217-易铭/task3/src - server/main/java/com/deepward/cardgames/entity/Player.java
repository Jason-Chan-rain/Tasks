package com.deepward.cardgames.entity;

import java.util.LinkedList;

public class Player {

    public static final int STRAIGHT_FLUSH = 5;
    public static final int FLUSH = 4;
    public static final int STRAIGHT = 3;
    public static final int BOMB = 2;
    public static final int DOUBLE = 1;
    public static final int NORMAL = 0;

    public Card[] cards = new Card[3];

    private int type;

    private int value;

    private String id;

    public Player() {
    }

    public Player(Card card0, Card card1, Card card2) {
        this.cards[0] = card0;
        this.cards[1] = card1;
        this.cards[2] = card2;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
