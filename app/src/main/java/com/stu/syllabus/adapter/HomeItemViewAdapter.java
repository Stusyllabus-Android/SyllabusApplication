package com.stu.syllabus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.stu.syllabus.R;
import com.stu.syllabus.bean.HomeItemsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :wwshe
 * 2019/11/28
 */
public class HomeItemViewAdapter extends ArrayAdapter<HomeItemsItem> {
    private List<HomeItemsItem> list_home_item;
    private LayoutInflater inflater;
    Context context;
    int resourceId;

    public HomeItemViewAdapter(Context context,int resourceId, List<HomeItemsItem> list_home_item) {
        super(context, resourceId, list_home_item);
        this.context = context;
        this.list_home_item = list_home_item;
        this.resourceId = resourceId;
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
            inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(resourceId,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        String text = list_home_item.get(position).getItem();
        viewHolder.bt_home_item.setText(text);
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
