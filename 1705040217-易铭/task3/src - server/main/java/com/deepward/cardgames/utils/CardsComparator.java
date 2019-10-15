package com.deepward.cardgames.utils;

import com.deepward.cardgames.entity.Player;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardsComparator {

    public Player getWinner(Player player1, Player player2) {

        regPlayerType(player1);
        regPlayerType(player2);

        int type = Integer.compare(player1.getType(),player2.getType());
        if (type == 0) {
            int value = Integer.compare(player1.getValue(),player2.getValue());
            if (value == 0) {
                return null;
            } else if (value < 0) {
                return player2;
            } else {
                return player1;
            }
        } else if (type < 0) {
            return player2;
        } else {
            return  player1;
        }
    }

    // 判断牌型、计算牌型绝对值大小
    public Player regPlayerType(Player player) {
        if (isFlush(player)) {
            if (isStraight(player)) {// 同花顺
                player.setType(Player.STRAIGHT_FLUSH);
                player.setValue(calculateValue(player));
            } else {// 同花
                player.setType(Player.FLUSH);
                player.setValue(calculateValue(player));
            }
        } else if (isStraight(player)) {// 顺子
            player.setType(Player.STRAIGHT);
            player.setValue(calculateValue(player));
        } else if (isDouble(player)) {
            if (isBmob(player)) {// 炸弹
                player.setType(Player.BOMB);
                player.setValue(calculateValue(player));
            } else {// 对子
                player.setType(Player.DOUBLE);
                player.setValue(calculateValue(player));
            }
        } else {// 普通牌
            player.setType(Player.NORMAL);
            player.setValue(calculateValue(player));
        }
        return player;
    }

    // 是否同花
    private boolean isFlush(Player player) {
        return player.cards[0].getFlower() == player.cards[1].getFlower()
                && player.cards[1].getFlower() == player.cards[2].getFlower();
    }

    // 是否顺子,A32也是同花顺，是最小的同花顺(参考自百度百科)
    // 花色参与比较的时候，黑桃A红桃3黑桃2<方片A黑桃3方片2
    private boolean isStraight(Player player) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(player.cards[0].getNumber());
        list.add(player.cards[1].getNumber());
        list.add(player.cards[2].getNumber());
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        });
        return list.get(0) + 1 == list.get(1) && list.get(1) + 1 == list.get(2);
    }

    // 是否炸弹
    private boolean isBmob(Player player) {
        return player.cards[0].getNumber() == player.cards[1].getNumber()
                && player.cards[1].getNumber() == player.cards[2].getNumber();
    }

    // 是否对子
    private boolean isDouble(Player player) {
        return player.cards[0].getNumber() == player.cards[1].getNumber()
                || player.cards[1].getNumber() == player.cards[2].getNumber();
    }

    private int calculateValue(Player player) {
        return player.cards[0].getNumber() + player.cards[1].getNumber() + player.cards[2].getNumber();
    }
}
