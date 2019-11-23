package com.stu.syllabus.syllabus;

import android.util.Log;

import com.stu.syllabus.bean.UserInfo;
import com.stu.syllabus.bean.YiBanTimeTable;
import com.stu.syllabus.bean.YiBanToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * yuan
 * 2019/11/12
 **/
public class SyllabusPresenter implements SyllabusContract.presenter {
    private final String TAG = this.getClass().getSimpleName();

    SyllabusContract.view view;
    ISyllabusModel model;

    UserInfo mUserInfo;
    YiBanToken mYiBanToken;
    YiBanTimeTable mYiBanTimeTable;
    Document document;
    String token;

    @Inject
    public SyllabusPresenter(SyllabusContract.view view, ISyllabusModel model) {
        super();
        this.view = view;
        this.model = model;
    }

    @Override
    public void init() {
//        getTimeTableFromYiBan();

    }

    /**
     * for test
     */
    public void getGetRequestToken() {
        Log.d(TAG, "getGetRequestToken: ");
        model.getRequestToken()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
                        document = Jsoup.parseBodyFragment(s);
//                        tokenEle = document.select("input").get(3);
//                        Log.d(TAG, "onNext: " + tokenEle);
                        Element tokenEle = document.getElementsByAttributeValue("name", "__RequestVerificationToken").first();
                        Log.d(TAG, "onNext: " + tokenEle);
                        token = tokenEle.attr("value");
                        Log.d(TAG, "onNext: " + token);
                        try {
                            Log.d(TAG, "onNext: " + URLEncoder.encode("wOCDzlYurJhAm7pPJ/7BqgIJ+QFytaOG7ZbbKkj6IYQ=", "UTF-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //要带前一步返回的cookie进行请求
    public void login(String email, String password, String requestToken) {

        model.login(email, password, requestToken)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getToken() {
        model.getToken()
                .subscribe(new Observer<YiBanToken>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(YiBanToken token) {
                        mYiBanToken = token;
                        Log.d(TAG, "onNext: " + mYiBanToken.vid);
                        Log.d(TAG, "onNext: " + mYiBanToken.timestamp);
                        Log.d(TAG, "onNext: " + mYiBanToken.app);
                        Log.d(TAG, "onNext: " + mYiBanToken.nonce);
                        Log.d(TAG, "onNext: " + mYiBanToken.token);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getTimeTable() {
        try {
            mYiBanToken.token = URLEncoder.encode(mYiBanToken.token, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.getTimeTable(mYiBanToken.vid, mYiBanToken.timestamp, mYiBanToken.app, mYiBanToken.nonce, mYiBanToken.token)
                .subscribe(new Observer<YiBanTimeTable>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(YiBanTimeTable timeTable) {
                        List<YiBanTimeTable.TableBean> tableBeanList = timeTable.getTable();
                        for (YiBanTimeTable.TableBean tableBean : tableBeanList) {
                            Log.d(TAG, "onNext: " + tableBean.getJsName());
                            Log.d(TAG, "onNext: " + tableBean.getKcName());
                            Log.d(TAG, "onNext: " + tableBean.getKsName());
                            Log.d(TAG, "onNext: " + tableBean.getSjName());
                            Log.d(TAG, "onNext: " + tableBean.getXnxqName());
                            Log.d(TAG, "onNext: " + tableBean.getKkbKey());
                        }
                        Log.d(TAG, "onNext: " + timeTable);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //爬取易班上的课程信息
    public void getTimeTableFromYiBan() {
        //获取用户的账号与密码
        model.getUserInfoFromDisk()
                .subscribe(new Observer<UserInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        Log.d(TAG, "onNext: " + userInfo.getAccount());
                        Log.d(TAG, "onNext: " + userInfo.getAccount());
                        mUserInfo = userInfo;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        //获取网页token
        model.getRequestToken()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        document = Jsoup.parseBodyFragment(s);
                        Element tokenElement = document.getElementsByAttributeValue("name", "__RequestVerificationToken").first();
                        token = tokenElement.attr("value");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        //进行登录操作
        model.login(mUserInfo.getAccount() + "stu.edu.cn", mUserInfo.getPassword(), token)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        //获取token
        model.getToken()
                .subscribe(new Observer<YiBanToken>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final YiBanToken yiBanToken) {
                        mYiBanToken = yiBanToken;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        //获取课表
        model.getTimeTable(mYiBanToken.vid, mYiBanToken.timestamp, mYiBanToken.app, mYiBanToken.nonce, mYiBanToken.token)
                .subscribe(new Observer<YiBanTimeTable>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final YiBanTimeTable yiBanTimeTable) {
                        mYiBanTimeTable = yiBanTimeTable;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
