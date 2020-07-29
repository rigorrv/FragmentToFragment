package com.github.fragmenttofragment

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_one.view.*
import kotlinx.android.synthetic.main.view_item.view.*
import java.util.concurrent.TimeUnit

lateinit var communicator: Communicator
lateinit var rootView: View

class FirstFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.recyclerView

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_one, container, false) as ViewGroup
        communicator = activity as Communicator
        rootView.btnSend.setOnClickListener {
            //communicator.passData(rootView.editText.text.toString())
        }
        return rootView

    }

    fun displayData(dataSet: List<RockList.Result>) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = FirstFragment.Adapter()
        recyclerView.adapter = adapter
        adapter.list = dataSet
    }

    fun displayDataPop(dataSet: List<PopList.Result>) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = FirstFragment.Adapter()
        recyclerView.adapter = adapter
        adapter.listPop = dataSet
    }


    class Adapter() : RecyclerView.Adapter<Adapter.ViewHolder>() {
        var listPop: List<PopList.Result> = emptyList()
        var list: List<RockList.Result> = emptyList()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view: View =
                LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false)

            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder?.onBind(list[position])
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val TAG : String = "TAG"
            val artistName = itemView.artistText
            val trackName = itemView.trackText
            val imageArtist = itemView.imageAlbum
            val timeSong = itemView.timeSong
            val mp = MediaPlayer()


            fun onBind(list: RockList.Result) {
                var mp : MediaPlayer = MediaPlayer()
                artistName.text = list.artistName
                trackName.text = list.trackName
                var imageArtistUrl = list.artworkUrl100
                var songUrl = list.previewUrl
                var milliseconds = list.trackTimeMillis.toLong()
                Picasso.get()
                Picasso.get()
                    .load(imageArtistUrl)
                    .resize(300, 300)
                    .into(imageArtist);
                val bandName: String = list.artistName + " - " + list.trackName
                val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
                val seconds : Long = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
                val time = minutes.toString() +": "+ seconds.toString()
                timeSong.text = time


                itemView.setOnClickListener {
                    var musicPlayer = TestMusic(mp, songUrl)
                   // musicPlayer.test()
                    var value = bandName
                    communicator.passData(value, imageArtistUrl, songUrl, time, list.artistName)
                    Log.d(TAG, "onBind: $value")





                }


            }


        }


    }
}