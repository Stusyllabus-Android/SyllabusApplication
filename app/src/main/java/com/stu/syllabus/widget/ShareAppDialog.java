package com.stu.syllabus.widget;

import android.view.View;
import android.widget.RelativeLayout;

import com.stu.syllabus.R;

/**
 * yuan
 * 2019/11/25
 **/
public class ShareAppDialog extends BaseBottomDialog implements View.OnClickListener {

    RelativeLayout shareToWechat;
    RelativeLayout shareToMoment;
    RelativeLayout shareToQQ;
    RelativeLayout shareToQQZone;
    RelativeLayout copyLink;

    OnShareSelectCallBack onShareSelectCallBack;

    @Override
    public void onClick(View v) {
        if (onShareSelectCallBack != null) {
            if (v.getId() == R.id.shareToWechat) {
                onShareSelectCallBack.onShareSelect(0);
            } else if (v.getId() == R.id.shareToMoment) {
                onShareSelectCallBack.onShareSelect(1);
            } else if (v.getId() == R.id.shareToQQ) {
                onShareSelectCallBack.onShareSelect(2);
            } else if (v.getId() == R.id.shareToQQZone) {
                onShareSelectCallBack.onShareSelect(3);
            } else {
                onShareSelectCallBack.onShareSelect(4);
            }
        }
        this.dismiss();
    }

    public interface OnShareSelectCallBack {
        void onShareSelect(int position);
    }

    public OnShareSelectCallBack setOnShareSelectCallBack(OnShareSelectCallBack onShareSelectCallBack) {
        return onShareSelectCallBack;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_share;
    }

    @Override
    public void bindView(View view) {
        shareToWechat = view.findViewById(R.id.shareToWechat);
        shareToMoment = view.findViewById(R.id.shareToMoment);
        shareToQQ = view.findViewById(R.id.shareToQQ);
        shareToQQZone = view.findViewById(R.id.shareToQQZone);
        copyLink = view.findViewById(R.id.copyLink);

        shareToWechat.setOnClickListener(this);
        shareToMoment.setOnClickListener(this);
        shareToQQ.setOnClickListener(this);
        shareToQQZone.setOnClickListener(this);
        copyLink.setOnClickListener(this);
    }
}
