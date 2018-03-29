package com.software2.software.features.home

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.software2.software.repository.Repository
import io.reactivex.functions.Consumer

/**
 * Created by chofo2003 on 26/03/18.
 */
class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val images = MutableLiveData<MutableList<String>>()
    private val repository = Repository()
    //

    fun getImages() {
        repository.getItemImages(Consumer {
            images.value = it
        }, Consumer {  })
    }
}