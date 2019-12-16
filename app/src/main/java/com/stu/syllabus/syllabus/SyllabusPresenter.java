package com.stu.syllabus.syllabus;

import android.util.Log;

import com.stu.syllabus.bean.BaseUserInfo;
import com.stu.syllabus.bean.ShowLessonBean;
import com.stu.syllabus.bean.YiBanTimeTable;
import com.stu.syllabus.bean.YiBanToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
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

    BaseUserInfo mBaseUserInfo;
    YiBanToken mYiBanToken;
    Document document;
    String token;
    String currentSemester;
    List<YiBanTimeTable.TableBean> allTables;
    List<YiBanTimeTable.TableBean> currentTables;
    List<ShowLessonBean> lessonBeanList;


    @Inject
    public SyllabusPresenter(SyllabusContract.view view, ISyllabusModel model) {
        super();
        this.view = view;
        this.model = model;
        allTables = new LinkedList<>();
        currentTables = new LinkedList<>();
        lessonBeanList = new LinkedList<>();
    }

    @Override
    public void init() {
        Log.d(TAG, "init: ");
        if (currentSemester == null || currentSemester.equals("Non-existent")) return;
        model.getYiBanTableFromDisk()
                .subscribe(new Observer<List<YiBanTimeTable.TableBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<YiBanTimeTable.TableBean> tableBeanList) {
//                        Log.d(TAG, "onNext: tableBeans.size" + tableBeanList.size());
                        if (tableBeanList == null || tableBeanList.size() <= 1) getTimeTableFromNet();
                        else {
                            allTables = tableBeanList;
                            currentTables = model.filterTables(allTables, currentSemester);
                            Log.d(TAG, "onNext: currentTables" + currentTables.size());
                            view.showSyllabus(model.convertTablesToLessons(currentTables));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: getTableFromDisk");
                    }
                });
    }

    //爬取易班上的课程信息
    public void getTimeTableFromNet() {
        model.getUserInfoFromDisk()
                .subscribe(new Observer<BaseUserInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseUserInfo baseUserInfo) {
                        Log.d(TAG, "onNext: " + baseUserInfo.getAccount());
                        Log.d(TAG, "onNext: " + baseUserInfo.getPassword());
                        mBaseUserInfo = new BaseUserInfo(baseUserInfo.getAccount(), baseUserInfo.getPassword());
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
                                        Element tokenEle = document.getElementsByAttributeValue("name", "__RequestVerificationToken").first();
                                        Log.d(TAG, "onNext: " + tokenEle);
                                        token = tokenEle.attr("value");
                                        Log.d(TAG, "onNext: " + token);
                                        try {
                                            Log.d(TAG, "onNext: " + URLEncoder.encode("wOCDzlYurJhAm7pPJ/7BqgIJ+QFytaOG7ZbbKkj6IYQ=", "UTF-8"));
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        //        要带前一步返回的cookie进行请求
                                        model.login(mBaseUserInfo.getAccount() + "@stu.edu.cn", mBaseUserInfo.getPassword(), token)
                                                .subscribe(new Observer<String>() {
                                                    @Override
                                                    public void onSubscribe(Disposable d) {

                                                    }

                                                    @Override
                                                    public void onNext(String s) {
                                                        Log.d(TAG, "onNext: " + s);
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
                                                                                        allTables = timeTable.getTable();
                                                                                        for (YiBanTimeTable.TableBean tableBean : allTables) {
                                                                                            Log.d(TAG, "onNext: " + tableBean.getJsName());
                                                                                            Log.d(TAG, "onNext: " + tableBean.getKcName());
                                                                                            Log.d(TAG, "onNext: " + tableBean.getKsName());
                                                                                            Log.d(TAG, "onNext: " + tableBean.getSjName());
                                                                                            Log.d(TAG, "onNext: " + tableBean.getXnxqName());
                                                                                            Log.d(TAG, "onNext: " + tableBean.getKkbKey());
                                                                                        }
                                                                                        //持久化课程
                                                                                        for (int i = 0; i < allTables.size(); i++) {
                                                                                            model.saveYiBanTableToDisk(allTables.get(i));
                                                                                        }
                                                                                        Log.d(TAG, "onNext: " + timeTable);
                                                                                        currentTables = model.filterTables(allTables, currentSemester);
                                                                                        view.showSyllabus(model.convertTablesToLessons(currentTables));
                                                                                    }

                                                                                    @Override
                                                                                    public void onError(Throwable e) {
                                                                                        Log.d(TAG, "onError: ");
                                                                                        e.printStackTrace();
                                                                                    }

                                                                                    @Override
                                                                                    public void onComplete() {
                                                                                        Log.d(TAG, "onComplete: getTimeTable");
                                                                                    }
                                                                                });
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

    public String getCurrentSemester() {
        model.getCurrentSemesterFromDisk()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        currentSemester = s;
                        if (currentSemester == null || currentSemester.equals("") || currentSemester.equals("Non-existent")) {
                            view.showMsg("当前学期未设置 \n 请前往个人页面设置当前学期");
                            return;
                        }
                        init();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        //为什么执行不到呢
                        Log.d(TAG, "onComplete: " + currentSemester);
                    }
                });
        return currentSemester;
    }

}
