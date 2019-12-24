package com.stu.syllabus.information.search;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.bean.OASearchBean;
import com.stu.syllabus.di.PerActivity;
import com.stu.syllabus.information.OAModel;
import com.stu.syllabus.information.OAModelModule;
import com.stu.syllabus.information.OASearch;
import dagger.Component;

/*@author cxy
 * by2019/12/24
 * */
    @PerActivity
    @Component(dependencies = AppComponent.class, modules = OAModelModule.class)
    public interface OAModelComponent {
        void inject(OASearch oasearch);

    }
