package com.github.fragmenttofragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Communicator {
    val TAG: String = "TAG"
    val fragmentTwo = FragmentTwo()
    val firstFragment = FirstFragment()
    var typeMusic = "rock"


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.relativeLayout, firstFragment)
            .commit()

        getMusic()


        ///Spiner


        // Initializing a String Array
        val colors = arrayOf("Genere", "Rock", "Pop", "Classic")

        // Initializing an ArrayAdapter
        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            colors // Array
        )
        spinner.setSelection(0);
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ){
                when (position){
                    0->{
                        //nothing
                    }
                    1->{
                         typeMusic = "rock"

                        getMusic()
                    }
                    2->{
                         typeMusic = "pop"

                        getMusic()
                    }
                    3->{
                         typeMusic = "classic"

                        getMusic()
                    }
                }

            }
        }


        }

    override fun passData(editTextInput: String, imageUrl : String, songUrl : String, timeTrack : String, tittleBand : String) {
        val bundle = Bundle()
        bundle.putString("inputText", editTextInput)
        bundle.putString("inputImage", imageUrl)
        bundle.putString("inputSong", songUrl)
        bundle.putString("trackTime", timeTrack)
        bundle.putString("tittleBand", tittleBand)


        Log.d(TAG, "passData: $editTextInput")
        val transaction = this.supportFragmentManager.beginTransaction()
        fragmentTwo.arguments = bundle
        transaction.remove(fragmentTwo)
        transaction.replace(R.id.relativeRecycler, fragmentTwo)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        transaction.commit()
    }

    //getRock
    fun getMusic() {
        MusicApi.getMusicApi().getMusic(typeMusic)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<RockList> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable?) {

                }

                override fun onNext(t: RockList) {
                    Log.d(TAG, "onNext: ${t}")
                    displayData(t)
                }

                override fun onError(e: Throwable?) {
                    Toast.makeText(
                        this@MainActivity,
                        " ${e?.message}", Toast.LENGTH_LONG
                    ).show()
                }
            })
    }


    //getRock
    fun getPop() {
        MusicApi.getMusicApi().getPop("rock")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<PopList> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable?) {

                }

                override fun onNext(t: PopList) {
                    Log.d(TAG, "onNext: ${t}")
                    displayDataPop(t)
                }

                override fun onError(e: Throwable?) {
                    Toast.makeText(
                        this@MainActivity,
                        " ${e?.message}", Toast.LENGTH_LONG
                    ).show()
                }
            })
    }

    fun displayData(dataSet: RockList) {
        firstFragment.displayData(dataSet.results)
    }

    fun displayDataPop(dataSet: PopList) {
        firstFragment.displayDataPop(dataSet.results)
    }


}