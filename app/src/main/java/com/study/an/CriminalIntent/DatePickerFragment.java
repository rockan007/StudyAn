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
import android.widget.DatePicker;


import com.study.an.all.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by admin on 2016/1/26.
 */
public class DatePickerFragment extends DialogFragment {
    public static final String EXTRA_DATE = "com.study.an.CriminalIntent.DatePickerFragment.date";
    private Date mDate;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date) getArguments().getSerializable(EXTRA_DATE);
        Calendar mCalendar = Calendar.getInstance();
        if (mDate != null) {
            mCalendar.setTime(mDate);
        } else {
            mCalendar.setTime(new Date());
        }
        int year = mCalendar.get(Calendar.YEAR);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        int month = mCalendar.get(Calendar.MONTH);
        final int HH = mCalendar.get(Calendar.HOUR_OF_DAY);
        final int mm = mCalendar.get(Calendar.MINUTE);
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
        DatePicker datePicker = (DatePicker) v.findViewById(R.id.dialog_date_datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDate = new GregorianCalendar(year, monthOfYear, dayOfMonth, HH, mm).getTime();
                getArguments().putSerializable(EXTRA_DATE, mDate);
            }
        });
//        return super.onCreateDialog(savedInstanceState);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(R.string.date_picker_OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .create();
    }

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(args);
        return datePickerFragment;
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent i = new Intent();
        i.putExtra(EXTRA_DATE, mDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }
}
