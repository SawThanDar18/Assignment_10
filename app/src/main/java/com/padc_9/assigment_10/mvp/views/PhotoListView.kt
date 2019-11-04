package com.padc_9.assigment_10.mvp.views

import com.padc_9.assigment_10.data.vos.PhotoVO

interface PhotoListView: BaseView {

    fun displayPhotoList(photoList: List<PhotoVO>)
    fun displayError(errorMessage: String)
    fun navigateToDetail(photoId: String)
}