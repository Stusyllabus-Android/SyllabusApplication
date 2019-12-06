package com.stu.syllabus.information;

import com.stu.syllabus.bean.OAArticle;

import java.util.List;

import io.reactivex.Observable;

/**
 * yuan
 * 2019/11/28
 **/
public interface IOAModel {

    Observable<List<OAArticle>> getOAArticle(int row_start, int row_end, int subcompany_id);
}
