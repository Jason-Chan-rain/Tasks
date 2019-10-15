package com.deepward.cardgames.controller;

import com.deepward.cardgames.entity.Card;
import com.deepward.cardgames.entity.Player;
import com.deepward.cardgames.entity.Wrapper;
import com.deepward.cardgames.service.Dealer;
import com.deepward.cardgames.utils.CardsComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.LinkedList;

@RestController
public class CardsController {

    @Autowired
    Dealer dealer;

    @Autowired
    CardsComparator cardsComparator;

    @GetMapping("shuffle")
    public void shuffle() {
        dealer.shuffle();
    }

    @GetMapping("getSingleCard")
    public Card getSingleCard(){
        return dealer.getcard();
    }

    @GetMapping("getRestCardNum")
    public int getRestCardNum(){
        return dealer.getRest_num();
    }

   @PostMapping("getWinner")
    public Wrapper getWinner(@RequestBody Player[] players) {
        Wrapper wrapper = new Wrapper();
        Player player = cardsComparator.getWinner(players[0], players[1]);
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        if (player == null) {
            map.put("winner", "both");
        } else {
            map.put("winner", player.getId());
        }
        map.put(players[0].getId(), Integer.toString(players[0].getType()));
        map.put(players[1].getId(), Integer.toString(players[1].getType()));
        wrapper.setData(map);
        return wrapper;
    }

    @GetMapping("getFormat")
    public Player getFormat() {
        Player player = new Player();
        Card[] cards = new Card[3];
        cards[0] = new Card(Card.FLOWER_DIAMOND,Card.NUM_2);
        cards[1] = new Card(Card.FLOWER_DIAMOND,Card.NUM_3);
        cards[2] = new Card(Card.FLOWER_DIAMOND,Card.NUM_4);
        player.setCards(cards);
        return player;
    }
}
