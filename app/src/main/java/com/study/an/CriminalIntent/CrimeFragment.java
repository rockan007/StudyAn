package com.study.an.CriminalIntent;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.study.an.all.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by admin on 2016/1/22.
 */
public class CrimeFragment extends Fragment implements TextWatcher,CompoundButton.OnCheckedChangeListener,View.OnClickListener{
    EditText mTitleET;
    Button mDateButton;
    Button mTimeButton;
    Button mTimeDateButton;
    private View mView;
    private Crime mCrime;
   private SimpleDateFormat mDateFormat;
    private SimpleDateFormat mTimeFormat;
    private SimpleDateFormat mTimeDateFormat;
    private CheckBox mSolved;
    boolean isSolved;
    Date date;
    String date0;
    public static final String EXTRA_CRIME_ID="com.study.an.criminalIntent.crime_id";
    public static final String DIALOG_DATE="date";
    public static final String DIALOG_TIME="time";
    public static final String DIALOG_DATE_TIME="date_time";
    public static final int RESULT_DATE=0;
    public static final int REQUEST_TIME=1;
    public static final int REQUEST_DATE_TIME=2;

    /**生命周期外无法获取资源*/
//    private String a=getResources().getString(R.string.editText_hint);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mCrime=new Crime();
        mDateFormat=new SimpleDateFormat("EE MM-dd-yyyy",Locale.getDefault());
        mTimeFormat=new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
        mTimeDateFormat=new SimpleDateFormat("HH:mm:ss EE MM-dd-yyyy",Locale.getDefault());
        setHasOptionsMenu(true);
        if(getArguments()!=null){
        UUID mId=(UUID)getArguments().getSerializable(EXTRA_CRIME_ID);
            mCrime=CrimeLab.get(getActivity()).getCrime(mId);
//            if(mCrime.getTitle()!=null&&mCrime.getTitle().length()>0){
//                getActivity().setTitle(mCrime.getTitle());
//                Log.e("Title",mCrime.getTitle());
//            }else {
//                getActivity().setTitle(R.string.new_crime);
//            }
        }else {
            mCrime=new Crime();
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.crime_fragment,container,false);
        if(Build.VERSION.SDK_INT>=11){
//            if(NavUtils.getParentActivityName(getActivity())!=null){
//                ((SingleFragmentActivity)getActivity()).getToolbar().setDis
//            }
        }
        initViews();

        return mView;
    }
    private void initViews(){
        mTitleET=(EditText)mView.findViewById(R.id.crime_title);
        mTitleET.setText(mCrime.getTitle());
        mDateButton=(Button)mView.findViewById(R.id.crime_date);
        updateDate(mDateButton,mDateFormat);
        mTimeButton=(Button)mView.findViewById(R.id.crime_time);
        updateDate(mTimeButton,mTimeFormat);
        mTimeDateButton=(Button)mView.findViewById(R.id.crime_date_time);
        updateDate(mTimeDateButton,mTimeDateFormat);
        mTitleET.addTextChangedListener(this);
        mDateButton.setText(mDateFormat.format(mCrime.getDate()));
        date=mCrime.getDate();
        mSolved=(CheckBox)mView.findViewById(R.id.crime_solved);
        mSolved.setChecked(mCrime.isSolved());
        mSolved.setOnCheckedChangeListener(this);
        mDateButton.setOnClickListener(this);
        mTimeButton.setOnClickListener(this);
        mTimeDateButton.setOnClickListener(this);


    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        mCrime.setTitle(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        isSolved=b;
        mCrime.setIsSolved(isSolved);
    }
    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args=new Bundle();
        args.putSerializable(EXTRA_CRIME_ID,crimeId);
        CrimeFragment crimeFragment=new CrimeFragment();
        crimeFragment.setArguments(args);
        return crimeFragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.crime_date:
                FragmentManager fm=getActivity().getSupportFragmentManager();
                DatePickerFragment datePickerFragment=DatePickerFragment.newInstance(mCrime.getDate());
                datePickerFragment.setTargetFragment(CrimeFragment.this,RESULT_DATE);
                datePickerFragment.show(fm, DIALOG_DATE);
//              final Calendar calendar=Calendar.getInstance();
//                calendar.setTime(date);
//                int year=calendar.get(Calendar.YEAR);
//                int month=calendar.get(Calendar.MONTH);
//                int day=calendar.get(Calendar.DAY_OF_MONTH);
//
//                mDatePickerDialog=new DatePickerDialog(getActivity(),android.R.style.Theme_Material_Dialog_Alert, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                        date0=i+"-"+(i1+1)+"-"+i2;
//
//
//                    }
//                }, year,month,day);
//                mDatePickerDialog.setButton(Dialog.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        DateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//                        try {
//                            Date date1=simpleDateFormat.parse(date0);
//                            mCrime.setDate(date1);
//                        }catch (Exception e){
//                            Log.e("Exception",e.toString());
//                        }
//                        mDateButton.setText(date0);
//
//                    }
//                });
//                mDatePickerDialog.setButton(Dialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                mDatePickerDialog.show();
                break;
            case R.id.crime_time:
                FragmentManager fm1=getActivity().getSupportFragmentManager();
                TimePickerFragment timePickerFragment=TimePickerFragment.newInstance(mCrime.getDate());
                timePickerFragment.setTargetFragment(CrimeFragment.this,REQUEST_TIME);
                timePickerFragment.show(fm1,DIALOG_TIME);
                break;
            case R.id.crime_date_time:
                FragmentManager fm2=getActivity().getSupportFragmentManager();
                DateOrTimePicker dateOrTimePicker=DateOrTimePicker.newInstance(0);
                dateOrTimePicker.setTargetFragment(CrimeFragment.this,REQUEST_DATE_TIME);
                dateOrTimePicker.show(fm2,DIALOG_DATE_TIME);
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(NavUtils.getParentActivityName(getActivity())!=null){
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        switch (requestCode){
            case RESULT_DATE:
                Date date=(Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
                mCrime.setDate(date);
                updateDate(mDateButton, mDateFormat);
                updateDate(mTimeDateButton,mTimeDateFormat);
                break;
            case REQUEST_TIME:
                Date date1=(Date)data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
                mCrime.setDate(date1);
                updateDate(mTimeButton, mTimeFormat);
                updateDate(mTimeDateButton,mTimeDateFormat);
                break;
            case REQUEST_DATE_TIME:
                int choseType=(int)data.getSerializableExtra(DateOrTimePicker.CHOSE_TYPE);
                switch (choseType){
                    case 0:
                        break;
                    case 1:
                        FragmentManager fm=getActivity().getSupportFragmentManager();
                        DatePickerFragment datePickerFragment=DatePickerFragment.newInstance(mCrime.getDate());
                        datePickerFragment.setTargetFragment(CrimeFragment.this, RESULT_DATE);
                        datePickerFragment.show(fm, DIALOG_DATE);
                        break;
                    case 2:
                        FragmentManager fm1=getActivity().getSupportFragmentManager();
                        TimePickerFragment timePickerFragment=TimePickerFragment.newInstance(mCrime.getDate());
                        timePickerFragment.setTargetFragment(CrimeFragment.this,REQUEST_TIME);
                        timePickerFragment.show(fm1,DIALOG_TIME);
                        break;
                    default:break;
                }
            break;
        default:break;
        }

    }
    private void updateDate(Button button,SimpleDateFormat simpleDateFormat){
        button.setText(simpleDateFormat.format(mCrime.getDate()));
    }

    @Override
    public void onPause() {
        super.onPause();
        CrimeLab.get(getActivity()).saveCrimes();
    }
}
