package com.stu.syllabus.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stu.syllabus.R;
import com.stu.syllabus.bean.ClassmateInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * yuan
 * 2019/12/26
 **/
public class ClassmatesInfoAdapter extends RecyclerView.Adapter<ClassmatesInfoAdapter.ViewHolde> {

    private Context context;
    private List<ClassmateInfo> classmateInfoList;

    public ClassmatesInfoAdapter(Context context, List<ClassmateInfo> classmateInfos) {
        super();
        this.context = context;
        classmateInfoList = classmateInfos;
    }

    @NonNull
    @Override
    public ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_classmate_info, parent, false);
        return new ViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolde holder, int position) {
        ClassmateInfo classmateInfo = classmateInfoList.get(position);
        holder.mStudentNameTextView.setText(classmateInfo.getName());
        holder.mStudentNoTextView.setText(classmateInfo.getNumber());
        holder.mStudentMajorTextView.setText(classmateInfo.getMajor());

        GradientDrawable shape = (GradientDrawable) context.getResources()
                .getDrawable(R.drawable.bg_sex);
        shape.setColor(context.getResources().getColor(
                classmateInfo.getGender().equals("男") ?
                        R.color.material_blue_300 : R.color.material_pink_300));
        holder.mSexView.setBackgroundDrawable(shape);
        holder.mDevLine.setVisibility(position == (classmateInfoList.size() - 1)
                ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        if (classmateInfoList == null) {
            return 0;
        }
        return classmateInfoList.size();
    }

    public class ViewHolde extends RecyclerView.ViewHolder {
        @BindView(R.id.studentNameTextView)
        TextView mStudentNameTextView;
        @BindView(R.id.studentNoTextView)
        TextView mStudentNoTextView;
        @BindView(R.id.studentMajorTextView)
        TextView mStudentMajorTextView;
        @BindView(R.id.sexView)
        View mSexView;
        @BindView(R.id.devLine)
        View mDevLine;

        public ViewHolde(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
