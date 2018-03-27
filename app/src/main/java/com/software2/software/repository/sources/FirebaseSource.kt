package com.software2.software.repository.sources

import com.google.gson.JsonObject
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable

/**
 * Created by chofo2003 on 26/03/18.
 */
class FirebaseSource() {

    companion object {
        private val BASE_URL = "https://appandroid-7ec5f.firebaseio.com"
        var PROVIDER = "$BASE_URL/providers.json"
        var PROVIDER_ONE = "$BASE_URL/providers/{id}.json"
        var ITEM_IMAGES = "$BASE_URL/images.json"
    }

    fun getAllProviders(): Observable<JsonObject> {
        return Rx2AndroidNetworking.get(PROVIDER).build().getObjectObservable(JsonObject::class.java)
    }

    fun getItemImages(): Observable<JsonObject> {
        return Rx2AndroidNetworking.get(ITEM_IMAGES).build().getObjectObservable(JsonObject::class.java)
    }


}