package com.padc_9.assigment_10.mvp.views

import com.padc_9.assigment_10.data.vos.PhotoVO

interface PhotoDetailView: BaseView {
    fun displayPhotoDetail(photoVO: PhotoVO)
    //fun displayRelatedPhotoList(photoList: List<PhotoVO>)
    fun errorMessage(errorMessage: String)
}