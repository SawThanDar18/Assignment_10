package com.padc_9.assigment_10.network.responses

import com.google.gson.annotations.SerializedName
import com.padc_9.assigment_10.data.vos.PhotoVO

data class GetSearchPhotosResponse(

        @SerializedName("results")
        var results: List<PhotoVO>
)