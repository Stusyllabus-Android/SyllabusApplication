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
import com.stu.syllabus.bean.OAArticle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * yuan
 * 2019/11/28
 **/
public class OAListAdapter extends ArrayAdapter<OAArticle> {
    int resourceId;
    public OAListAdapter(@NonNull Context context, int resource, @NonNull List<OAArticle> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        OAArticle oaArticle = getItem(position);
        ViewHolder viewHolder;
        View view;
        if (convertView != null) {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        viewHolder.oATitleTextView.setText(oaArticle.getTitle());
        viewHolder.oASubTextView.setText(oaArticle.getDepartment());
        viewHolder.oATimeTextView.setText(oaArticle.publishDate);
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
