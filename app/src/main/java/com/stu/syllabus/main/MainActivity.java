package com.stu.syllabus.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stu.syllabus.R;
import com.stu.syllabus.adapter.MainViewPagerAdapter;
import com.stu.syllabus.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * yuan
 * 2019/10/22
 **/
public class MainActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new InfoFragment());
        fragmentList.add(new SyllabusFragment());
        fragmentList.add(new ContactFragment());
        fragmentList.add(new PersonFragment());
        initView();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    private void initView() {
        initViewPager();
        initBottomNavigationView();
    }

    private void initViewPager() {
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                switch (position) {
                    case 0: break;
                    case 1: ;
                    case 2: ;
                    case 3: ;
                    case 4: ;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.information:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.syllabus:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.contact:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.person:
                        viewPager.setCurrentItem(4);
                        break;
                }
                return false;
            }
        });
    }

}
