package com.stu.syllabus.bean;

import com.google.gson.annotations.SerializedName;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static com.stu.syllabus.util.AssetUtil.getStringFromPath;

/*@author cxy
* by2019/12/05
* */
public class OADetail {
    /* "title": "中国科学引文索引（CSCI）数据库试用通知",
    "content": "<p align='left' class='MsoNormal'>《中国科学引文索引（CSCI）》是由中国科学技术信息研究所推出的基于期刊引用的检索评价工具，囊括了2000年来我国出版的科技类和部分社科类学术期刊约9000余种，累积论文40,614,512篇，引文记录194,344,681条，是目前最完备的中文论文引文库。</p><p align='left' class='MsoNormal'><b>访问链接：<a href='http://www.lib.stu.edu.cn/database/1423'>http://www.lib.stu.edu.cn/database/1423</a> </b></p><p align='left' class='MsoNormal'>试用结束日期： 2020年 2 月 29日</p><p align='left' class='MsoNormal'></p><p align='left' class='MsoNormal'>欢迎广大师生读者积极使用！试用评价和意见请反馈至图书馆学科服务部，Tel：86503247，Email：<a href='mailto:lqxie@stu.edu.cn'>lqxie@stu.edu.cn</a> </p><p align='left' class='MsoNormal'></p><p align='right' class='MsoNormal'>图书馆</p><p align='right' class='MsoNormal'>2019年11 月27日</p>",
    "attachment": [],
    "code": "0"*/

    @SerializedName("title")
    public String title;
    @SerializedName("content")
    public String content;
    @SerializedName("attachment")
    public String attachment;
    @SerializedName("code")
    public int code;

    public String getTitle(){return title;}
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.title = content;
    }
    public String getContent() {

        String oaContent = "";
        String label = "!@#$%^&*";

//        oaContent = getDOCCONTENT();
        int index = oaContent.indexOf(label) + label.length();
        oaContent = oaContent.substring(index);

        org.jsoup.nodes.Document contentDoc = Jsoup.parse(oaContent);

        Elements imgs = contentDoc.select("img");
        for (org.jsoup.nodes.Element img : imgs) {
            imgs.attr("style", "width: 100%;");
        }

        Elements tables = contentDoc.getElementsByTag("table");
        for (org.jsoup.nodes.Element table : tables) {
            table.attr("width", "100%");
            table.attr("style", "width: 100%;");
            Elements trs = table.select("tr");
            for (org.jsoup.nodes.Element tr : trs) {
                Elements tds = tr.select("td");
                double witdh = 100.0 / tds.size();
                for (org.jsoup.nodes.Element td : tds) {
                    String colspan = td.attr("colspan");
                    if (colspan.trim().isEmpty()) {
                        td.attr("style", "width:" + witdh + "%");
                    } else {
                        td.attr("style", "width:" + witdh * Integer.parseInt(colspan) + "%");
                    }
                    td.removeAttr("nowrap");
                }
            }
        }

        Document doc = Jsoup.parse(getStringFromPath("index.html"));
        Element div = doc.select("div#div_doc").first();
        div.append(contentDoc.toString());
        div = doc.select("div#div_accessory").first();

//        if (getACCESSORYCOUNT() == 0) {
//            div.remove();
//        } else {
//        }

        return doc.toString();
    }
}
