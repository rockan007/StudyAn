package com.study.an.BaseUnits;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.study.an.EventBusUtils.EventBusUtil;
import com.study.an.all.R;


/**
 * Created by admin on 2016/1/20.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private Toolbar mToolbar;
    SwipeRefreshLayout mRefreshLayout;

    public abstract Fragment createFragment();

    private boolean isRefresh = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EventBusUtil.register(this);
        setContentView(R.layout.fragment_home);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mRefreshLayout.setOnRefreshListener(this);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregister(this);
    }

    @Override
    public void onRefresh() {
//        ArrayList post = new ArrayList();
//        post.add(1);
//        post.add(mRefreshLayout);
//        EventBusUtil.post(post);
//        if(!isRefresh){
//            isRefresh=true;
    }
}

