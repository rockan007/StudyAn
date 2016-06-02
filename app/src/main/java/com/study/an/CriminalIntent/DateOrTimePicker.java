package com.study.an.CriminalIntent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

import com.study.an.all.R;


/**
 * Created by admin on 2016/1/27.
 */
public class DateOrTimePicker extends DialogFragment implements View.OnClickListener {
    TextView mChangeDate;
    TextView mChangeTime;
    private int mChoice=0;
    public static final int CHOOSE_DATE=1;
    public static final int CHOOSE_TIME=2;
    public static final String CHOSE_TYPE="chose_type";
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(getArguments()!=null){
        mChoice=(int)getArguments().getSerializable(CHOSE_TYPE);
        }else {
           mChoice=0;
        }

       View view=getActivity().getLayoutInflater().inflate(R.layout.time_date,null);
        mChangeDate=(TextView)view.findViewById(R.id.change_date);
        mChangeTime=(TextView)view.findViewById(R.id.change_time);
        mChangeTime.setOnClickListener(this);
        mChangeDate.setOnClickListener(this);
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.choose_change)
                .setView(view)
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .create();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_date:
                mChoice=CHOOSE_DATE;
                getArguments().putSerializable(CHOSE_TYPE,mChoice);
               sendResult(Activity.RESULT_OK);
                dismiss();
                break;
            case R.id.change_time:
                mChoice=CHOOSE_TIME;
                getArguments().putSerializable(CHOSE_TYPE,mChoice);
                sendResult(Activity.RESULT_OK);
                dismiss();
                break;
            default:break;
        }
    }
    public static DateOrTimePicker newInstance(int choice){
        Bundle args=new Bundle();
        args.putSerializable(CHOSE_TYPE, choice);
        DateOrTimePicker dateOrTimePicker=new DateOrTimePicker();
        dateOrTimePicker.setArguments(args);
        return dateOrTimePicker;
    }
    private void sendResult(int resultCode){
            if(getArguments()==null){
                return;
            }
        Intent intent=new Intent();
        intent.putExtra(CHOSE_TYPE,mChoice);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }
}
