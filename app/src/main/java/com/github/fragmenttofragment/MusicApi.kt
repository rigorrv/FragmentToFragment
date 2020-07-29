package com.github.fragmenttofragment
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicApi {
    @GET(value = "/search")
    fun getMusic(@Query("term")type : String) : Observable<RockList>
    fun getPop(@Query("pop&amp;media=music&amp;entity=song&amp;limit=50")type : String) : Observable<PopList>
    fun getClassic(@Query("classic&amp;media=music&amp;entity=song&amp;limit=50")type : String) : Observable<PopList>

    companion object{
        fun getMusicApi(): MusicApi{
            return Retrofit.Builder()
                .baseUrl("https://itunes.apple.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(MusicApi::class.java)

        }
    }
}