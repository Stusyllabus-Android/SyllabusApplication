package com.stu.syllabus.syllabus.syllabus;

import com.stu.syllabus.bean.BaseUserInfo;
import com.stu.syllabus.bean.ShowLessonBean;
import com.stu.syllabus.bean.YiBanTimeTable;
import com.stu.syllabus.bean.YiBanToken;

import java.util.List;

import io.reactivex.Observable;

/**
 * yuan
 * 2019/11/12
 **/
public interface ISyllabusModel {

    Observable<BaseUserInfo> getUserInfoFromDisk();

    Observable<String> getRequestToken();

    Observable<String> login(String email, String password, String requestToken);

    Observable<YiBanToken> getToken();

    Observable<YiBanTimeTable> getTimeTable(long vid, long timestamp, String app, String nonce, String token);

    Observable<String> getCurrentSemesterFromDisk();

    void saveYiBanTableToDisk(YiBanTimeTable.TableBean tableBean);

    Observable<List<YiBanTimeTable.TableBean>> getYiBanTableFromDisk();

    List<YiBanTimeTable.TableBean> filterTables(List<YiBanTimeTable.TableBean> tableBeanList, String currentSemester);

    List<ShowLessonBean> convertTablesToLessons(List<YiBanTimeTable.TableBean> currentTables);

}
