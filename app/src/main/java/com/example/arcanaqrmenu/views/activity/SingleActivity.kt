package com.example.arcanaqrmenu.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.arcanaqrmenu.R

class SingleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.single__activity)
    }
}