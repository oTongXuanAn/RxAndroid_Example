package com.example.framgiatongxuanan.rxjava_tutorio.network;

import com.example.framgiatongxuanan.rxjava_tutorio.model.Android;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

/**
 * Created by FRAMGIA\tong.xuan.an on 22/11/2017.
 */

public interface RequestInterface {
    @FormUrlEncoded
    @GET("android/jsonarray/")
    Observable<List<Android>> register();
}
