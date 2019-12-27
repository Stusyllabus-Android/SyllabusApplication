package com.stu.syllabus.home.fragment;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeLibraryFragment extends Fragment {

    private static final boolean GRID_LAYOUT = false;

    @BindView(R.id.recyclerView_library)
    RecyclerView mRecyclerView;

    public static HomeLibraryFragment newInstance(int position) {
        return new HomeLibraryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_library, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

        List<Object> items = new ArrayList<>();
        items.add("图书检索");
        items.add("研讨室预约");
        mRecyclerView.setAdapter(new HomeRecyclerViewAdapter(items, mRecyclerView));

    }
}
