package com.software2.software.models

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.gson.JsonElement
import com.google.gson.JsonObject

/**
 * Created by chofo2003 on 26/03/18.
 */
data class Proveedor(var id: String, var nombre: String, var descripcion: String) : ViewModel {
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