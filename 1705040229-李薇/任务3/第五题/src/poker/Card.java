package poker;

import java.util.Random;

public class Card {

    public Card() {

    }

    //生成扑克
    public int[] Random_draw() {
        Random ran_card = new Random();
        int ran_cardnum[] = new int[6];
        for(int i = 0; i < 6; i++) {
            ran_cardnum[i] = ran_card.nextInt(51) + 1;
            int n = i;
            while(n>0) {
                if(ran_cardnum[i] == ran_cardnum[n-1]) {
                    do{
                        ran_cardnum[i] = ran_card.nextInt(51) + 1;
                    }while(ran_cardnum[i] == ran_cardnum[n-1]);
                }
                n--;
            }
        }
        return ran_cardnum;
    }

    public String card_image(int num) {
        String address = "./card_images/" + num + ".png";
        return address;
    }

    public String[] card_images(int[] num) {
        String address[] = new String[num.length];
        for(int i = 0; i <num.length; i++) {
            address[i] = "card_images/" + num[i] + ".png";
        }
        return address;
    }
}
