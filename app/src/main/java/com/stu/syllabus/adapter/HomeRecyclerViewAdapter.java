package com.stu.syllabus.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.stu.syllabus.R;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    List<Object> contents;
    private int item_position;
    RecyclerView parent;

    /*static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;*/

    public HomeRecyclerViewAdapter(List<Object> contents, RecyclerView parent) {
        this.contents = contents;
        this.parent = parent;
    }

    @Override
    public int getItemViewType(int position) {
        /*switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }*/
        super.getItemViewType(position);
        return 1;
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
        item_position = position;
        holder.tv_card.setText(contents.get(position).toString());
        holder.cardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //程序执行到此，会去执行具体实现的onItemClick()方法
        if (onItemClickListener!=null){
            onItemClickListener.onItemClick(parent,view,item_position,contents.get(item_position).toString());
        }
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 定义接口
     */
    public interface OnItemClickListener{
        //参数（父组件，当前单击的View,单击的View的位置，数据）
        void onItemClick(RecyclerView parent,View view, int position, String data);
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


