package com.software2.software.models

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import java.io.Serializable

/**
 * Created by chofo2003 on 26/03/18.
 */
data class Item(var title: String, var url: String) : ViewModel, Serializable {
    constructor(title: String) : this(title, "")
}