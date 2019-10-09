package com.software.t5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ContactViewHolder> {
    //MyAdapter的成员变量contactInfoList, 这里被我们用作数据的来源
    private List<Card> cardList;
    //构造函数 增加context glide加载图片要使用
    public RvAdapter(List<Card> cardList) {
        this.cardList = cardList;
    }
    //重写3个抽象方法
    @NonNull
    @Override
    public RvAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.player_cards, viewGroup, false);
        return new RvAdapter.ContactViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull RvAdapter.ContactViewHolder contactViewHolder, int i) {
        Card card = cardList.get(i);
        contactViewHolder.imCardColor.setImageResource(card.getColor());
        contactViewHolder.tvCardSize.setText(card.getSize());
        contactViewHolder.tvCardSize2.setText(card.getSize());
    }
    @Override
    public int getItemCount() {
        return cardList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imCardColor;
        protected TextView tvCardSize;
        protected TextView tvCardSize2;
        public ContactViewHolder(View itemView) {
            super(itemView);
            imCardColor = itemView.findViewById(R.id.iv_card_color);
            tvCardSize = itemView.findViewById(R.id.tv_card_size);
            tvCardSize2 = itemView.findViewById(R.id.tv_card_size2);
        }
    }//ContactViewHolder class
}//adapter.class