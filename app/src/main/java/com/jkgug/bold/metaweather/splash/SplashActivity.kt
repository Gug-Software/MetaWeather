package com.jkgug.bold.metaweather.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jkgug.bold.metaweather.R
import com.jkgug.bold.metaweather.databinding.ActivitySplashBinding
import com.jkgug.bold.metaweather.splash.viewmodel.SplashUiModel
import com.jkgug.bold.metaweather.splash.viewmodel.SplashViewModel
import com.jkgug.bold.metaweather.utils.show
import com.jkgug.bold.metaweather.weather.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModel<SplashViewModel>()

    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        lifecycle.addObserver(viewModel)
        defineObservers()
    }

    private fun defineObservers() = viewModel.uiModel.observe(this) {
        handleUiModel(it)
    }

    private fun handleUiModel(uiModel: SplashUiModel) {
        when (uiModel) {
            SplashUiModel.Loading -> loading()
            SplashUiModel.Next -> next()
        }
    }

    private fun loading() = show(binding.lottieLoader)

    private fun next() = startActivity(
        Intent(
            this,
            MainActivity::class.java
        ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    )
}