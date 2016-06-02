package com.study.an.WebList;

import android.support.v4.app.Fragment;

import com.study.an.BaseUnits.SingleFragmentActivity;

import java.util.UUID;

/**
 * Created by admin on 2016/1/28.
 */
public class WebActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new WebFragment();
    }
}
