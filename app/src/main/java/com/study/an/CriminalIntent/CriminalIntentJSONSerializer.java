package com.study.an.CriminalIntent;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by admin on 2016/1/29.
 */
public class CriminalIntentJSONSerializer {
    private Context mContext;
    private String mFileName;
    public CriminalIntentJSONSerializer (Context c,String fileName){
        mContext=c;
        mFileName=fileName;
    }

    /**
     *
     * @param crimes save
     * @throws Exception
     */
    public void saveCrimes(ArrayList<Crime> crimes)throws Exception{
        JSONArray array=new JSONArray();
        for(Crime c:crimes){
            array.put(c.toJSON());
        }
        Writer writer=null;
        try {
            OutputStream out=mContext.openFileOutput(mFileName,Context.MODE_PRIVATE);
            writer=new OutputStreamWriter(out);
            writer.write(array.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(writer!=null){
                try {
                    writer.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     *
     * @return crimes
     * @throws IOException
     * @throws JSONException
     */
    public ArrayList<Crime> loadCrimes()throws IOException,JSONException{
        ArrayList<Crime> crimes=new ArrayList<>();
        BufferedReader reader=null;
        try {
            InputStream in=mContext.openFileInput(mFileName);
            reader=new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString=new StringBuilder();
            String line=null;
            while ((line=reader.readLine())!=null){
                jsonString.append(line);
            }
            JSONArray array=(JSONArray)new JSONTokener(jsonString.toString()).nextValue();
            for(int i=0;i<array.length();i++){
                crimes.add(new Crime(array.getJSONObject(i)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(reader!=null){
                reader.close();
            }
        }
        return crimes;
    }
}
