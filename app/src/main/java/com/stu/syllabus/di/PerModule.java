package com.stu.syllabus.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by niuxiaowei on 16/3/22.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerModule {
}