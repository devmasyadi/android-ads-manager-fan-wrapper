package com.adsmanager.adswrapper.di

import com.adsmanager.adswrapper.AdsManagerOpenAdWrapper
import com.adsmanager.adswrapper.AdsManagerWrapper
import org.koin.dsl.module

val adsManagerWrapperModule = module {
    single { AdsManagerWrapper(get()) }
    single { AdsManagerOpenAdWrapper(get()) }
}