package com.stu.syllabus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.stu.syllabus.R;
import com.stu.syllabus.bean.YiBanTimeTable;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * yuan
 * 2019/11/23
 **/
public class SyllabusListViewAdapter extends ArrayAdapter<YiBanTimeTable.TableBean> {

    int resourceId;
    private List<YiBanTimeTable.TableBean> tableBeanList;

    public SyllabusListViewAdapter(@NonNull Context context, int resource, @NonNull List<YiBanTimeTable.TableBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
        tableBeanList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final YiBanTimeTable.TableBean tableBean = getItem(position);
        View view;
        final ViewHolder viewHolder;
        if (convertView != null) {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        if (tableBean != null) {
            viewHolder.kc_name.setText(tableBean.kcName);
            viewHolder.xnxq_name.setText(tableBean.xnxqName);
            viewHolder.sj_name.setText(tableBean.sjName);
            viewHolder.ks_name.setText(tableBean.ksName);
        }
        return view;
    }

    class ViewHolder {

        @BindView(R.id.kc_name)
        TextView kc_name;
        @BindView(R.id.xnxq_name)
        TextView xnxq_name;
        @BindView(R.id.sj_name)
        TextView sj_name;
        @BindView(R.id.ks_name)
        TextView ks_name;

        public ViewHolder(View view) {
            super();
            ButterKnife.bind(this, view);
        }
    }
}
