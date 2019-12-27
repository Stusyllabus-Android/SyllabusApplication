package com.stu.syllabus.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stu.syllabus.R;
import com.stu.syllabus.bean.ContractInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContractRVAdapter extends RecyclerView.Adapter<ContractRVAdapter.ViewHolder> {

    private List<ContractInfo> contents;
    private RecyclerView parent;

    public ContractRVAdapter(List<ContractInfo> items, RecyclerView parent){
            contents = items;
            this.parent = parent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ContractRVAdapter.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nicknameTV.setText(contents.get(position).getNickname());
        holder.contentTv.setText(contents.get(position).getContent());
        holder.postTimeTv.setText(contents.get(position).getPostTime());
//        holder.postDeviceTv.setText(contents.get(position).getPostDevice());
//        holder.zanTv.setText(contents.get(position).getZans());
//        holder.commentT.setText(contents.get(position).getComments());

    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.contract_headImage)
        ImageView headImage;
        @BindView(R.id.contract_nicknameTV)
        TextView nicknameTV;
        @BindView(R.id.contract_contentTv)
        TextView contentTv;
        @BindView(R.id.contract_postTimeTv)
        TextView postTimeTv;
        @BindView(R.id.contract_postDeviceTv)
        TextView postDeviceTv;
        @BindView(R.id.contract_zanTv)
        TextView zanTv;
        @BindView(R.id.contract_commentTv)
        TextView commentT;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }



}
