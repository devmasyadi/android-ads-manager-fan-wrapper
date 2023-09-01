package com.adsmanager.androidmodulewrapper

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.adsmanager.adswrapper.AdsManagerOpenAdWrapper
import com.adsmanager.adswrapper.AdsManagerWrapper
import com.adsmanager.core.CallbackAds
import com.adsmanager.core.CallbackOpenAd
import com.adsmanager.core.ConfigAds
import com.adsmanager.core.NetworkAds
import com.adsmanager.core.iadsmanager.IInitialize
import org.koin.android.ext.android.inject

/**
 * Number of seconds to count down before showing the app open ad. This simulates the time needed
 * to load the app.
 */
private const val COUNTER_TIME = 2L

private const val LOG_TAG = "SplashActivity"

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val adsManager: AdsManagerWrapper by inject()
    private val adsManagerOpenAd: AdsManagerOpenAdWrapper by inject()
    private var secondsRemaining: Long = 0L
    private var isShowOpenAd = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        ConfigAds.primaryOpenAdId = "ca-app-pub-3940256099942544/3419835294XX"
        ConfigAds.secondaryOpenAdId = "ca-app-pub-3940256099942544/3419835294"
        ConfigAds.tertiaryOpenAdId = "fcd4981c18e62771"
        ConfigAds.primaryAds = NetworkAds.ADMOB
        ConfigAds.secondaryAds = NetworkAds.FAN
        ConfigAds.tertiaryAds = NetworkAds.APPLOVIN_MAX

        ConfigAds.isShowBanner = true

        ConfigAds.primaryBannerId = "ca-app-pub-3940256099942544/6300978111XXX"
        ConfigAds.secondaryBannerId = "1363711600744576_1363713000744436"
        ConfigAds.tertiaryBannerId = "62c9e910bbd85680"

        ConfigAds.primaryInterstitialId = "ca-app-pub-3940256099942544/1033173712"
        ConfigAds.secondaryInterstitialId = "1363711600744576_1508878896227845"
        ConfigAds.tertiaryInterstitialId = "7263a762d1a5366b"

        ConfigAds.primaryNativeId = "ca-app-pub-4764558539538067/9810032480XXX"
        ConfigAds.secondaryNativeId = "1363711600744576_1508905202891881"
        ConfigAds.tertiaryNativeId = "91294e31700550f5"

        ConfigAds.primaryRewardsId = "ca-app-pub-3940256099942544/5224354917"
        ConfigAds.secondaryRewardsId = "1363711600744576_1508879032894498"
        ConfigAds.tertiaryRewardsId = "c11378688d2adfd1"
        ConfigAds.isShowAds = true
        ConfigAds.isShowOpenAd = true

        if (!ConfigAds.isShowAds) {
            startMainActivity()
            return
        }

        adsManager.initialize(
            this,
            object : IInitialize {
                override fun onInitializationComplete() {}
            },
        )
        adsManager.loadGdpr(
            this,
            false,
        )
        adsManager.setTestDevices(
            this,
            listOf("a325109e-23fd-4c57-aa10-0a2a79b871c5"),
        )
        adsManagerOpenAd.loadAd(
            this,
            object :
                CallbackAds() {
                override fun onAdFailedToLoad(error: String?) {
                    startMainActivity()
                    Log.e("HALLO", "adsManagerOpenAd loadAd: $error")
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    if(!isShowOpenAd) {
                        isShowOpenAd = true
                        createTimer(COUNTER_TIME)
                    }
                }
            })
    }

    /**
     * Create the countdown timer, which counts down to zero and show the app open ad.
     *
     * @param seconds the number of seconds that the timer counts down from
     */
    private fun createTimer(seconds: Long) {

        val countDownTimer: CountDownTimer = object : CountDownTimer(seconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000 + 1
            }

            override fun onFinish() {
                secondsRemaining = 0
                adsManagerOpenAd.showAdIfAvailable(
                    this@SplashActivity,
                    object : CallbackOpenAd() {

                        override fun onAdFailedToLoad(error: String?) {
                            Log.e(LOG_TAG, "$error")
                            startMainActivity()
                        }

                        override fun onShowAdComplete() {
                            Log.e(LOG_TAG, "onShowAdComplete")
                            startMainActivity()
                        }

                    })

            }
        }
        countDownTimer.start()
    }

    /** Start the MainActivity. */
    fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}