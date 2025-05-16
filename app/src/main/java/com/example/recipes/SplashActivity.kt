// SplashActivity.kt
package com.example.recipes

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.recipes.data.ArticleData.getArticles
import com.example.recipes.data.MealData.getMeals
import com.example.recipes.model.ArticlesModel
import com.example.recipes.model.MealModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private var receiverRegistered = false
    private val finishReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d(TAG, "Broadcast received: Finishing SplashActivity.")
            // Add a very small delay before finishing to ensure MainActivity is ready
            Handler(Looper.getMainLooper()).postDelayed({
                // Use a custom transition to prevent the white flash
                finish()
            }, 1)
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.splash_screen)

        Log.d(TAG, "SplashActivity started and layout set")

        val lottieView: LottieAnimationView = findViewById(R.id.lottieView)
        lottieView.setAnimation(R.raw.newsplash)
        lottieView.playAnimation()

        val spinnerView: LottieAnimationView = findViewById(R.id.spinnerView)
        spinnerView.setAnimation(R.raw.spinner)
        spinnerView.playAnimation()

        // Register receiver immediately to avoid potential timing issues
        registerReceiver(finishReceiver, IntentFilter("finish_splash"))
        receiverRegistered = true
        Log.d(TAG, "Receiver registered in onCreate().")

        Handler(Looper.getMainLooper()).postDelayed({
            // Prepare data for MainActivity
            val articlesList = ArrayList<ArticlesModel>()
            // Add articles to the list...
            articlesList.addAll(getArticles())

            val mealsList = ArrayList<MealModel>()
            // Add meals to the list...
            mealsList.addAll(getMeals())

            val intent = Intent(this, MainActivity::class.java)
            intent.putParcelableArrayListExtra("articles_list", articlesList)
            intent.putParcelableArrayListExtra("meals_list", mealsList)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

            startActivity(intent)
            overridePendingTransition(0, 0) // Disable activity transition
        }, 7000) // Missing delay parameter
    }


    override fun onDestroy() {
        if (receiverRegistered) {
            try {
                unregisterReceiver(finishReceiver)
                receiverRegistered = false
                Log.d(TAG, "Receiver unregistered in onDestroy().")
            } catch (e: Exception) {
                Log.e(TAG, "Error unregistering receiver: ${e.message}")
            }
        }
        super.onDestroy()
    }

    // This is key to preventing the white flash
    override fun finish() {
        super.finish()
        // Use a cross-fade animation to avoid white flash
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}