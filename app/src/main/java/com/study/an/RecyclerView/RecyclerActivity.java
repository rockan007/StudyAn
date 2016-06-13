package com.study.an.RecyclerView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.study.an.all.R;
import java.util.ArrayList;

/**
 * RecyclerView
 * Created by admin on 2016/6/12.
 */
public class RecyclerActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }
    private void initViews(){
        setContentView(R.layout.activity_recycle);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Lostbug.com");
        setSupportActionBar(toolbar);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //设置布局管理器
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)) ;//ListView
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//GridView
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//瀑布流
        //设置Item添加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
//        mRecyclerView.addItemDecoration(RecyclerView.ItemDecoration);
        RecyclerAdapter mRecyclerAdapter;
        mRecyclerView.setAdapter(mRecyclerAdapter=new RecyclerAdapter(this));
        ArrayList<String> names=new ArrayList<>();
        names.add("Java");
        names.add("Android");
        names.add("C++");
        names.add("PHP");
        names.add("Html");
        names.add("Spring");
        mRecyclerAdapter.setData(names);
        mRecyclerAdapter.notifyDataSetChanged();
    }
}
