package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity



class SignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

    }

    fun back(view: View){

        finish()
    }

    fun webview(view:View){

        startActivity(Intent(this,WebActivity::class.java))
    }

}
