package com.stu.syllabus.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.stu.syllabus.R;
import com.stu.syllabus.bean.HomeItem;
import com.stu.syllabus.bean.HomeItemsItem;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :wwshe
 * 2019/11/28
 * 图书馆、流量监控...等分类的ListView
 */
public class HomeListViewAdapter extends ArrayAdapter<HomeItem> {
    private List<HomeItem> list_home;
    private List<HomeItemsItem> list_home_item;
    private LayoutInflater inflater;
    private HomeItemViewAdapter homeItemViewAdapter;
    Context context;
    int resouceId;

    /**
     *
     * @param context:上下文
     * @param resourceId: LayoutInflater
     * @param list_home: data
     */
    public HomeListViewAdapter(Context context, int resourceId, List<HomeItem> list_home) {
        super(context, resourceId, list_home);
        this.list_home = list_home;
        this.context = context;
        this.resouceId = resourceId;
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
            view = inflater.inflate(resouceId,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        //1.让textView显示大类的名称
        String text = list_home.get(position).getFunc();
        viewHolder.tv_home.setText(text);

        //2.让右边的ListView显示按钮，获取view
        list_home_item = new ArrayList<>();
        list_home_item = list_home.get(position).getFuncItem();
        viewHolder.lv_home.setAdapter(new HomeItemViewAdapter(context,R.layout.item_items_home,list_home_item));
        return view;
    }

    class ViewHolder{
        @BindView(R.id.tv_home)
        TextView tv_home;
        @BindView(R.id.lv_items)
        ListView lv_home;

        public ViewHolder(View view){
            super();
            ButterKnife.bind(this, view);
        }

    }
}
