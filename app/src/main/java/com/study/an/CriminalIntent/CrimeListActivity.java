package com.study.an.CriminalIntent;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.study.an.BaseUnits.SingleFragmentActivity;

/**
 * Created by admin on 2016/1/22.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new CrimeListFragment();
    }

}
