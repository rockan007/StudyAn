package com.study.an.Expandable;

import android.support.v4.app.Fragment;

import com.study.an.BaseUnits.SingleFragmentActivity;

/**
 * Created by admin on 2016/1/21.
 */
public class MainActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment(){
        return new MainFragment();
    }
}
