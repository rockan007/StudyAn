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
import android.widget.TimePicker;

import com.study.an.all.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by admin on 2016/1/27.
 */
public class TimePickerFragment extends DialogFragment {
    TimePicker mTimePicker;
    View mView;
    Date mDate;
    Date mTime;
    public static final String EXTRA_TIME="time";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate=(Date)getArguments().getSerializable(EXTRA_TIME);
        Calendar mCalendar=Calendar.getInstance();
        if(mDate!=null){
            mCalendar.setTime(mDate);
        }else {
            mCalendar.setTime(new Date());
        }
        final int year=mCalendar.get(Calendar.YEAR);
        final int month=mCalendar.get(Calendar.MONTH);
        final int day=mCalendar.get(Calendar.DAY_OF_MONTH);
        int HH=mCalendar.get(Calendar.HOUR_OF_DAY);
        int mm=mCalendar.get(Calendar.MINUTE);
        View view=getActivity().getLayoutInflater().inflate(R.layout.dialog_time, null);
        TimePicker timePicker=(TimePicker)view.findViewById(R.id.dialog_time_timePicker);
        timePicker.setCurrentHour(HH);
        timePicker.setCurrentMinute(mm);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mDate=new GregorianCalendar(year,month,day,hourOfDay,minute).getTime();
                getArguments().putSerializable(EXTRA_TIME,mDate);
            }
        });
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.crime_time)
                .setPositiveButton(R.string.date_picker_OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            sendResult(Activity.RESULT_OK);
                    }
                }).create();
    }
    public static TimePickerFragment newInstance(Date mDate){
        Bundle args=new Bundle();
        args.putSerializable(EXTRA_TIME, mDate);
        TimePickerFragment timePickerFragment=new TimePickerFragment();
        timePickerFragment.setArguments(args);
        return timePickerFragment;
    }
    private void sendResult(int resultCode){
        if(getTargetFragment()==null){
            return;
        }
        Intent i= new Intent();
        i.putExtra(EXTRA_TIME,mDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }
}
