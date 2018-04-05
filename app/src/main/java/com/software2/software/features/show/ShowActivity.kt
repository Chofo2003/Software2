package com.software2.software.features.show

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.software2.software.R
import com.software2.software.models.Categoria
import com.software2.software.models.Proveedor
import com.software2.software.repository.Repository
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.helper.form
import com.thejuki.kformmaster.helper.text
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_show.*
import java.io.Serializable

/**
 * Created by chofo2003 on 28/03/18.
 */
class ShowActivity : AppCompatActivity() {

    private lateinit var item : Serializable
    private lateinit var formBuilder: FormBuildHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        setSupportActionBar(show_toolbar)
        item = intent.getSerializableExtra("item")
        save_button.setOnClickListener {
            setupUpdate()
        }
        delete_button.setOnClickListener {
            onBackPressed()
        }
        setupForm()
    }

    fun setupUpdate() {
        when (item) {
            is Proveedor -> {
                updateProvider()
            }
            is Categoria -> {
//                updateCategory()
            }
        }
    }

    fun setupForm() {
        when (item) {
            is Proveedor -> {
                setupProvider()
            }
            is Categoria -> {
                setupCategory()
            }
        }
    }

    fun setupCategory() {
        val item = this.item as Categoria
        supportActionBar!!.title = item.id
        formBuilder = form(this, show_form) {
            text {
                title = "Nombre"
                value = item.nombre
            }
            text {
                title = "Descripción"
                value = item.descripcion
            }
        }
    }

    fun setupProvider() {
        val item = this.item as Proveedor
        supportActionBar!!.title = item.id
        formBuilder = form(this, show_form) {
            text {
                title = "Nombre"
                value = item.name
            }
            text {
                title = "Descripción"
                value = item.description
            }
        }
    }

    fun updateProvider() {
        val item = this.item as Proveedor
        val name = formBuilder.getElementAtIndex(0)!!.value.toString()
        val description = formBuilder.getElementAtIndex(1)!!.value.toString()
        val newProvider = Proveedor(item.id, name, description)
        Repository().updateProvider(newProvider, Consumer {
            Toast.makeText(this, "Se ha actualizado", Toast.LENGTH_SHORT).show()
        }, Consumer {})
    }
}