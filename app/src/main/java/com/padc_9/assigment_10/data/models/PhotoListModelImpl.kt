package com.padc_9.assigment_10.data.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.padc_9.assigment_10.data.vos.PhotoVO
import com.padc_9.assigment_10.utils.EM_NULL_RESPONSE
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object PhotoListModelImpl: PhotoListModel, BaseModel() {

    override fun getAllPhotos(onFailure: (String) -> Unit): LiveData<List<PhotoVO>> {

        val databaseSingle = database.photoDao().getAllPhotosMaybe().subscribeOn(Schedulers.io())
            .flatMap {
                if (it.isEmpty()){
                    Maybe.empty()
                }else {
                    Maybe.just(it)
                }
            }

        val networkObservable = dataAgent.getPhotosObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                onFailure(it.message ?: EM_NULL_RESPONSE)
            }
            .doOnNext {
                Log.d("AllPhotos network", it.toString())
            }

        Observable.concat(databaseSingle.toObservable(), networkObservable)
            .firstElement()
            .flatMap<LongArray> {
                database.photoDao().insertPhotos(it).subscribeOn(Schedulers.io()).toMaybe()
            }
            .subscribe(
                {
                    Log.d("Room", it.toString())
                },
                {
                    Log.e("Room", it.localizedMessage)
                }
            )

        val photosFromDB = database.photoDao().getAllPhotos()
        return Transformations.distinctUntilChanged(photosFromDB)
    }

    override fun getPhotoDetail(id: String, onSuccess: (PhotoVO) -> Unit, onFailure: (String) -> Unit) {
        dataAgent.getPhotoDetail(id,
            onSuccess= {
                onSuccess(it)
            },
            onFailure= {
                onFailure(it)
            })
    }

    override fun getSearchPhoto(searchValue: String): List<PhotoVO> {
        return database.photoDao().getSearchPhoto("%$searchValue%")
    }
}