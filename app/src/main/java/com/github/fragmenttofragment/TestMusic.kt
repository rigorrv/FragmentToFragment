package com.github.fragmenttofragment

import android.R
import android.media.MediaPlayer
import android.widget.VideoView


class TestMusic(var mediaPlayer : MediaPlayer, var song : String) {



    val TAG : String = "TAG"
    fun test(){





        /*
        mediaPlayer.setDataSource(song)
        if (mediaPlayer.isPlaying==true){
            Log.d(TAG, "test: Is Playing")
            mediaPlayer.reset()
            mediaPlayer.stop()
        }else{
            Log.d(TAG, "test: is Not Playing")
            mediaPlayer.prepare()
            mediaPlayer.start()
        }

         */
    }
}