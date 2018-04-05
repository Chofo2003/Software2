package com.software2.software.models

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.io.Serializable

/**
 * Created by chofo2003 on 26/03/18.
 */
data class Categoria(var id: String, var nombre: String, var descripcion: String) : ViewModel, Serializable {
    constructor() : this("", "", "")

    companion object {
        fun fromMultipleJson(jsonObject: JsonObject): MutableList<Categoria> {
            val categorias = mutableListOf<Categoria>()
            jsonObject.entrySet().forEach {
                categorias.add(fromJson(it.value))
            }
            return categorias
        }

        private fun fromJson(json: JsonElement): Categoria {
            val id = json.asJsonObject.get("id").asString
            val nombre = json.asJsonObject.get("name").asString
            val descripcion = json.asJsonObject.get("description").asString
            return Categoria(id, nombre, descripcion)
        }
    }
}