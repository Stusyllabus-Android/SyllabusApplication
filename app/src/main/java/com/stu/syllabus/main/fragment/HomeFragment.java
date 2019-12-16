package com.stu.syllabus.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
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
    @BindView(R.id.materialViewPager)
    MaterialViewPager mViewPager;

    @Inject
    HomePresenter homePresenter;

    private AppComponent appComponent;

    private HomeViewPagerAdapter homeViewPagerAdapter;


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
        homeViewPagerAdapter = new HomeViewPagerAdapter(getFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.getToolbar().setVisibility(View.INVISIBLE);
        mViewPager.getViewPager().setAdapter(homeViewPagerAdapter);

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
    }

    //轮播图
    @Override
    public void setBannerImages(){
        List<Integer> images=new ArrayList<>();
        images.add(R.drawable.three_pig);
        images.add(R.drawable.three_cute);
        images.add(R.drawable.three_dogs);

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

}
