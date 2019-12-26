package com.stu.syllabus.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.stu.syllabus.R;
import com.stu.syllabus.adapter.HomeRecyclerViewAdapter;
import com.stu.syllabus.home.wirelessData.WirelessActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeWirelessFragment  extends Fragment {

    @BindView(R.id.recyclerView_wireless)
    RecyclerView mRecyclerView;

    public static HomeWirelessFragment newInstance(int position) {
        return new HomeWirelessFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_wireless, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        Log.d("HomeWirelessFragment---", "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("HomeWirelessFragment---", "onStart");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));//getActivity?
        mRecyclerView.setHasFixedSize(true);

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

        List<Object> items = new ArrayList<>();
        items.add("流量监控");
        HomeRecyclerViewAdapter rvAdapter = new HomeRecyclerViewAdapter(items,mRecyclerView);

        rvAdapter.setOnItemClickListener(new HomeRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, String data) {
                if(data.equals("流量监控")){
                    Intent intent = new Intent(getActivity(), WirelessActivity.class);
                    startActivity(intent);
                }
            }
        });
        mRecyclerView.setAdapter(rvAdapter);
    }
}
