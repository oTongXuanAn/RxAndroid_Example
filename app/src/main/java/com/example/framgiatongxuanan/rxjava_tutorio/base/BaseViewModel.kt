package com.example.framgiatongxuanan.rxjava_tutorio.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.support.annotation.StringRes
import com.example.framgiatongxuanan.rxjava_tutorio.App
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.IllegalArgumentException

/**
 * Created by FRAMGIA\tong.xuan.an on 22/11/2017.
 */
abstract class BaseViewModel(context: Application, val disposable: CompositeDisposable?) : AndroidViewModel(context) {
    constructor(context: Application) : this(context, null)

    protected fun rx(block: () -> Disposable) {
        if (disposable == null) throw IllegalArgumentException("Need disposable on constructor when subscribe RxJava")
        disposable.add(block())
    }

    protected fun getString(@StringRes res: Int) = getApplication<App>().getString(res) ?: ""
}