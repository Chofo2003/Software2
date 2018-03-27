package com.software2.software.models

/**
 * Created by chofo2003 on 26/03/18.
 */
data class Categoria(var id: Int, var nombre: String, var descripcion: String) {
    constructor() : this(0, "", "")
}