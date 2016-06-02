package com.study.an.ceshi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.study.an.all.R;


/**
 * Created by admin on 2016/3/18.
 */
public class Tomorrow extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_moon_fragment);
    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        Intent i=new Intent();
        i.putExtra("s",55);
        setResult(RESULT_OK,i);
        finish();
        super.onBackPressed();
    }
}
