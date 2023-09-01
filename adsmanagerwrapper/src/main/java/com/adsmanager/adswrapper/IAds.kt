package com.adsmanager.adswrapper

import android.app.Activity
import android.content.Context
import android.widget.RelativeLayout
import com.adsmanager.core.CallbackAds
import com.adsmanager.core.SizeBanner
import com.adsmanager.core.SizeNative
import com.adsmanager.core.iadsmanager.IInitialize
import com.adsmanager.core.rewards.IRewards

interface IAds {

    fun initialize(
        context: Context,
        iInitialize: IInitialize,
    )

    fun setTestDevices(
        activity: Activity,
        testDevices: List<String>,
    )

    fun loadGdpr(activity: Activity, childDirected: Boolean)

    fun showBanner(
        activity: Activity,
        bannerView: RelativeLayout,
        sizeBanner: SizeBanner,
        callbackAds: CallbackAds?
    )

    fun loadInterstitial(
        activity: Activity,
    )

    fun showInterstitial(
        activity: Activity,
        callbackAds: CallbackAds?
    )

    fun showNativeAds(
        activity: Activity,
        nativeView: RelativeLayout,
        sizeNative: SizeNative,
        callbackAds: CallbackAds?
    )

    fun loadRewards(
        activity: Activity,
    )

    fun showRewards(
        activity: Activity,
        callbackAds: CallbackAds?,
        iRewards: IRewards?
    )
}

