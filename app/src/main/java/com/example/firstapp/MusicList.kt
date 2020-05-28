package com.example.firstapp

import Adaptar.MyAdapter
import SongItem
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import okhttp3.internal.notify
import org.json.JSONArray
import java.io.IOException


class MusicList : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val client = OkHttpClient()

    private var dataList: JSONArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_list)

        viewManager = LinearLayoutManager(this)
        val songItem = SongItem("Girls like you", "detail", "https://m.media-amazon.com/images/M/MV5BZmI3Njc2OGYtZTMyMC00NjNhLWJjMzItYmZmNDdlOTVmZjQ1XkEyXkFqcGdeQXVyNjE0ODc0MDc@._V1_.jpg")
        val myDataset = arrayListOf(songItem)
        viewAdapter = MyAdapter(myDataset)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }


    }

    override fun onStart() {
        super.onStart()

        getData()

    }

    fun getData() {


        val request = Request.Builder()
            .url("https://raw.githubusercontent.com/bing-deng/Android_Doc/master/songs.json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val gson = Gson()

                    val json = response.body!!.string()
                    val dataSource: ArrayList<SongItem> = gson.fromJson(
                        json, object : TypeToken<List<SongItem?>?>() {}.type
                    ) //数组

                    this@MusicList.runOnUiThread(java.lang.Runnable {
                        viewAdapter.myDataset = dataSource
                        viewAdapter!!.notifyDataSetChanged()
                    })

                }
            }
        })
    }
}
