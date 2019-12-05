package com.stu.syllabus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.stu.syllabus.R;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    List<Object> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    public HomeRecyclerViewAdapter(List<Object> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_card, parent, false);

        return new HomeRecyclerViewAdapter.ViewHolder(view) {
        };
    }


    @Override
    public void onBindViewHolder(final HomeRecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.tv_card.setText(contents.get(position).toString());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newClickListener(holder,position);
            }
        });
    }

    public void newClickListener(ViewHolder holder, int position){
        //这里写上获取text信息，然后判断，在进入功能面
        if(holder.tv_card.getText().equals("流量监控")){
            holder.tv_card.setText("hello");
        }else if (holder.tv_card.getText().equals("hello")) {
            holder.tv_card.setText("流量监控");
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view)
        CardView cardView;
        @BindView(R.id.tv_card)
        TextView tv_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}


