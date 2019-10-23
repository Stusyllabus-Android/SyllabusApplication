package com.stu.syllabus;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * yuan
 * 2019/10/23
 **/
@Module
public final class ApplicationModule {
    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }
    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }
}
