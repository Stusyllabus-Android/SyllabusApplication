package com.stu.syllabus.syllabus.syllabusmain;

import javax.inject.Inject;

/**
 * yuan
 * 2019/12/22
 **/
public class SyllabusMainPresenter implements SyllabusMainContract.presenter {

    SyllabusMainContract.view view;

    @Inject
    public SyllabusMainPresenter(SyllabusMainContract.view view) {
        super();
        this.view = view;
    }

    @Override
    public void init() {

    }
}
