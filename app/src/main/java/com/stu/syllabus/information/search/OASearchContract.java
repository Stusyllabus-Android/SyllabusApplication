package com.stu.syllabus.information.search;

import com.stu.syllabus.base.BasePresenter;
import com.stu.syllabus.base.BaseView;
import com.stu.syllabus.bean.OAArticle;
import com.stu.syllabus.bean.OASearchBean;

import java.util.List;
/*@author cxy
 * by2019/12/24
 * */
public interface OASearchContract {
    interface view extends BaseView

    {
        void isRefresh(boolean flag);
        void showOASearch(List<OASearchBean> articleList);
    }
    interface presenter extends BasePresenter {

    }
}
