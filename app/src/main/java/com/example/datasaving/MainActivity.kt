package com.example.datasaving

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as MainApp).viewModel

        val textView = findViewById<TextView>(R.id.textView)

        viewModel.observe(object : UiStateCallback {
            override fun post(message: String) {
                textView.text = message
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.startTrackingTime()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopTrackingTime()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }
}