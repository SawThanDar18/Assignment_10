package com.padc_9.assigment_10.data.models

import androidx.lifecycle.LiveData
import com.padc_9.assigment_10.data.vos.PhotoVO
import com.padc_9.assigment_10.network.dataagents.RetrofitDataAgent

interface PhotoListModel {

    fun getAllPhotos(
        onFailure: (String) -> Unit
    ) : LiveData<List<PhotoVO>>

    fun getPhotoDetail(
        id: String,
        onSuccess: (PhotoVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSearchPhoto(searchValue: String): List<PhotoVO>
}