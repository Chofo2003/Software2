package com.software2.software.models

/**
 * Created by chofo2003 on 26/03/18.
 */
data class Compra(var id: String, var fecha: String, var proveedorId: String, var estado: String,
                  var productos: MutableList<String>, var total: Float) {
}