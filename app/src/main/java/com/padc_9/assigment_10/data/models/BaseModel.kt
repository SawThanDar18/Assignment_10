package com.padc_9.assigment_10.data.models

import android.content.Context
import com.padc_9.assigment_10.network.dataagents.PhotoDataAgent
import com.padc_9.assigment_10.network.dataagents.RetrofitDataAgent
import com.padc_9.assigment_10.persistance.PhotoDatabase

abstract class BaseModel{

    protected val dataAgent: PhotoDataAgent = RetrofitDataAgent

    protected lateinit var database: PhotoDatabase

    fun initDatabase(context: Context){
        database = PhotoDatabase.getInstance(context)
    }
}