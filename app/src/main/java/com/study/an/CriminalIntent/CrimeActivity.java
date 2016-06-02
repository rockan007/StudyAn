package com.study.an.CriminalIntent;

import android.support.v4.app.Fragment;

import com.study.an.BaseUnits.SingleFragmentActivity;

import java.util.UUID;

/**
 * Created by admin on 2016/1/22.
 */
public class CrimeActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        UUID mId=(UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(mId);
    }
}
