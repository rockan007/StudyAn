package com.study.an.MediaPlayer;

import android.support.v4.app.Fragment;

import com.study.an.BaseUnits.SingleFragmentActivity;

/**
 * Created by admin on 2016/1/25.
 */
public class HelloMoonActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new HelloMoonFragment();
    }
}
