package com.padc_9.assigment_10

import android.app.Application
import com.padc_9.assigment_10.data.models.PhotoListModelImpl

class PhotoApp: Application(){

    override fun onCreate() {
        super.onCreate()
        PhotoListModelImpl.initDatabase(applicationContext)
    }
}