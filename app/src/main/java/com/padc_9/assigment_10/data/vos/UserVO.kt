package com.padc_9.assigment_10.data.vos

import com.google.gson.annotations.SerializedName

data class UserVO(

    @SerializedName("id")
    var id: String,

    @SerializedName("updated_at")
    var updated_id: String?,

    @SerializedName("name")
    var name: String?,

    @SerializedName("twitter_username")
    var twitter_username: String?,

    @SerializedName("portfolio_url")
    var photo: String?

)