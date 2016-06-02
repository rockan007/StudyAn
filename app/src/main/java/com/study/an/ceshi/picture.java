package com.study.an.ceshi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.study.an.all.R;

/**
 * Created by admin on 2016/3/5.
 */
public class picture extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {
//    TextView textView;
RadioGroup radioGroup;
    Button textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture);
//        textView=(TextView)findViewById(R.id.text);
        textView=(Button)findViewById(R.id.text);
         radioGroup=(RadioGroup)findViewById(R.id.radio_group);
        RadioGroup s=(RadioGroup)findViewById(R.id.group);
        s.setOnCheckedChangeListener(this);
        textView.setOnClickListener(this);

        for (int i = 0; i < 5; i++) {
            RadioButton rbtn = new RadioButton(this);
            rbtn.setText("btn" + i);
            radioGroup.addView(rbtn);
        }
        RadioButton radioButton = (RadioButton) radioGroup.getChildAt(2);
        radioButton.setChecked(true);
        radioGroup.setOnCheckedChangeListener(this);
//        new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                Log.d("qwe", "qwe---" + radioGroup.getCheckedRadioButtonId());
//                Log.d("rb","rb_"+i);
//            }
//        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Log.d("rb","rb_"+checkedId);
        switch (checkedId){
            case R.id.r_1:
                break;
            default:break;
        }
    }

    @Override
    protected void onPause() {
        radioGroup.removeAllViews();
        radioGroup.clearCheck();
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case RESULT_OK:
                int  s=data.getIntExtra("s",0);
                Log.d("我疯了",s+"");
                break;
        }
    }

    @Override
    public void onClick(View v) {
         Intent intent=new Intent(this,Tomorrow.class);
        startActivityForResult(intent,0);
//        }
    }
}
