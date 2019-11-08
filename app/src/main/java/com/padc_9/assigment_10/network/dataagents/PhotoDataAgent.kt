package com.padc_9.assigment_10.network.dataagents

import com.padc_9.assigment_10.data.vos.PhotoVO
import io.reactivex.Observable

interface PhotoDataAgent {

  fun getPhotos(
    onSuccess: (List<PhotoVO>) -> Unit,
    onFailure: (String) -> Unit)

  fun getPhotosObservable(): Observable<List<PhotoVO>>

  fun getPhotoDetail(
    id: String,
    onSuccess: (PhotoVO) -> Unit,
    onFailure: (String) -> Unit
  )

  //fun getSearchPhoto(searchValue: String)
}