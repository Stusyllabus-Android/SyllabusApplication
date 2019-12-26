package com.stu.syllabus.home.wirelessData;

import dagger.Module;
import dagger.Provides;

/**
 * wwshe
 * 2019/12/20
 */
@Module
public class WirelessModule {

    private final WirelessContract.view mView;

    public WirelessModule(WirelessContract.view view) {
        mView = view;
    }
    @Provides
    WirelessContract.view provideView() {
        return mView;
    }
}
