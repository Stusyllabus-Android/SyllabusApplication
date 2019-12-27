package com.stu.syllabus.main.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stu.syllabus.R;
import com.stu.syllabus.adapter.ContractRVAdapter;
import com.stu.syllabus.base.BaseFragment;
import com.stu.syllabus.bean.ContractInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * yuan
 * 2019/10/22
 **/
public class ContactFragment extends BaseFragment {
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.rv_contact)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_contact;
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.contact);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//getActivity?
        List<ContractInfo> contractInfos = getContents();
        ContractRVAdapter contractRVAdapter = new ContractRVAdapter(contractInfos,mRecyclerView);
        Log.d("ContractFragment:",contractInfos.toString());
        mRecyclerView.setAdapter(contractRVAdapter);
    }

    private List<ContractInfo> getContents(){
        List<ContractInfo> contractInfos = new ArrayList<>();
        contractInfos.add(new ContractInfo("春困","2019-12-20 15:25:56","呼叫请回答", 4, 0));
        contractInfos.add(new ContractInfo("夏倦","2019-12-20 15:26:15","呼叫请回答", 2, 0));
        contractInfos.add(new ContractInfo("秋乏","2019-12-20 15:30:25","呼叫请回答", 4, 0));
        contractInfos.add(new ContractInfo("冬眠","2019-12-20 15:32:40","呼叫请回答", 0, 0));
        return contractInfos;
    }
}
