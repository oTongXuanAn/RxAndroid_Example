package com.example.framgiatongxuanan.rxjava_tutorio.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.framgiatongxuanan.rxjava_tutorio.R
import com.example.framgiatongxuanan.rxjava_tutorio.model.Android
import com.example.framgiatongxuanan.rxjava_tutorio.network.RequestInterface
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    val BASE_URL = "https://api.learn2crack.com/"

    private var mCompositeDisposable: CompositeDisposable? = null

    private var mAdapter: DataAdapter? = null

    private var mAndroidArrayList: ArrayList<Android>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        loadJSon()
    }

    private fun initRecyclerView() {

        recycler_view.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(applicationContext)
        recycler_view.layoutManager = layoutManager
    }

    fun loadJSon() {
        var requestInterface: RequestInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestInterface::class.java)
        var disposable: Disposable = requestInterface.register()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError, this::handleSuccess)
        mCompositeDisposable?.add(disposable)
    }

    private fun handleResponse(androidList: List<Android>) {
        mAndroidArrayList = ArrayList(androidList)
        mAdapter = DataAdapter(mAndroidArrayList)
        recycler_view.adapter = mAdapter
    }

    private fun handleError(error: Throwable) {
        Log.e("handleError: ", "" + error)
        Toast.makeText(this, "Error " + error.localizedMessage, Toast.LENGTH_SHORT).show()
    }

    private fun handleSuccess() {
        Toast.makeText(this, "Get data success! ", Toast.LENGTH_SHORT).show()
    }

}
