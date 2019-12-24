package com.stu.syllabus.information.search;

import com.stu.syllabus.bean.OASearchBean;

import java.util.List;

import io.reactivex.Observable;
/*@author cxy
 * by2019/12/24
 * */
public interface IOASearchModel {
    Observable<List<OASearchBean>> getOASearch(String keyword,int row_start, int row_end);
}
