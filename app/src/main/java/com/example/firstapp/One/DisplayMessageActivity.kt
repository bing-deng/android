package com.example.firstapp.One

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
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


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            planets_spinner.adapter = adapter
        }

    }



        override fun onStart() {
            super.onStart()
        }

        override fun onResume() {
            super.onResume()

            val intent = Intent()
            intent.putExtra("result","done")
            this.setResult(2,intent)
//            this.finish()
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
