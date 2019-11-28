package com.stu.syllabus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.stu.syllabus.R;
import com.stu.syllabus.bean.HomeItemsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeItemViewAdapter extends BaseAdapter {
    private List<HomeItemsItem> list_home_item;
    private LayoutInflater inflater;
    Context context;

    public HomeItemViewAdapter(Context context, List<HomeItemsItem> list_home_item, LayoutInflater inflater) {
        this.context = context;
        this.list_home_item = list_home_item;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView != null) {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        } else {
            view = inflater.inflate(R.layout.item_items_home,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        viewHolder.bt_home_item.setText((CharSequence) list_home_item.get(position));
        return view;
    }

    class ViewHolder{
        @BindView(R.id.bt_lv_item)
        Button bt_home_item;

        public ViewHolder(View view){
            super();
            ButterKnife.bind(this, view);
        }
    }
}
