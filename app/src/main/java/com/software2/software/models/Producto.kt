package com.software2.software.models

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.gson.JsonElement
import com.google.gson.JsonObject

/**
 * Created by chofo2003 on 26/03/18.
 */
data class Producto(var id: String, var nombre: String, var descripcion: String, var unidad: String,
                    var categoriaId: String, var proveedorId: String) : ViewModel {
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
            val nombre = json.asJsonObject.get("nombre").asString
            val descripcion = json.asJsonObject.get("descripcion").asString
            val unidad = json.asJsonObject.get("unidad").asString
            val categoriaId = json.asJsonObject.get("categoriaId").asString
            val proveedorId = json.asJsonObject.get("proveedorIr").asString
            return Producto(id, nombre, descripcion, unidad, categoriaId, proveedorId)
        }
    }

}