package com.stu.syllabus.information;

import com.stu.syllabus.adapter.OAListAdapter;
import com.stu.syllabus.base.BasePresenter;
import com.stu.syllabus.base.BaseView;
import com.stu.syllabus.bean.OAArticle;

import java.util.List;

/**
 * yuan
 * 2019/11/28
 **/
public interface OAContract {
    interface view extends BaseView{
        void showOA(List<OAArticle> articleList);
    }
    interface presenter extends BasePresenter {

    }
}
