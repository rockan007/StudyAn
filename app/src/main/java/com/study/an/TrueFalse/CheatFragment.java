package com.study.an.TrueFalse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.study.an.all.R;

/**
 * Created by admin on 2016/1/22.
 */
public class CheatFragment extends Fragment implements View.OnClickListener{
    View mView;
    TextView mAnswer;
    Button mShowAnswer;
    boolean isAnswerTrue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnswerShowResult(false);
        isAnswerTrue=getActivity().getIntent().getBooleanExtra("isAnswerTrue",false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       mView=inflater.inflate(R.layout.cheat_fragment,container,false);
        initViews();
        return mView;
    }
    private void initViews(){
        mAnswer=(TextView)mView.findViewById(R.id.question_answer);
        mShowAnswer=(Button)mView.findViewById(R.id.show_answer);
        mShowAnswer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.show_answer:
                setAnswerShowResult(true);
                mAnswer.setText(String.valueOf(isAnswerTrue));
                break;
            default:
                break;
        }
    }
    private void setAnswerShowResult(boolean isAnswerShow){
        Intent data=new Intent();
        data.putExtra("isAnswerShow", isAnswerShow);
        getActivity().setResult(Activity.RESULT_OK,data);

    }
}
