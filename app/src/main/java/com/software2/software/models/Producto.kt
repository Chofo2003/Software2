package com.software2.software.models

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.io.Serializable

/**
 * Created by chofo2003 on 26/03/18.
 */
data class Producto(var id: String, var name: String, var description: String, var unity: String,
                    var categoryId: String, var providerId: String) : ViewModel, Serializable {
    constructor() : this("", "", "", "", "", "")

    companion object {
        fun fromMultipleJson(jsonObject: JsonObject): MutableList<Producto> {
            val productos = mutableListOf<Producto>()
            jsonObject.entrySet().forEach {
                productos.add(fromJson(it.value))
            }
            return productos
        }

        private fun fromJson(json: JsonElement): Producto {
            val id = json.asJsonObject.get("id").asString
            val name = json.asJsonObject.get("name").asString
            val description = json.asJsonObject.get("description").asString
            val unity = json.asJsonObject.get("unity").asString
            val categoryId = json.asJsonObject.get("categoryId").asString
            val providerId = json.asJsonObject.get("providerId").asString
            return Producto(id, name, description, unity, categoryId, providerId)
        }
    }

}