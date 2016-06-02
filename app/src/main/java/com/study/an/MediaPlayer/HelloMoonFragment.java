package com.study.an.MediaPlayer;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import com.study.an.all.R;
import java.util.TimerTask;

/**
 * Created by admin on 2016/1/23.
 */
public class HelloMoonFragment extends Fragment implements View.OnClickListener{
    Button mPlayButton;
    Button mStopButton;
    Button mPauseButton;
    View mView;
    AudioPlayer mPlayer;
    SeekBar mSeekBar;
    int Max;
    int cur;
    TimerTask mTimerTask;
    Boolean isChanging=false;
    AudioManager mAudioManager;
   public static Handler mHandler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         mPlayer=new AudioPlayer();
        mAudioManager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        Max= mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        cur= mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//        mThread.start();
//        mHandler=new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                switch (msg.what){
//                    case 0:
//                        mSeekBar.setProgress(mPlayer.getCurrentPosition());
//                        try{ mThread.sleep(200);}catch (Exception e){
//                            e.printStackTrace();
//                        }
//                        break;
//                    default:
//                        break;
//                }
//            }
//        };
    }
   Thread mThread=new Thread(new Runnable() {
       @Override
       public void run() {
           mHandler.sendEmptyMessage(0);
       }
   });

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup parent,Bundle savedInstanceState){
        mView=inflater.inflate(R.layout.hello_moon_fragment,parent,false);
        initViews();
        return mView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }

    private void initViews(){
        mPlayButton=(Button)mView.findViewById(R.id.mediaPayer_play);
        mStopButton=(Button)mView.findViewById(R.id.mediaPayer_stop);
        mPauseButton=(Button)mView.findViewById(R.id.mediaPayer_pause);
        mSeekBar=(SeekBar)mView.findViewById(R.id.seek_bar);

        mSeekBar.setMax(Max);
        mSeekBar.setProgress(cur);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
//                    audioTrackChange(progress); //用户控制进度的改变
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isChanging=true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isChanging=false;
            }
        });
        mPauseButton.setOnClickListener(this);
        mStopButton.setOnClickListener(this);
        mPlayButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mediaPayer_play:
                mPlayer.play(getActivity());
//                mSeekBar.setMax(mPlayer.getDuration());
                break;
            case R.id.mediaPayer_stop:
                mPlayer.stop();
                break;
            case R.id.mediaPayer_pause:
              if(mPlayer.isPlaying()){
                  mPlayer.pause();
              }
                break;
            default:break;
        }
    }
}
