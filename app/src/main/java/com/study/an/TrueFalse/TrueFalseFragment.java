package com.study.an.TrueFalse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.study.an.all.R;


/**
 * Created by admin on 2016/1/20.
 */
public class TrueFalseFragment extends Fragment implements View.OnClickListener{
    View view;
    TextView mQuestionText;
    Button mNextButton;
    Button mLastButton;
    Button mTrue,mFalse;
    Button mCheat;
    boolean isCheat=false;
    TrueFalse[] mTrueFalses;
    int current=0;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup parent,Bundle savedInstanceState){
        view=inflater.inflate(R.layout.true_false_fragment,parent,false);
        setTrueFalses();
        initViews();
        return view;
    }
    private TrueFalse[] setTrueFalses(){
        mTrueFalses=new TrueFalse[]{new TrueFalse(R.string.question_animal,false),
                 new TrueFalse(R.string.question_river,true),
                new TrueFalse(R.string.question_food,true)};
        return mTrueFalses;

    }
    private void initViews(){
        mQuestionText=(TextView)view.findViewById(R.id.question_text);
        updateQuestion();
        mLastButton=(Button)view.findViewById(R.id.last_question);
        mNextButton=(Button)view.findViewById(R.id.next_question);
        mTrue=(Button)view.findViewById(R.id.question_isTrue);
        mFalse=(Button)view.findViewById(R.id.question_isFalse);
        mCheat=(Button)view.findViewById(R.id.cheat_forAnswer);
        mCheat.setOnClickListener(this);
        mTrue.setOnClickListener(this);
        mFalse.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mLastButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.last_question:
                isCheat=false;
                current=(current-1+mTrueFalses.length)%mTrueFalses.length;
               updateQuestion();
                break;
            case R.id.next_question:
                isCheat=false;
                current=(current+1)%mTrueFalses.length;
               updateQuestion();
                break;
            case R.id.question_isTrue:
                isAnswerTrue(true);
                break;
            case R.id.question_isFalse:
                isAnswerTrue(false);
                break;
            case R.id.cheat_forAnswer:
                Intent intent=new Intent(getActivity(),CheatActivity.class);
                intent.putExtra("isAnswerTrue",mTrueFalses[current].isTrue());
                startActivityForResult(intent, 0);
                break;
            default:
                break;
        }
    }
    private void isAnswerTrue(boolean userPressedTrue){
        boolean isQuestionTrue =mTrueFalses[current].isTrue();
        int messageId=0;
        if(isCheat){
            messageId=R.string.you_are_cheater;
        }else {
            if(userPressedTrue==isQuestionTrue){
               messageId=R.string.answer_true;
            }else {
                messageId=R.string.answer_false;
            }

        }
        Toast.makeText(getActivity(),messageId,Toast.LENGTH_SHORT).show();
    }
    private void updateQuestion(){
        int question=mTrueFalses[current].getQuestion();
        mQuestionText.setText(question);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      if(data!=null){
          isCheat=data.getBooleanExtra("isAnswerShow",false);
      }


    }
}
