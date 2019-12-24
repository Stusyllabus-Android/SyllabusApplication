package com.stu.syllabus.widget;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.stu.syllabus.R;

/**
 * yuan
 * 2019/11/25
 **/
public class ShareAppDialog extends BaseBottomDialog implements View.OnClickListener {
    private String TAG = this.getClass().getSimpleName();

    RelativeLayout shareToWechat;
    RelativeLayout shareToMoment;
    RelativeLayout copyLink;
    LinearLayout cancelLinearLayout;

    OnShareSelectCallBack onShareSelectCallBack;

    @Override
    public void onClick(View v) {
        if (onShareSelectCallBack != null) {
            if (v.getId() == R.id.shareToWechat) {
                onShareSelectCallBack.onShareSelect(0);
            } else if (v.getId() == R.id.shareToMoment) {
                onShareSelectCallBack.onShareSelect(1);
            } else if (v.getId() == R.id.copyLink){
                onShareSelectCallBack.onShareSelect(2);
            } else if (v.getId() == R.id.cancelLinearLayout) {
                this.dismiss();
            }
        }
        this.dismiss();
    }

    public interface OnShareSelectCallBack {
        void onShareSelect(int position);
    }

    public OnShareSelectCallBack setOnShareSelectCallBack(OnShareSelectCallBack onShareSelectCallBack) {
        return this.onShareSelectCallBack = onShareSelectCallBack;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_shareapp;
    }

    @Override
    public void bindView(View view) {
        shareToWechat = view.findViewById(R.id.shareToWechat);
        shareToMoment = view.findViewById(R.id.shareToMoment);
        copyLink = view.findViewById(R.id.copyLink);
        cancelLinearLayout = view.findViewById(R.id.cancelLinearLayout);

        shareToWechat.setOnClickListener(this);
        shareToMoment.setOnClickListener(this);
        copyLink.setOnClickListener(this);
        cancelLinearLayout.setOnClickListener(this);
    }
}
