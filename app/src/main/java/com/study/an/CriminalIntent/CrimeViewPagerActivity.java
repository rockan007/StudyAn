package com.study.an.CriminalIntent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.study.an.all.R;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by admin on 2016/1/22.
 */
public class CrimeViewPagerActivity extends AppCompatActivity {
    ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.crime_view_pager);
        mToolbar=(Toolbar)findViewById(R.id.tool_bar);
       mViewPager=(ViewPager)findViewById(R.id.view_pager);
       setSupportActionBar(mToolbar);
        mCrimes=CrimeLab.get(this).getCrimes();
        FragmentManager fm=getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime c=mCrimes.get(position);
                CrimeFragment crimeFragment=CrimeFragment.newInstance(c.getId());
                return crimeFragment;
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        UUID mId=(UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for(int i=0;i<mCrimes.size();i++){
            if(mCrimes.get(i).getId().equals(mId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Crime c=mCrimes.get(position);
                if(c.getTitle()!=null){
                  setTitle(c.getTitle());
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
