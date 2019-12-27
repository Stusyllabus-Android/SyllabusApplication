package com.stu.syllabus.main.fragment;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.R;
import com.stu.syllabus.adapter.HomeViewPagerAdapter;
import com.stu.syllabus.base.BaseFragment;
import com.stu.syllabus.home.DaggerHomeComponent;
import com.stu.syllabus.home.GlideImageLoader;
import com.stu.syllabus.home.HomeContract;
import com.stu.syllabus.home.HomeModule;
import com.stu.syllabus.home.HomePresenter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;

/**
 * @author wwshe
 * 2019/11/28
 **/
public class HomeFragment extends BaseFragment implements HomeContract.view {

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.pagerTabStrip)
    PagerTabStrip pagerTabStrip;

    @Inject
    HomePresenter homePresenter;

    private AppComponent appComponent;

    private HomeViewPagerAdapter homeViewPagerAdapter;
    private FragmentManager manager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = this.getComponent(AppComponent.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerHomeComponent.builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public int getContentView() {
        manager = this.getFragmentManager();
        return R.layout.fragment_home;
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.home);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
    }

    @Override
    public void init() {
        homePresenter.init();
    }

    //ViewPager
    @Override
    public void setAdapterForViewPager() {
        homeViewPagerAdapter = new HomeViewPagerAdapter(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(homeViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(mViewPager.getAdapter().getCount());
        pagerTabStrip.setTextSpacing(0);
        pagerTabStrip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        pagerTabStrip.setTabIndicatorColor(0xffffff);

    }

    //轮播图
    @Override
    public void setBannerImages(){
        List<Integer> images=new ArrayList<>();
        images.add(R.drawable.pic_school3);
        images.add(R.drawable.pic_school1);
        images.add(R.drawable.pic_school2);

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

}
