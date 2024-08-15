package com.example.launchmanagementsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.launchmanagementsystem.databinding.ActivityMainBinding

import com.example.launchmanagementsystem.Fragments.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.green)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.FrameLayoutID, MainFragment())
                .commit()
        }
    }
}