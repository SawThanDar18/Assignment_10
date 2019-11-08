package com.padc_9.assigment_10.network

import com.padc_9.assigment_10.data.vos.PhotoVO
import com.padc_9.assigment_10.network.responses.GetSearchPhotosResponse
import com.padc_9.assigment_10.utils.GET_PHOTO_DETAIL
import com.padc_9.assigment_10.utils.GET_PHOTO_LIST
import com.padc_9.assigment_10.utils.GET_SEARCH_PHOTO
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoApi {

    @GET(GET_PHOTO_LIST)
    fun getAllPhotos(): Call<List<PhotoVO>>

    @GET(GET_PHOTO_LIST)
    fun getAllPhotosObservable(): Observable<List<PhotoVO>>

    @GET(GET_PHOTO_DETAIL)
    fun getPhotoDetail(@Path("id") id: String): Call<PhotoVO>

    @GET(GET_SEARCH_PHOTO)
    fun getSearchPhoto(@Query("s") keyword: String): Call<GetSearchPhotosResponse>
}