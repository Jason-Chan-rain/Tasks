package com.software.t5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.bt_start)
    Button btStart;
    @BindView(R.id.et_times)
    EditText etTimes;//模拟次数
    @BindView(R.id.rv_player_1_cards)
    RecyclerView mRecyclerView;
    @BindView(R.id.rv_player_2_cards)
    RecyclerView mRecyclerView2;
    @BindView(R.id.lv_result)
    ListView lvResult;
    @BindView(R.id.tv_result)
    TextView tvResult;


    //四种花色资源文件
    static int[] cardColorPic = {
            R.mipmap.diamond,
            R.mipmap.red_heart,
            R.mipmap.clubs,
            R.mipmap.black_heart};
    static String[] cardColor = {"方块", "红桃", "梅花", "黑桃"};
    static String[] cardSize = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    static ArrayList<Card> card = new ArrayList();//所有牌
    static ArrayList cardStr = new ArrayList();//所有牌 String
    static ArrayList cardStrPlayer = new ArrayList();//玩家牌 String

    static int weightValue1;//玩家牌等级 权值 比较大小
    static int weightValue2;

    static int[] rank = new int[5];//每种类型牌场数
    static int[] rankWin = new int[5];//每种类型牌赢得场数
    static int[] rankNoLoser = new int[5];//每种类型牌平局得场数

    int times;//总次数
    static int playerWin1 = 0;//玩家一胜场
    static int playerWin2 = 0;

    static ArrayList cardResult = new ArrayList();//发牌+结果集合 String集合
    static String tempres = "";//一轮的结果

    static String[] kind = {"同花顺", "顺子", "同点", "对子", "杂牌"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initCard();//初始化牌
        initView();
        initListener();
    }

    private void initView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView2.setHasFixedSize(true);
        //平常的水平一个item布局的流
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView2.setLayoutManager(layoutManager2);
    }

    private void initListener() {
        btStart.setOnClickListener(view -> {
            String strTimes = etTimes.getText().toString();
            try {
                //重置结果
                rank = new int[5];//每种类型牌场数
                rankWin = new int[5];//每种类型牌赢得场数
                rankNoLoser = new int[5];//每种类型牌平局得场数
                times = 0;//模拟次数
                playerWin1 = 0;//玩家一胜场
                playerWin2 = 0;//玩家二胜场
                cardResult = new ArrayList();

                times = Integer.parseInt(strTimes);
                for (int i = 0; i < times; i++) cardGame();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1, cardResult);
                lvResult.setAdapter(adapter);

                String rankResult = "";
                for (int i = 0; i < rank.length; i++) {
                    rankResult += "\n等级" + (5 - i) + "(" + kind[4 - i] + ")" + "胜率：" + 100 * (float) rankWin[i] / rank[i]
                            + "  平局率：" + 100 * (float) rankNoLoser[i] / rank[i];
                }

                tvResult.setText("模拟结果:" + rankResult
//                        + "\n 玩家1胜场:" + playerWin1 +
//                        " 胜率" + 100 * (float) playerWin1 / times + "%"
//                        + "\n 玩家2胜场:" + playerWin2 + " 胜率" + 100 * (float) playerWin2 / times + "%"
//                        + "\n 平局:" + (times - playerWin1 - playerWin2)
//                        + " 概率" + 100 * (times - playerWin1 - playerWin2) / (float) times + "%"
                );

            } catch (NumberFormatException e) {
                //异常处理
                Toast.makeText(MainActivity.this, "请输入输入大于0的整数", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void initCard() {
        for (int i = 0; i < cardColor.length; i++) {
            for (String s : cardSize) {
                card.add(new Card(cardColorPic[i], cardSize[i]));
                cardStr.add(cardColor[i] + "" + s);
            }
        }
    }

    /**
     * 同等级牌比较点数
     *
     * @param resultc 玩家1牌下标除13余数集合
     * @param resultd 玩家2牌下标除13余数集合
     */
    public static void point(List<Integer> resultc, List<Integer> resultd) {
        int point1 = 0, point2 = 0; //点数为 余数+1  2的点数为1 ... A点数为13
        for (int i = 0; i < resultc.size(); i++) point1 += resultc.get(i) + 1;
        for (int i = 0; i < resultd.size(); i++) point2 += resultd.get(i) + 1;
        if (point1 > point2) {
            tempres += " 玩家1赢";
            playerWin1++;
            rankWin[weightValue1 - 1]++;//该等级胜场+1
        } else if (point1 < point2) {
            tempres += " 玩家2赢";
            playerWin2++;
        } else {
            tempres += " 平局";
            rankNoLoser[weightValue1 - 1]++;//该等级平局+1
        }
    }

    private void cardGame() {
        //每次比完都重新初始化
        weightValue1 = 1;//玩家牌 等级
        weightValue2 = 1;
        HashSet<Integer> playerCard = new HashSet<>();//玩家牌 下标
        int num = 51;//52张牌
        while (playerCard.size() != 6) {
            playerCard.add((int) (Math.random() * num)); //生成从m到n的随机整数[0,51] 52张
        }
        ArrayList<Integer> result = new ArrayList(playerCard);//发牌结果 下标

        cardStrPlayer = new ArrayList();
        tempres = "";//一局的结果
        for (int i = 0; i < result.size(); i++) {
            tempres += cardStr.get(result.get(i));
            String a = cardStr.get(result.get(i)) + "";
            cardStrPlayer.add(new Card(card.get(result.get(i)).getColor(), a.substring(2)));
        }
        //实例化MyAdapter并传入mList对象
        runOnUiThread(() -> {
            RvAdapter rvAdapter = new RvAdapter(cardStrPlayer.subList(0, 3));
            RvAdapter rvAdapter2 = new RvAdapter(cardStrPlayer.subList(3, 6));
            mRecyclerView.setAdapter(rvAdapter);
            mRecyclerView2.setAdapter(rvAdapter2);
        });
        //比较大小
        ArrayList<Integer> resultTemp = new ArrayList<>();//取余数
        ArrayList<Integer> resultTemp2 = new ArrayList<>();//取整数
        for (int i : result) {
            resultTemp.add(i % 13);//下标+1除以13取余数 对应牌的面值 点数
            resultTemp2.add(i / 13);//下标+1除以13取余整数 得到对应花色
        }
        //等级一 同花顺 =5
        //等级二 顺子 =4
        //等级三 同点 =3
        //等级四 对子  =2
        //等级五 杂牌  =1
        //玩家权值 默认为1
        List<Integer> resulta = resultTemp2.subList(0, 3);//Collections.sort(resulta);
        List<Integer> resultb = resultTemp2.subList(3, 6);
        List<Integer> resultc = resultTemp.subList(0, 3);
        List<Integer> resultd = resultTemp.subList(3, 6);
        Collections.sort(resultc);//排序
        Collections.sort(resultd);
        //按照等级4 3 2 1分类 因为部分等级有包含关系 如同点包含对子

        //等级四 对子   取余数 两个一样
        if (resultc.get(0).equals(resultc.get(1)) || resultc.get(0).equals(resultc.get(2)) || resultc.get(1) == resultc.get(2))
            weightValue1 = 2;
        if (resultd.get(0).equals(resultd.get(1)) || resultd.get(0).equals(resultd.get(2)) || resultd.get(1) == resultd.get(2))
            weightValue2 = 2;
        //等级三 同点   取余数 三个一样
        if (resultc.get(0).equals(resultc.get(1)) && resultc.get(1).equals(resultc.get(2)))
            weightValue1 = 3;
        if (resultd.get(0).equals(resultd.get(1)) && resultd.get(1).equals(resultd.get(2)))
            weightValue2 = 3;
        //等级二 顺子   取余数
        if (resultc.get(0) == resultc.get(1) - 1 && resultc.get(0) == resultc.get(2) - 2)
            weightValue1 = 4;
        if (resultd.get(0) == resultd.get(1) - 1 && resultd.get(0) == resultd.get(2) - 2)
            weightValue2 = 4;
        //等级一 同花顺  取整数
        if (resulta.get(0).equals(resulta.get(1)) && resulta.get(0).equals(resulta.get(2)))
            weightValue1 = 5;
        if (resultb.get(0).equals(resultb.get(1)) && resultb.get(0).equals(resultb.get(2)))
            weightValue2 = 5;

        rank[weightValue1 - 1]++;//对应牌型场数
        if (weightValue1 > weightValue2) {
            tempres += " 玩家1赢";
            rankWin[weightValue1 - 1]++;//该等级胜场+1
            playerWin1++;
        }
        if (weightValue1 < weightValue2) {
            tempres += " 玩家2赢";
            playerWin2++;
        }
        //等级五 杂牌  整数余数都要看 先看余数——面值 后看整数——花色
        //等级相同（包括均为杂牌）比较点数
        if (weightValue1 == weightValue2) {
            point(resultc, resultd);
        }
        cardResult.add(tempres);
    }//cardgame

}
