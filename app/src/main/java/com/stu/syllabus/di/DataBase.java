package com.stu.syllabus.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * yuan
 * 2019/10/24
 **/
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface DataBase {
}
