package com.software2.software.models

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.io.Serializable

/**
 * Created by chofo2003 on 26/03/18.
 */
data class Proveedor(var id: String, var nombre: String, var descripcion: String) : ViewModel, Serializable{
    constructor() : this("", "", "")

    constructor(nombre: String,descripcion: String): this("",nombre,descripcion)

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
            val name = json.asJsonObject.get("nombre").asString
            val description = json.asJsonObject.get("descripcion").asString
            return Proveedor(id, name, description)
        }
    }

    override fun toString(): String {
        return this.nombre
    }

}