package com.study.an.TrueFalse;

import android.support.v4.app.Fragment;
import com.study.an.BaseUnits.SingleFragmentActivity;

/**
 * Created by admin on 2016/1/20.
 */
public class TrueFalseActivity extends SingleFragmentActivity{
    @Override
    public Fragment createFragment(){
        return new TrueFalseFragment();
    }
}
