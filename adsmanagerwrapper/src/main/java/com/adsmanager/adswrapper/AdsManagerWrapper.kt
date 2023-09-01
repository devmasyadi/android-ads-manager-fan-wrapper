package com.adsmanager.adswrapper

import android.app.Activity
import android.content.Context
import android.widget.RelativeLayout
import com.adsmanager.ads.AdsManager
import com.adsmanager.core.*
import com.adsmanager.core.iadsmanager.IInitialize
import com.adsmanager.core.rewards.IRewards


class AdsManagerWrapper(
    private val adsManager: AdsManager
) : IAds {
    override fun initialize(context: Context, iInitialize: IInitialize) {
        if (ConfigAds.isShowAds)
            adsManager.initialize(
                context,
                iInitialize,
                ConfigAds.primaryAds,
                ConfigAds.primaryAppId,
                ConfigAds.secondaryAds,
                ConfigAds.secondaryAppId,
                ConfigAds.tertiaryAds,
                ConfigAds.tertiaryAppId,
                ConfigAds.quaternaryAds,
                ConfigAds.quaternaryAppId,
            )
    }

    override fun setTestDevices(activity: Activity, testDevices: List<String>) {
        if (ConfigAds.isShowAds)
            adsManager.setTestDevices(
                activity,
                testDevices,
                ConfigAds.primaryAds,
                ConfigAds.secondaryAds,
                ConfigAds.tertiaryAds,
                ConfigAds.quaternaryAds
            )
    }

    override fun loadGdpr(activity: Activity, childDirected: Boolean) {
        if (ConfigAds.isShowAds)
            adsManager.loadGdpr(
                activity, childDirected,
                ConfigAds.primaryAds,
                ConfigAds.secondaryAds,
                ConfigAds.tertiaryAds,
                ConfigAds.quaternaryAds,
            )
    }

    override fun showBanner(
        activity: Activity,
        bannerView: RelativeLayout,
        sizeBanner: SizeBanner,
        callbackAds: CallbackAds?
    ) {
        if (ConfigAds.isShowAds && ConfigAds.isShowBanner)
            adsManager.showBanner(
                activity,
                bannerView,
                sizeBanner,
                ConfigAds.primaryAds,
                ConfigAds.primaryBannerId,
                ConfigAds.secondaryAds,
                ConfigAds.secondaryBannerId,
                ConfigAds.tertiaryAds,
                ConfigAds.tertiaryBannerId,
                ConfigAds.quaternaryAds,
                ConfigAds.quaternaryBannerId,
                callbackAds
            )
        else
            callbackAds?.onAdFailedToLoad("ads off")
    }

    override fun loadInterstitial(activity: Activity) {
        if (ConfigAds.isShowAds && ConfigAds.isShowInterstitial)
            adsManager.loadInterstitial(
                activity,
                ConfigAds.primaryAds,
                ConfigAds.primaryInterstitialId,
                ConfigAds.secondaryAds,
                ConfigAds.secondaryInterstitialId,
                ConfigAds.tertiaryAds,
                ConfigAds.tertiaryInterstitialId,
                ConfigAds.quaternaryAds,
                ConfigAds.quaternaryInterstitialId,
            )
    }

    override fun showInterstitial(activity: Activity, callbackAds: CallbackAds?) {
        if (ConfigAds.isShowAds && ConfigAds.isShowInterstitial && Utils.isValidBetweenTimeInterstitial())
            adsManager.showInterstitial(
                activity,
                ConfigAds.primaryAds,
                ConfigAds.primaryInterstitialId,
                ConfigAds.secondaryAds,
                ConfigAds.secondaryInterstitialId,
                ConfigAds.tertiaryAds,
                ConfigAds.tertiaryInterstitialId,
                ConfigAds.quaternaryAds,
                ConfigAds.quaternaryInterstitialId,
                callbackAds
            ) else {
            callbackAds?.onAdFailedToLoad("ads off")
        }
    }

    override fun showNativeAds(
        activity: Activity,
        nativeView: RelativeLayout,
        sizeNative: SizeNative,
        callbackAds: CallbackAds?
    ) {
        if (ConfigAds.isShowAds && ConfigAds.isShowNativeAd)
            adsManager.showNativeAds(
                activity,
                nativeView,
                sizeNative,
                ConfigAds.primaryAds,
                ConfigAds.primaryNativeId,
                ConfigAds.secondaryAds,
                ConfigAds.secondaryNativeId,
                ConfigAds.tertiaryAds,
                ConfigAds.tertiaryNativeId,
                ConfigAds.quaternaryAds,
                ConfigAds.quaternaryNativeId,
                callbackAds
            )
        else {
            callbackAds?.onAdFailedToLoad("ads off")
        }
    }

    override fun loadRewards(activity: Activity) {
        if (ConfigAds.isShowAds && ConfigAds.isShowRewards)
            adsManager.loadRewards(
                activity,
                ConfigAds.primaryAds,
                ConfigAds.primaryRewardsId,
                ConfigAds.secondaryAds,
                ConfigAds.secondaryRewardsId,
                ConfigAds.tertiaryAds,
                ConfigAds.tertiaryRewardsId,
                ConfigAds.quaternaryAds,
                ConfigAds.quaternaryRewardsId,
            )
    }

    override fun showRewards(activity: Activity, callbackAds: CallbackAds?, iRewards: IRewards?) {
        if (ConfigAds.isShowAds && ConfigAds.isShowRewards)
            adsManager.showRewards(
                activity,
                ConfigAds.primaryAds,
                ConfigAds.primaryRewardsId,
                ConfigAds.secondaryAds,
                ConfigAds.secondaryRewardsId,
                ConfigAds.tertiaryAds,
                ConfigAds.tertiaryRewardsId,
                ConfigAds.quaternaryAds,
                ConfigAds.quaternaryRewardsId,
                callbackAds,
                iRewards
            )
        else {
            callbackAds?.onAdFailedToLoad("ads off")
        }
    }

}