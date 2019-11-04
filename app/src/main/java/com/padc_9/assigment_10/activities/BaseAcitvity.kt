package com.padc_9.assigment_10.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.padc_9.assigment_10.data.models.PhotoListModel
import com.padc_9.assigment_10.data.models.PhotoListModelImpl

abstract class BaseAcitvity: AppCompatActivity() {

    protected lateinit var model: PhotoListModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = PhotoListModelImpl
    }
}