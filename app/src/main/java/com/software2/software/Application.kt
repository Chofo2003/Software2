package com.software2.software

import android.app.Application
import com.androidnetworking.AndroidNetworking

/**
 * Created by chofo2003 on 26/03/18.
 */
class Application :Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext)
    }
}