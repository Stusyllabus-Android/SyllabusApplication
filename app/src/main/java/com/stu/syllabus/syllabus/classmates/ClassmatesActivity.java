package com.stu.syllabus.syllabus.classmates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.stu.syllabus.R;
import com.stu.syllabus.adapter.ClassmatesInfoAdapter;
import com.stu.syllabus.base.BaseActivity;
import com.stu.syllabus.bean.ClassmateInfo;
import com.stu.syllabus.util.ToastUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ClassmatesActivity extends BaseActivity {

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.classmateRecyclerView)
    RecyclerView classmateRecycleView;

    Document document;
    List<ClassmateInfo> classmateInfoList;

    public static Intent getIntent(Context context, long classID) {
        Intent intent = new Intent(context, ClassmatesActivity.class);
        intent.putExtra("classID", classID);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        classmateInfoList = new LinkedList<>();
        getClassmatesDataFromCredit(intent.getLongExtra("classID", 0));
        Log.d(TAG, "onCreate: " + intent.getLongExtra("classID", 0));
    }

    @Override
    protected void init() {
        super.init();
        setupTitleBar(toolbar);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_classmates;
    }

    public void getClassmatesDataFromCredit(long classID) {
        classmateInfoList.clear();
        GetClassmatesMethod.getInstance().getClassmatesInfo(classID)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        /**
                         * <tr class="gridview_row">
                         * <td>2016401088</td>
                         * <td>刘美琪</td>
                         * <td>女</td>
                         * <td>工学大类(2016) </td>
                         * <td align="center">0</td>
                         * <td>&nbsp;</td>
                         * </tr>
                         */

                        document = Jsoup.parseBodyFragment(s);
                        Elements allTdEle = document.getElementsByTag("td");
                        int numOfStudents = (allTdEle.size() - 17) / 6;
                        Log.d(TAG, "onNext: " + numOfStudents);
                        int i = 0, j = 18;
                        //第19个数据开始都是有价值的数据，每个六个数据项（为同一人）new 一个对象
                        while (i < numOfStudents) {
                            ClassmateInfo studentInfo = new ClassmateInfo();
                            while (j < allTdEle.size() && (j - 17) % 6 != 0 ) {
                                if ((j - 17) % 6 == 1) studentInfo.setNumber(allTdEle.get(j).text());
                                if ((j - 17) % 6 == 2) studentInfo.setName(allTdEle.get(j).text());
                                if ((j - 17) % 6 == 3) studentInfo.setGender(allTdEle.get(j).text());
                                if ((j - 17) % 6 == 4) studentInfo.setMajor(allTdEle.get(j).text());
                                j++;
                            }
                            j++;
                            classmateInfoList.add(studentInfo);
                            i++;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(ClassmatesActivity.this, "请连接校园网查看");
                    }

                    @Override
                    public void onComplete() {
                        showData(classmateInfoList);
                    }
                });
    }

    private void showData(List<ClassmateInfo> classmateInfoList) {
         classmateRecycleView.setAdapter(new ClassmatesInfoAdapter(this, classmateInfoList));
    }
}
