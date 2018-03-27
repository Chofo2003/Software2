package com.software2.software.features.index

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.software2.software.models.Proveedor
import com.software2.software.repository.Repository
import io.reactivex.functions.Consumer

/**
 * Created by chofo2003 on 26/03/18.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    val proveedores = MutableLiveData<MutableList<Proveedor>>()
    val repository = Repository()

    fun getProveedores(){
        repository.getProvider(Consumer {
            proveedores.value = it
        }, Consumer {  })
    }


}