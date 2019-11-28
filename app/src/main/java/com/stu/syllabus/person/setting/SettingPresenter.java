package com.stu.syllabus.person.setting;

import javax.inject.Inject;

/**
 * yuan
 * 2019/11/24
 **/
public class SettingPresenter implements SettingContract.presenter {

    SettingContract.view view;
    ISettingModel settingModel;

    @Inject
    public SettingPresenter(SettingContract.view view, ISettingModel settingModel) {
        super();
        this.view = view;
        this.settingModel = settingModel;
    }

    @Override
    public void init() {
        view.init();
    }

    @Override
    public void recommendTo() {
        view.showShareDialog();
    }

    @Override
    public void logout() {
        settingModel.logout();
        view.toLoginActivity();
    }
}
