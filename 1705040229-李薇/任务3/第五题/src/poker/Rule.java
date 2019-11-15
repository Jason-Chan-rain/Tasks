package poker;

import java.util.*;


public class Rule {
    Card ca = new Card();
    public Rule() {

    }

    public int judges() {
        int card[] = ca.Random_draw();
        return judge(card);
    }

    public int judge(int[] card) {
        //int card[] = ca.Random_draw();
        int value[][] = new int[2][3];
        //求点数和
        value[0][2] = card[0] + card[1] + card[2];
        value[1][2] = card[3] + card[4] + card[5];
        //玩家A
        if((card[0] / 13 == card[1] / 13) && (card[1] / 13 == card[2] / 13)) {
            value[0][0] = 1;
            value[0][1] = 1;//同花
        }else if(((card[0]+1) % 13 == card[1] % 13) && ((card[1]+1) % 13 == card[2] % 13)) {
            value[0][0] = 2;//顺子
            if((card[0] / 13 != card[1] / 13) && (card[1] / 13 != card[2] / 13) && (card[0] / 13 != card[2] / 13)) {
                value[0][1] = 3;
            }else {
                value[0][1] = 2;
            }
        }else if((card[0] % 13 == card[1] % 13) && (card[1] % 13 == card[2] % 13)) {
            value[0][0] = 3;//同点
            value[0][1] = 1;
        }else if((card[0] % 13 == card[1] % 13) || (card[0] % 13 == card[2] % 13)|| (card[1] % 13 == card[2] % 13)) {
            value[0][0] = 4;//对子
            value[0][1] = 1;
        }else {
            value[0][0] = 5;
            value[0][1] = 1;
        }

        //玩家B
        if((card[3] / 13 == card[4] / 13) && (card[4] / 13 == card[5] / 13)) {
            value[1][0] = 1;
            value[1][1] = 1;
        }else if(((card[3]+1) % 13 == card[4] % 13) && ((card[4]+1) % 13 == card[5] % 13)) {
            value[1][0] = 2;
            if((card[3] / 13 != card[4] / 13) && (card[4] / 13 != card[5] / 13) && (card[3] / 13 != card[5] / 13)) {
                value[1][1] = 3;
            }else {
                value[1][1] = 2;
            }
        }else if((card[3] % 13 == card[4] % 13) && (card[4] % 13 == card[5] % 13)) {
            value[1][0] = 3;
            value[1][1] = 1;
        }else if((card[3] % 13 == card[4] % 13) || (card[3] % 13 == card[5] % 13)|| (card[4] % 13 == card[5] % 13)) {
            value[1][0] = 4;
            value[1][1] = 1;
        }else {
            value[1][0] = 5;
            value[1][1] = 1;
        }

        //A、B对比
        if(value[0][0] < value[1][0])
        {
            return -1*value[0][0];//A胜
        }
        else if(value[0][0] == value[1][0])
        {
            if(value[0][2] == value[1][2])
            {
                return 0;//平局
            }
            else if(value[0][2] > value[1][2])
            {
                return -1*value[0][0];//A胜
            }
            else
                {
                return value[1][0];//B胜
                }
        }
        else {
            return value[1][0];//B胜
        }
    }

    public void card_paint(int[] card) {
        main.window.set_address(ca.card_images(card));
    }

    public void game_start() {
        //设置状态--显示值 键值对
        Map <Integer, String> map = new HashMap<>();
        map.put(5, "杂牌");
        map.put(4, "对子");
        map.put(3, "同点");
        map.put(2, "顺子");
        map.put(1, "同花");
        map.put(0, "平局");

        int card[] = ca.Random_draw();
        card_paint(card);
        int status =judge(card);
        if(status > 0) {
            main.window.winner.setText("B");
            main.window.win_type.setText(map.get(status));
        }else if(status == 0) {
            main.window.winner.setText("AB");
            main.window.win_type.setText(map.get(status));
        }else {
            main.window.winner.setText("A");
            main.window.win_type.setText(map.get(-1*status));
        }
    }
}
