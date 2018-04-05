package com.software2.software.repository.sources

import android.util.Log
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.rx2androidnetworking.Rx2AndroidNetworking
import com.software2.software.models.Proveedor
import io.reactivex.Observable
import org.json.JSONObject

/**
 * Created by chofo2003 on 26/03/18.
 */
class FirebaseSource() {

    companion object {
        private val BASE_URL = "https://appandroid-7ec5f.firebaseio.com"
        var PROVIDER = "$BASE_URL/providers.json"
        var PRODUCTS = "$BASE_URL/products.json"
        var PURCHASES = "$BASE_URL/purchases.json"
        var CATEGORIES = "$BASE_URL/categories.json"
        var PROVIDER_ONE = "$BASE_URL/providers/{id}.json"
        var ITEM_IMAGES = "$BASE_URL/images.json"
    }

    fun getAllProviders(): Observable<JsonObject> {
        return Rx2AndroidNetworking.get(PROVIDER).build().getObjectObservable(JsonObject::class.java)
    }

    fun getAllProducts(): Observable<JsonObject> {
        return Rx2AndroidNetworking.get(PRODUCTS).build().getObjectObservable(JsonObject::class.java)
    }

    fun getAllCategories(): Observable<JsonObject> {
        return Rx2AndroidNetworking.get(CATEGORIES).build().getObjectObservable(JsonObject::class.java)
    }

    fun getAllPurchases(): Observable<JsonObject> {
        return Rx2AndroidNetworking.get(PURCHASES).build().getObjectObservable(JsonObject::class.java)
    }

    fun getItemImages(): Observable<JsonObject> {
        return Rx2AndroidNetworking.get(ITEM_IMAGES).build().getObjectObservable(JsonObject::class.java)
    }

    fun updateProvider(id: String, body: JSONObject): Observable<JsonObject> {
        return Rx2AndroidNetworking.put(PROVIDER_ONE)
                .addPathParameter("id", id)
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(body)
                .build()
                .getObjectObservable(JsonObject::class.java)
    }
}