package com.example.firstapp

import android.content.Context
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

class MotionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion)

        this.getSpf(R.string.user_pwd)
        this.getShareSpf(R.string.user_pwd2)
    }

    /**
     * 保存键值对数据
     *
     */


    fun getSpf(key:Int):String{

        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
        var value = sharedPref.getString(getString(key), "defaultValue")
        return value?:""
    }

    fun getShareSpf(key:Int):String{

        val sharedPref = this?.getSharedPreferences(getString(R.string.share_preference_01),Context.MODE_PRIVATE)
        var value = sharedPref.getString(getString(key), "defaultValue")
        return value?:""
    }

}