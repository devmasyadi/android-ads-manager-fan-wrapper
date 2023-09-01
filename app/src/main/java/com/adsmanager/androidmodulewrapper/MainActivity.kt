package com.adsmanager.androidmodulewrapper

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.adsmanager.adswrapper.AdsManagerOpenAdWrapper
import com.adsmanager.adswrapper.AdsManagerWrapper
import com.adsmanager.core.CallbackAds
import com.adsmanager.core.CallbackOpenAd
import com.adsmanager.core.SizeBanner
import com.adsmanager.core.SizeNative
import com.adsmanager.core.rewards.IRewards
import com.adsmanager.core.rewards.RewardsItem
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val adsManager: AdsManagerWrapper by inject()
    private val adsManagerOpenAd: AdsManagerOpenAdWrapper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        adsManager.loadGdpr(this@MainActivity, false)
        adsManager.loadInterstitial(this@MainActivity)
        adsManager.loadRewards(this@MainActivity)

        findViewById<Button>(R.id.btnShowOpenApp).setOnClickListener {
            adsManagerOpenAd.showAdIfAvailable(
                this,
                object : CallbackOpenAd() {
                    override fun onAdFailedToLoad(error: String?) {
                        Log.e("HALLO", "openAd onAdFailedToLoad: $error")
                    }

                    override fun onAdLoaded() {
                        Log.e("HALLO", "openAd onAdLoaded")
                    }

                    override fun onShowAdComplete() {
                        Log.e("HALLO", "openAd onShowAdComplete")
                    }

                })
        }

        findViewById<Button>(R.id.btnShowBanner).setOnClickListener {
            val bannerView = findViewById<RelativeLayout>(R.id.bannerView)
            adsManager.showBanner(
                this,
                bannerView,
                SizeBanner.SMALL,
                object : CallbackAds() {
                    override fun onAdFailedToLoad(error: String?) {
                        Log.e("HALLO", "banner error: $error")
                    }
                })
        }

        findViewById<Button>(R.id.btnShowInterstitial).setOnClickListener {
            adsManager.showInterstitial(this, object : CallbackAds() {
                override fun onAdFailedToLoad(error: String?) {
                    Log.e("HALLO", "interstitial error: $error")
                }
            })
        }

        findViewById<Button>(R.id.btnShowRewards).setOnClickListener {
            adsManager.showRewards(this, object : CallbackAds() {
                override fun onAdFailedToLoad(error: String?) {
                    Log.e("HALLO", "rewards error: $error")
                }
            },
                object : IRewards {
                    override fun onUserEarnedReward(rewardsItem: RewardsItem?) {
                        Log.i("HALLO", "onUserEarnedReward: $rewardsItem")
                    }

                }
            )
        }

        findViewById<Button>(R.id.btnSmallNative).setOnClickListener {
            val nativeView = findViewById<RelativeLayout>(R.id.nativeView)
            adsManager.showNativeAds(
                this,
                nativeView,
                SizeNative.SMALL,
                object : CallbackAds() {
                    override fun onAdFailedToLoad(error: String?) {
                        Log.e("HALLO", "Native error: $error")
                    }
                })
        }


    }

    override fun onBackPressed() {
        return
    }

}