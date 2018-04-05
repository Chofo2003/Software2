package com.software2.software.models

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.io.Serializable

/**
 * Created by chofo2003 on 26/03/18.
 */
data class Proveedor(var id: String, var name: String, var description: String) : ViewModel, Serializable{
    constructor() : this("", "", "")

    companion object {
        fun fromMultipleJson(jsonObject: JsonObject): MutableList<Proveedor> {
            val proveedores = mutableListOf<Proveedor>()
            jsonObject.entrySet().forEach {
                proveedores.add(fromJson(it.value))
            }
            return proveedores
        }

        private fun fromJson(json: JsonElement): Proveedor {
            val id = json.asJsonObject.get("id").asString
            val name = json.asJsonObject.get("name").asString
            val description = json.asJsonObject.get("description").asString
            return Proveedor(id, name, description)
        }
    }




}