package com.stu.syllabus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stu.syllabus.R;
import com.stu.syllabus.bean.OASearchBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
/*@author cxy
 * by2019/12/24
 * */
public class OASearchAdapter extends ArrayAdapter<OASearchBean> {
    public static OASearchBean oasearchbean;
    int resourceId;
    public OASearchAdapter(@NonNull Context context, int resource, @NonNull List<OASearchBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        oasearchbean = getItem(position);
        OASearchAdapter.ViewHolder viewHolder;
        View view;
        if (convertView != null) {
            view = convertView;
            viewHolder = (OASearchAdapter.ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new OASearchAdapter.ViewHolder(view);
            view.setTag(viewHolder);
        }
        viewHolder.oATitleTextView.setText(oasearchbean.getTitle());
        viewHolder.oASubTextView.setText(oasearchbean.getDepartment());
        viewHolder.oATimeTextView.setText(oasearchbean.publishDate);
        return view;
    }

    class ViewHolder {
        @BindView(R.id.oATitleTextView)
        TextView oATitleTextView;
        @BindView(R.id.oASubTextView)
        TextView oASubTextView;
        @BindView(R.id.oATimeTextView)
        TextView oATimeTextView;

        public ViewHolder(View view) {
            super();
            ButterKnife.bind(this, view);
        }
    }
}
