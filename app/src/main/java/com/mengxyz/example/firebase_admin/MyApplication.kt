package com.mengxyz.example.firebase_admin

import android.app.Application
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Headers

const val IP_ADDR = "192.168.0.8"
const val PORT = 8080

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        FuelManager.instance.apply {
            basePath = "http://$IP_ADDR:$PORT/v1"
            baseHeaders = mapOf(Headers.CONTENT_TYPE to "application/json")
        }
    }
}