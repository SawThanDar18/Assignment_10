package com.padc_9.assigment_10.mvp.presenters

import androidx.lifecycle.Observer
import com.padc_9.assigment_10.activities.BaseAcitvity
import com.padc_9.assigment_10.data.models.PhotoListModelImpl
import com.padc_9.assigment_10.mvp.views.PhotoDetailView

class PhotoDetailPresenter: BasePresenter<PhotoDetailView>() {

    fun onUiReady(activity: BaseAcitvity, photoId: String) {
        if (photoId != null) {
            val model = PhotoListModelImpl
            model.getPhotoDetail(photoId, {
                mView.displayPhotoDetail(it)
            }, {
                mView.errorMessage(it)
            })
        }
    }
}