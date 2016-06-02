package com.study.an.MediaPlayer;

import android.content.Context;
import android.media.MediaPlayer;

import com.study.an.all.R;

/**
 * Created by admin on 2016/1/25.
 */
public class AudioPlayer  {
    private MediaPlayer mMediaPlayer;
    boolean isPaused;
    public void stop(){
        if(mMediaPlayer!=null){
            mMediaPlayer.release();
            mMediaPlayer=null;
            isPaused=false;
        }
    }
    public void play(Context context){
        if(isPaused){
            mMediaPlayer.start();
        }else {
        stop();
        mMediaPlayer=MediaPlayer.create(context, R.raw.lose_lover);

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });
        mMediaPlayer.start();

        }
        isPaused=false;
    }
    public void pause(){
        if(mMediaPlayer!=null){
        mMediaPlayer.pause();
            isPaused=true;
        }
    }
    public boolean isPlaying(){
        if(mMediaPlayer!=null){
       return mMediaPlayer.isPlaying();
        }else {
            return false;
        }
    }
    public int getCurrentPosition(){
        if(mMediaPlayer!=null){
        return mMediaPlayer.getCurrentPosition();
        }else {
            return 0;
        }
    }
    public int getDuration(){
        if(mMediaPlayer!=null){
        return mMediaPlayer.getDuration();
        }else {
            return 100;
        }
    }


}
