package com.example.koindependencyinjection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.koindependencyinjection.databinding.ActivityMainBinding
import com.example.koindependencyinjection.viewmodel.MainViewModel
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainModuleScope = getKoin().getOrCreateScope("mainModuleScope", named<MainActivity>())
    private val mainViewModel: MainViewModel = mainModuleScope.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        mainViewModel.getLiveData().observe(this, Observer { number ->
            binding.content.activityMainTextView.text = number.toString()
        })

        binding.content.activityMainButton.setOnClickListener {
            mainViewModel.generateNextNumber()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainModuleScope.close()
    }
}