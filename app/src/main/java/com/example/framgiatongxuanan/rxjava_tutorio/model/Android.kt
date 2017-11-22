package com.example.framgiatongxuanan.rxjava_tutorio.model

import com.google.gson.annotations.SerializedName

/**
 * Created by FRAMGIA\tong.xuan.an on 22/11/2017.
 */
data class Android(@SerializedName("ver") var version: String,
                   @SerializedName("name") var name: String,
                   @SerializedName("api") var api: String)