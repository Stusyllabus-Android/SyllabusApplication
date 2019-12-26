package com.stu.syllabus.home.wirelessData;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.reflect.TypeToken;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.stu.syllabus.App;
import com.stu.syllabus.RetrofitModule;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class InternetModel {

    boolean isOpen;

    public Retrofit mRetrofit;

    SharedPreferences mSharedPreferences;

    SharedPreferences.Editor mEditor;

    private static InternetModel ourInstance = new InternetModel();

    public static InternetModel getInstance() {
        return ourInstance;
    }

    private List<LoginAccount> mLoginAccounts;

    public static final String EXTRA_LOGIN_ACCOUNTS = "mLoginAccounts";

    public static final String EXTRA_IS_OPEN_INTERNET = "isOpen";

    private InternetModel() {
//        mRetrofit = RetrofitModule.provideWirelessRetrofit();//替换

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://1.1.1.2/ac_portal/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mSharedPreferences = App.getContext().getSharedPreferences("Internet", Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        isOpen = mSharedPreferences.getBoolean(EXTRA_IS_OPEN_INTERNET, false);

        String loginAccountsString = mSharedPreferences.getString(EXTRA_LOGIN_ACCOUNTS, "");
        if (loginAccountsString.trim().isEmpty()) {
            mLoginAccounts = new ArrayList<>();
        } else {
            //有待改进

            mLoginAccounts = GsonUtil.getDefault().fromJson(
                    loginAccountsString, new TypeToken<List<LoginAccount>>() {
                    }.getType()
            );
        }

    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
        mEditor.putBoolean(EXTRA_IS_OPEN_INTERNET, open);
        mEditor.commit();
    }

    public static class LoginAccount {

        public String account;
        public String password;

    }

    public List<LoginAccount> getLoginAccounts() {
        return mLoginAccounts;
    }

    public void addAccount(LoginAccount account) {
        boolean isExist = false;
        for (LoginAccount loginAccount : mLoginAccounts) {
            if (loginAccount.account.equals(account.account)) {
                loginAccount.password = account.password;
                isExist = true;
            }
        }
        if (!isExist) {
            mLoginAccounts.add(account);
        }

        mEditor.putString(EXTRA_LOGIN_ACCOUNTS, GsonUtil.getDefault().toJson(mLoginAccounts));
        mEditor.commit();
    }

    public void deleteAccount(LoginAccount account) {
        mLoginAccounts.remove(account);
        mEditor.putString(EXTRA_LOGIN_ACCOUNTS, GsonUtil.getDefault().toJson(mLoginAccounts));
        mEditor.commit();
    }

    public String[] getAccountName() {
        String[] accountNames = new String[mLoginAccounts.size()];
        for (int i = 0; i < mLoginAccounts.size(); i++) {
            accountNames[i] = mLoginAccounts.get(i).account;
        }
        return accountNames;
    }
}
