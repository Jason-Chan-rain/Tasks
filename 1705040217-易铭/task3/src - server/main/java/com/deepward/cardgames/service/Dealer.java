package com.deepward.cardgames.service;

import com.deepward.cardgames.entity.Card;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Random;

@Service
public class Dealer {

    private static final int TOTAL_NUM = 52;

    private int rest_num;

    private LinkedList<Card> cards;

    public Dealer() {
        this.shuffle();
    }

    public int getRest_num() {
        return rest_num;
    }

    private void setRest_num() {
        this.rest_num = Dealer.TOTAL_NUM;
    }

    public Card getcard() {
        if (cards.isEmpty()) {
            return null;
        }
        return this.cards.remove(new Random().nextInt(this.rest_num--));
    }

    public void shuffle() {
        setRest_num();
        this.cards = new LinkedList<Card>();
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j<15;j++) {
                this.cards.add(new Card(i,j));
            }
        }
    }
}

