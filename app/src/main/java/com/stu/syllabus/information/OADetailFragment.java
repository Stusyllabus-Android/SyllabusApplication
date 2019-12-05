//package com.stu.syllabus.information;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.WebView;
//
//import com.stu.syllabus.AppComponent;
//import com.stu.syllabus.R;
//import com.stu.syllabus.base.BaseFragment;
//import com.stu.syllabus.bean.OADetail;
//
//import java.util.List;
//
//import javax.inject.Inject;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.widget.Toolbar;
//import butterknife.BindView;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class OADetailFragment extends BaseFragment implements OADetailContract.view {
//    @BindView(R.id.oAWebView)
//    WebView mOAWebView;
//    @BindView(R.id.toolBar)
//    Toolbar toolbar;
//    private AppComponent appComponent;
//    Call<OADetail> oadetail;
//    @Inject
//    OADetailPresenter oadetailPresenter;
//    String id;
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        appComponent = this.getComponent(AppComponent.class);
//
//    }
//    @Nullable
//    public View onCreatView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        DaggerOADetailComponent.builder()
//                .appComponent(appComponent)
//                .oADetailModule(new OADetailModule(this))
//                .build()
//                .inject(this);
//
//        oadetailPresenter.init();
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }
//
//    @Override
//    public int getContentView() {
//        return R.layout.fragment_oadetail;
//    }
//
//    @Override
//    public void setToolBarTitle() {
//        toolbar.setTitle("办公自动化");
//        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
//    }
//    @Override
//    public void init() {
//        super.init();
//
//    }
//    @Override
//    public void showDetail(Call<OADetail> oadetail) {
// oadetail.enqueue(new Callback<OADetail>() {
//                @Override
//                public void onResponse(Call<OADetail> call, Response<OADetail> response) {
//                    try{
//                        Thread.sleep(1000);
//                        OADetail OAdetail = response.body();
//                        String message=OAdetail.title;
//                        Log.i("text",message);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<OADetail> call, Throwable t) {
//                    Log.i("text","fail");
//                }
//    });
//    }
//
//}
