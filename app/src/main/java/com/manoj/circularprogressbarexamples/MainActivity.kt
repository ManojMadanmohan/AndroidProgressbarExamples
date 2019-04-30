package com.manoj.circularprogressbarexamples

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postUpdate()
    }

    var percent = 0

    fun postUpdate() {
        if(percent >= 100) {
            percent = 0
        }
        percent = percent + 1
        progress_1.progress = percent
        progress_2.progress = percent
        progress_count.text = percent.toString()
        Handler().postDelayed({
                postUpdate()
        }, 1000)
    }
}
