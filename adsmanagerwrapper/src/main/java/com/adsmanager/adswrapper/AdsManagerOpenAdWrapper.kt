package com.adsmanager.adswrapper

import android.app.Activity
import com.adsmanager.ads.AdsManagerOpenAd
import com.adsmanager.core.CallbackAds
import com.adsmanager.core.CallbackOpenAd
import com.adsmanager.core.ConfigAds

class AdsManagerOpenAdWrapper(
    private val adsManagerOpenAd: AdsManagerOpenAd
) {

    private var currentActivity: Activity? = null

    fun setCurrentActivity(activity: Activity) {
        this.currentActivity = activity
        adsManagerOpenAd.setCurrentActivity(activity)
    }

    fun getCurrentActivity() = currentActivity

    fun isShowingAd(): Boolean {
        return adsManagerOpenAd.isShowingAd()
    }

    fun loadAd(
        activity: Activity,
        callbackAds: CallbackAds?
    ) {
        if (ConfigAds.isShowAds && ConfigAds.isShowOpenAd)
            adsManagerOpenAd.loadAd(
                activity,
                ConfigAds.primaryAds,
                ConfigAds.primaryOpenAdId,
                ConfigAds.secondaryAds,
                ConfigAds.secondaryOpenAdId,
                ConfigAds.tertiaryAds,
                ConfigAds.tertiaryOpenAdId,
                ConfigAds.quaternaryAds,
                ConfigAds.quaternaryOpenAdId,
                callbackAds
            )
        else {
            callbackAds?.onAdFailedToLoad("ads off")
        }
    }

    fun showAdIfAvailable(
        activity: Activity,
        callbackOpenAd: CallbackOpenAd?
    ) {
        if (ConfigAds.isShowAds && ConfigAds.isShowOpenAd)
            adsManagerOpenAd.showAdIfAvailable(
                activity,
                ConfigAds.primaryAds,
                ConfigAds.primaryOpenAdId,
                ConfigAds.secondaryAds,
                ConfigAds.secondaryOpenAdId,
                ConfigAds.tertiaryAds,
                ConfigAds.tertiaryOpenAdId,
                ConfigAds.quaternaryAds,
                ConfigAds.quaternaryOpenAdId,
                callbackOpenAd
            )
        else {
            callbackOpenAd?.onAdFailedToLoad("ads off")
        }
    }

}
