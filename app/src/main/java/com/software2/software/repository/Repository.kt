package com.software2.software.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.software2.software.models.Categoria
import com.software2.software.models.Compra
import com.software2.software.models.Producto
import com.software2.software.models.Proveedor
import com.software2.software.repository.sources.FirebaseSource
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.security.Provider
import java.util.*
import kotlin.collections.HashMap

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
        firebaseSource.getAllProducts().map { Producto.fromMultipleJson(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer, error)
    }

    fun getCategory(observer: Consumer<MutableList<Categoria>>, error: Consumer<Throwable>) {
        firebaseSource.getAllCategories().map { Categoria.fromMultipleJson(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer, error)
    }

    fun getPurchase(observer: Consumer<MutableList<Compra>>, error: Consumer<Throwable>) {
        firebaseSource.getAllPurchases().map { Compra.fromMultipleJson(it) }
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

    fun updateProvider(provider: Proveedor, observer: Consumer<JsonObject>,
                       error: Consumer<Throwable>) {
        firebaseSource.updateProvider(provider.id, JSONObject(Gson().toJson(provider)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer, error)

    }

    fun updateCategory(category: Categoria, observer: Consumer<JsonObject>,
                       error: Consumer<Throwable>) {
        firebaseSource.updateCategory(category.id, JSONObject(Gson().toJson(category)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer, error)

    }

    fun updateProduct(product: Producto, observer: Consumer<JsonObject>,
                      error: Consumer<Throwable>) {
        firebaseSource.updateProduct(product.id, JSONObject(Gson().toJson(product)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer, error)

    }

    fun createProvider(provider: Proveedor, observer: Consumer<JsonObject>,
                       error: Consumer<Throwable>) {
        firebaseSource.createProvider(JSONObject(Gson().toJson(provider)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer, error)
    }

    fun deleteProvider(provider: Proveedor, observer: Consumer<JsonObject>,
                       error: Consumer<Throwable>) {
        firebaseSource.deleteProvider(provider.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer, error)
    }

    fun deleteCategory(category: Categoria, observer: Consumer<JsonObject>,
                       error: Consumer<Throwable>) {
        firebaseSource.deleteCategory(category.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer, error)
    }

    fun deleteProduct(product: Producto, observer: Consumer<JsonObject>,
                      error: Consumer<Throwable>) {
        firebaseSource.deleteProduct(product.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer, error)
    }

    fun getAllProvidersAndCategories(observer: Consumer<in HashMap<String, MutableList<out Any>>>, error: Consumer<Throwable>) {
        Observable.zip(firebaseSource.getAllProviders(), firebaseSource.getAllCategories(),
                BiFunction<JsonObject, JsonObject, HashMap<String, MutableList<out Any>>> {t1, t2 ->
                    hashMapOf("providers" to Proveedor.fromMultipleJson(t1), "categories" to Categoria.fromMultipleJson(t2)) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer, error)
    }


}