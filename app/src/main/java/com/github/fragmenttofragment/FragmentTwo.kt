package com.github.fragmenttofragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_two.view.*
class FragmentTwo : Fragment() {

    var inputText: String? = ""
    var inputImage: String? = ""
    var inputSong: String? = ""
    var trackTime: String? = ""
    var tittleBand: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_two, container, false)
        inputText = arguments?.getString("inputText")
        inputImage = arguments?.getString("inputImage")
        inputSong = arguments?.getString("inputSong")
        trackTime = arguments?.getString("trackTime")
        tittleBand = arguments?.getString("tittleBand")

        rootView.bandName.text = inputText
        Picasso.get()
            .load(inputImage)
            .resize(500, 500)
            .into(rootView.imageView5);

        rootView.songLong.text = trackTime
        rootView.bandTittle.text = tittleBand


        val videoView: VideoView = rootView.musicplayer
        //MediaController mediaController = new MediaController(this);
        // mediaController.setAnchorView(videoView);
        //videoView.setMediaController(mediaController);

        //MediaController mediaController = new MediaController(this);
        // mediaController.setAnchorView(videoView);
        //videoView.setMediaController(mediaController);
        videoView.setVideoPath(inputSong)

        videoView.start()


        return rootView
    }

}