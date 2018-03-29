package com.software2.software.models

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

/**
 * Created by chofo2003 on 26/03/18.
 */
data class Categoria(var id: Int, var nombre: String, var descripcion: String) : ViewModel {
    constructor() : this(0, "", "")
}