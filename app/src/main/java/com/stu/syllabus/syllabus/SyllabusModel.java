package com.stu.syllabus.syllabus;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.bean.BaseUserInfo;
import com.stu.syllabus.bean.ShowLessonBean;
import com.stu.syllabus.bean.YiBanTimeTable;
import com.stu.syllabus.bean.YiBanToken;
import com.stu.syllabus.retrofitApi.YiBanApi;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * yuan
 * 2019/11/12
 **/
public class SyllabusModel implements ISyllabusModel {
    private final String TAG = this.getClass().getSimpleName();

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Retrofit retrofit;
    YiBanApi yiBanApi;
    List<YiBanTimeTable.TableBean> tableBeanList;

    public SyllabusModel(DataBaseHelper dataBase, Retrofit retrofit) {
        super();
        this.dataBaseHelper = dataBase;
        this.retrofit = retrofit;
        yiBanApi = retrofit.create(YiBanApi.class);
        tableBeanList = new LinkedList<>();
    }

    @Override
    public Observable<BaseUserInfo> getUserInfoFromDisk() {
        return Observable.create(new ObservableOnSubscribe<BaseUserInfo>() {
            @Override
            public void subscribe(ObservableEmitter<BaseUserInfo> emitter) throws Exception {
                String account = null;
                String password = null;
                sqLiteDatabase = dataBaseHelper.getReadableDatabase();
                String sql = "select * from user_base_info";
                Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
                while (cursor.moveToNext()) {
                    account = cursor.getString(cursor.getColumnIndex("account"));
                    password = cursor.getString(cursor.getColumnIndex("password"));
                }
                sqLiteDatabase.close();
                BaseUserInfo baseUserInfo = new BaseUserInfo(account, password);
                emitter.onNext(baseUserInfo);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getRequestToken() {
        return yiBanApi.getRequestToken()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> login(String email, String password, String requestToken) {
        return yiBanApi.login(email, password, requestToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<YiBanToken> getToken() {
        return yiBanApi.getToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<YiBanTimeTable> getTimeTable(long vid, long timestamp, String app, String nonce, String token) {
        return yiBanApi.getTimeTable(vid, timestamp, app, nonce, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getCurrentSemesterFromDisk() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String currentSemester = null;
                sqLiteDatabase = dataBaseHelper.getReadableDatabase();
                String sql = "select * from user_info";
                Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
                while (cursor.moveToNext()) {
                    currentSemester = cursor.getString(cursor.getColumnIndex("semester"));
                }
                sqLiteDatabase.close();
                if (currentSemester == null) {
                    currentSemester = "Non-existent";
                }
                emitter.onNext(currentSemester);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void saveYiBanTableToDisk(YiBanTimeTable.TableBean tableBean) {
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("xnxqName", tableBean.xnxqName);
        values.put("kkbKey", tableBean.kkbKey);
        values.put("kcName", tableBean.kcName);
        values.put("jsName", tableBean.jsName);
        values.put("ksName", tableBean.ksName);
        values.put("sjName", tableBean.sjName);
        sqLiteDatabase.insert("yiban_table", null, values);
        sqLiteDatabase.close();
    }

    @Override
    public Observable<List<YiBanTimeTable.TableBean>> getYiBanTableFromDisk() {
        return Observable.create(new ObservableOnSubscribe<List<YiBanTimeTable.TableBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<YiBanTimeTable.TableBean>> emitter) throws Exception {
                String xnxq_name = null;
                String kkb_key = null;
                String kc_name = null;
                String js_name = null;
                String ks_name = null;
                String sj_name = null;
                sqLiteDatabase = dataBaseHelper.getReadableDatabase();
                String sql = "select * from yiban_table";
                Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
                while (cursor.moveToNext()) {
                    xnxq_name = cursor.getString(cursor.getColumnIndex("xnxqName"));
                    kkb_key = cursor.getString(cursor.getColumnIndex("kkbKey"));
                    kc_name = cursor.getString(cursor.getColumnIndex("kcName"));
                    js_name = cursor.getString(cursor.getColumnIndex("jsName"));
                    ks_name = cursor.getString(cursor.getColumnIndex("ksName"));
                    sj_name = cursor.getString(cursor.getColumnIndex("sjName"));
                    if (xnxq_name != null) tableBeanList.add(new YiBanTimeTable.TableBean(xnxq_name, Integer.parseInt(kkb_key), kc_name, js_name, ks_name, sj_name));
                    else break;
                }
                sqLiteDatabase.close();
                emitter.onNext(tableBeanList);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    // TODO: 2019/12/4 对数据的筛选与转换应该在model类进行
    @Override
    public List<YiBanTimeTable.TableBean> filterTables(List<YiBanTimeTable.TableBean> tableBeanList, String currentSemester) {
        List<YiBanTimeTable.TableBean> currentTables = new LinkedList<>();
        Log.d(TAG, "filterTables: all" + tableBeanList.size());
        for (int i = 0; i < tableBeanList.size(); i++) {
            if (tableBeanList.get(i).xnxqName.contains(currentSemester.substring(0, 9)) && tableBeanList.get(i).xnxqName.contains(currentSemester.substring(10))) {
                currentTables.add(tableBeanList.get(i));
            }
        }
        Log.d(TAG, "filterTables: " + currentTables.size());
        return currentTables;
    }

    /**
     * xnxq_name : 2017-2018学年秋季学期
     * kkb_key : 95421
     * kc_name : [ELC1]英语(ELC1)
     * js_name : 苏雪枫
     * ks_name : D座307
     * sj_name : 第1-16周，周二(12节)，周五(34节)
     * sj_name : 第1-16周，周四(单89节)，周五(34节) (单周的情况)
     */

    /**
     * name : [PED1060A]网球
     * id : 112857
     * teacher : 王家君
     * room : 网球场
     * duration : 3 -18
     * days : {"w0":"None","w1":"None","w2":"12","w3":"None","w4":"None","w5":"None","w6":"None"}
     * credit : 0.0
     */

    @Override
    public List<ShowLessonBean> convertTablesToLessons(List<YiBanTimeTable.TableBean> currentTables) {
        List<ShowLessonBean> lessonBeanList = new LinkedList<>();

        for (int i = 0; i < currentTables.size(); i++) {
            String[] time = currentTables.get(i).getSjName().split("，");
            String duration = null;
            ShowLessonBean.DaysBean daysBean = new ShowLessonBean.DaysBean();
            for (int j = 0; j < time.length; j++) {
                if (j == 0) {
                    int indexOfBeginWeek = currentTables.get(i).getSjName().indexOf("第");
                    int indexOfEndWeek = currentTables.get(i).getSjName().indexOf("周");
                    duration = time[0].substring(indexOfBeginWeek + 1, indexOfEndWeek - indexOfBeginWeek);
                } else {
//                    Log.d(TAG, "convertTablesToLessons: " + time[j]);
                    int indexOfSectionBeginBound = time[j].indexOf("(");
                    int indexOfSectionEndBound = time[j].indexOf(")");
//                    Log.d(TAG, "convertTablesToLessons: " + indexOfSectionBound);
//                    Log.d(TAG, "convertTablesToLessons: " + time[j].substring(0, 2));
//                    Log.d(TAG, "convertTablesToLessons:" + time[j] + time[j].charAt(indexOfSectionBeginBound + 1));
                    String first = null;
//                    Log.d(TAG, "convertTablesToLessons: " + time[j] + time[j].charAt(indexOfSectionBeginBound + 1));
                    if (time[j].charAt(indexOfSectionBeginBound + 1) == '单' || time[j].charAt(indexOfSectionBeginBound + 1) == '双') indexOfSectionBeginBound++;
//                    Log.d(TAG, "convertTablesToLessons: " + time[j] + time[j].charAt(indexOfSectionBeginBound + 1));
                    if (time[j].charAt(indexOfSectionBeginBound + 1) == '0') {
                        first = "10";
                    } else if (time[j].charAt(indexOfSectionBeginBound + 1) == 'A') {
                        first = "11";
                    } else if (time[j].charAt(indexOfSectionBeginBound + 1) == 'B') {
                        first = "12";
                    } else if (time[j].charAt(indexOfSectionBeginBound + 1) == 'C') {
                        first = "13";
                    } else first = time[j].charAt(indexOfSectionBeginBound + 1) + "";
                    String last = null;
                    if (time[j].charAt(indexOfSectionEndBound - 2) == '0') {
                        last = "10";
                    } else if (time[j].charAt(indexOfSectionEndBound - 2) == 'A') {
                        last = "11";
                    } else if (time[j].charAt(indexOfSectionEndBound - 2) == 'B') {
                        last = "12";
                    } else if (time[j].charAt(indexOfSectionEndBound - 2) == 'C') {
                        last = "13";
                    } else last = time[j].charAt(indexOfSectionEndBound - 2) + "";
                    Log.d(TAG, "convertTablesToLessons: " + time[j] + time[j].charAt(indexOfSectionBeginBound + 1) + " " + time[j].charAt(indexOfSectionEndBound - 2));
                    switch (time[j].substring(0,2)) {
                        case "周日": daysBean.setW0(first + "-" + last); break;
                        case "周一": daysBean.setW1(first + "-" + last); break;
                        case "周二": daysBean.setW2(first + "-" + last); break;
                        case "周三": daysBean.setW3(first + "-" + last); break;
                        case "周四": daysBean.setW4(first + "-" + last); break;
                        case "周五": daysBean.setW5(first + "-" + last); break;
                        case "周六": daysBean.setW6(first + "-" + last); break;
                    }
                }
            }
            ShowLessonBean showLessonBean = new ShowLessonBean(currentTables.get(i).kcName, String.valueOf(currentTables.get(i).getKkbKey()), currentTables.get(i).getJsName(), currentTables.get(i).getJsName(), duration, daysBean, "0");
            lessonBeanList.add(showLessonBean);
        }
        return lessonBeanList;
    }
}
