package com.study.an.selfDefine;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;

/**
 * Created by admin on 2016/1/23.
 */
public class CancelDatePikerDialog extends DatePickerDialog {
    public CancelDatePikerDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
    }



    public CancelDatePikerDialog(Context context, int theme, OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
        super(context, theme, listener, year, monthOfYear, dayOfMonth);
    }

    public void setNegtiveButton() {
        setButton(BUTTON_POSITIVE, "确认", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
    }
    public void setPositiveButton(){
        setButton(BUTTON_POSITIVE, "取消", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });
    }


    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        super.onDateChanged(view, year, month, day);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onClick(dialog, which);
    }

    @Override
    public DatePicker getDatePicker() {
        return super.getDatePicker();
    }

    @Override
    public void updateDate(int year, int monthOfYear, int dayOfMonth) {
        super.updateDate(year, monthOfYear, dayOfMonth);
    }

    @Override
    public Bundle onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
