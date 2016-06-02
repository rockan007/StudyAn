package com.study.an.CriminalIntent;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by admin on 2016/1/22.
 */
public class CrimeLab {
    private static final String TAG="CrimeLab";
    private static final String FILENAME="crimes.json";
    private CriminalIntentJSONSerializer mSerializer;
    private static CrimeLab sCrimeLab;
    private Context mAppContext;
    private ArrayList<Crime> mCrimes;

    public CrimeLab(Context appContext){
        mAppContext=appContext;
        mSerializer=new CriminalIntentJSONSerializer(mAppContext,FILENAME);
        try {
            mCrimes=mSerializer.loadCrimes();
        }catch (Exception e){
            mCrimes=new ArrayList<>();
        }

    }
    public static CrimeLab get(Context context){
        if(sCrimeLab==null){
            sCrimeLab=new CrimeLab(context.getApplicationContext());
        }
        return sCrimeLab;
    }
    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }
    public Crime getCrime(UUID id){
        for (Crime c:mCrimes){
            if(c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }
    public void addCrime(Crime c){
        mCrimes.add(c);
    }
    public boolean saveCrimes(){
        try {
            mSerializer.saveCrimes(mCrimes);
            Log.d(TAG,"crimes save to file");
            return true;
        }catch (Exception e){
            Log.e(TAG,"crimes saved to file failed");
            return false;
        }

    }
    public void deleteCrime(Crime c){
        mCrimes.remove(c);
    }
}
