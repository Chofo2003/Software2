package com.software2.software.models

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.gson.JsonElement
import com.google.gson.JsonObject

/**
 * Created by chofo2003 on 26/03/18.
 */
data class Compra(var id: String, var fecha: String, var proveedorId: String, var estado: String,
                  var productos: MutableList<String>, var total: Float) : ViewModel {

    companion object {
        fun fromMultipleJson(jsonObject: JsonObject): MutableList<Compra> {
            val compras = mutableListOf<Compra>()
            jsonObject.entrySet().forEach {
                compras.add(fromJson(it.value))
            }
            return compras
        }

        private fun fromJson(json: JsonElement): Compra {
            val id = json.asJsonObject.get("id").asString
            val fecha = json.asJsonObject.get("fecha").asString
            val estado = json.asJsonObject.get("estado").asString
//            val productos = json.asJsonObject.get("unidad").asString
            val total = json.asJsonObject.get("total").asFloat
            val proveedorId = json.asJsonObject.get("proveedorIr").asString
            return Compra(id, fecha, proveedorId, estado, mutableListOf(), total)
        }
    }
}