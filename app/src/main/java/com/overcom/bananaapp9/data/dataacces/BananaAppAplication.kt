package com.overcom.bananaapp9.io.dataacces

import android.app.Application
import com.overcom.bananaapp9.data.dataacces.Preferences

class BananaAppAplication : Application() {

    companion object{
        lateinit var preferences: Preferences
    }

    override fun onCreate() {
        super.onCreate()
        preferences = Preferences(applicationContext)
    }

}