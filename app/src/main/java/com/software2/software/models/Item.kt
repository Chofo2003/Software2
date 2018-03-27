package com.software2.software.models

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

/**
 * Created by chofo2003 on 26/03/18.
 */
data class Item(var title: String, var url: String) : ViewModel {
    constructor(title: String) : this(title, "")
}