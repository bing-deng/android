package com.example.firstapp.One

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.EXTRA_MESSAGE
import com.example.firstapp.R

import kotlinx.android.synthetic.main.activity_display_message.*
import kotlinx.android.synthetic.main.content_display_message.*

class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)
        setSupportActionBar(toolbar)


        val textValue = intent.getStringExtra(EXTRA_MESSAGE)
        textView2.text = textValue

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }


        override fun onStart() {
            super.onStart()
        }

        override fun onResume() {
            super.onResume()
        }

        override fun onPause() {
            super.onPause()
        }

        override fun onStop() {
            super.onStop()
        }

        override fun onDestroy() {
            super.onDestroy()
        }

}
