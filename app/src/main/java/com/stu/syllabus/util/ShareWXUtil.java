package com.stu.syllabus.util;

import android.graphics.Bitmap;

import com.stu.syllabus.App;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;

/**
 * yuan
 * 2019/12/9
 **/
public class ShareWXUtil {

    public static void shareUrl(String url, String title, String desc, Bitmap bitmap, int scene) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = desc;
        msg.setThumbImage(bitmap);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = scene;
        App.getApi().sendReq(req);
    }

}
