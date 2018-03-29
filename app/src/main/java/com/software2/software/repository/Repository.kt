package com.software2.software.repository

import com.software2.software.models.Categoria
import com.software2.software.models.Compra
import com.software2.software.models.Producto
import com.software2.software.models.Proveedor
import com.software2.software.repository.sources.FirebaseSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by chofo2003 on 26/03/18.
 */
class Repository {

    private val firebaseSource = FirebaseSource()

    fun getProvider(observer: Consumer<MutableList<Proveedor>>, error: Consumer<Throwable>) {
        firebaseSource.getAllProviders().map { Proveedor.fromMultipleJson(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer, error)
    }

    fun getProduct(observer: Consumer<MutableList<Producto>>, error: Consumer<Throwable>) {
        firebaseSource.getAllProviders().map { Producto.fromMultipleJson(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer, error)
    }
    fun getCategory(observer: Consumer<MutableList<Categoria>>, error: Consumer<Throwable>) {
        firebaseSource.getAllProviders().map { Categoria.fromMultipleJson(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer, error)
    }
    fun getPurchase(observer: Consumer<MutableList<Compra>>, error: Consumer<Throwable>) {
        firebaseSource.getAllProviders().map { Compra.fromMultipleJson(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer, error)
    }

    fun getItemImages(observer: Consumer<MutableList<String>>, error: Consumer<Throwable>) {
        firebaseSource.getItemImages().map {
            val imagesUrls = mutableListOf<String>()
            it.entrySet().forEach {
                imagesUrls.add(it.value.asJsonObject.get("url").asString)
            }
            imagesUrls
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer, error)
    }
}