package com.study.an.TrueFalse;

import android.support.v4.app.Fragment;

import com.study.an.BaseUnits.SingleFragmentActivity;

/**
 * Created by admin on 2016/1/22.
 */
public class CheatActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new CheatFragment();
    }
}
