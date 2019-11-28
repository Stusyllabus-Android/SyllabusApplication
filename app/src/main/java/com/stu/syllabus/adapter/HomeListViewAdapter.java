package com.stu.syllabus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.stu.syllabus.R;
import com.stu.syllabus.bean.HomeItem;
import com.stu.syllabus.bean.HomeItemsItem;
import com.stu.syllabus.syllabus.SyllabusContract;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 图书馆、流量监控...等分类的ListView
 */
public class HomeListViewAdapter extends BaseAdapter{
    private List<HomeItem> list_home;
    private List<HomeItemsItem> list_home_item;
    private LayoutInflater inflater;
    private HomeItemViewAdapter homeItemViewAdapter;
    Context context;

    /**
     *
     * @param list_home: 分类list的数据：图书馆、预约、流量监控
     * @param inflater: ？？？
     * @param list_home_item: 每个分类的item中list数据
     */
    public HomeListViewAdapter(Context context, List<HomeItem> list_home, LayoutInflater inflater) {
        this.list_home = list_home;
        this.inflater = inflater;
        this.context = context;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView != null) {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        } else {
            view = inflater.inflate(R.layout.item_home,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        //1.让textView显示大类的名称
        viewHolder.tv_home.setText(list_home.get(position).getFunc());
        //2.让右边的ListView显示按钮，获取view
        list_home_item = list_home.get(position).getFuncItem();
        viewHolder.lv_home.setAdapter(new HomeItemViewAdapter(context,list_home_item, inflater));
        return view;

    }

    /*public void setAdapterForListView() {
        homeItemViewAdapter = new HomeItemViewAdapter(context,list_home_item, inflater);
        lv_home_item.setAdapter(homeItemViewAdapter);
    }*/


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
