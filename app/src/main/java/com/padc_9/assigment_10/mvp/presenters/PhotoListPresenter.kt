package com.padc_9.assigment_10.mvp.presenters

import androidx.lifecycle.Observer
import com.padc_9.assigment_10.activities.BaseAcitvity
import com.padc_9.assigment_10.data.models.PhotoListModelImpl
import com.padc_9.assigment_10.delegates.ItemClicked
import com.padc_9.assigment_10.mvp.views.PhotoListView

class PhotoListPresenter: BasePresenter<PhotoListView>(), ItemClicked {

    override fun onItemClicked(id: String) {
        mView.navigateToDetail(id)
    }

    fun onUiReady(activity: BaseAcitvity){
        val model = PhotoListModelImpl
        model.getAllPhotos { mView.displayError(it) }
            .observe(activity, Observer {
                mView.displayPhotoList(it)
            })
    }

    /*fun displaySearchPhotos(searchValue: String){
        val model = PhotoListModelImpl
        model.getSearchPhoto(searchValue)
    }*/
}