package com.software2.software.models

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

/**
 * Created by chofo2003 on 26/03/18.
 */
data class Producto(var id: Int, var nombre: String, var descripcion: String, var unidad: String,
                    var categoriaId: String, var proveedorId: String) : ViewModel {
    constructor() : this(0, "", "", "", "", "")
}