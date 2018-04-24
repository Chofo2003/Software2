package com.software2.software.features.show

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.software2.software.R
import com.software2.software.features.home.HomeActivity
import com.software2.software.models.Categoria
import com.software2.software.models.Producto
import com.software2.software.models.Proveedor
import com.software2.software.repository.Repository
import com.thejuki.kformmaster.helper.*
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
            setupDelete()
        }
        setupForm()
    }

    fun setupDelete() {
        when (item) {
            is Proveedor -> {
                deleteProvider()
                startActivity(Intent(this, HomeActivity::class.java))
                this.finish()
                Toast.makeText(this, "Se ha borrado", Toast.LENGTH_SHORT).show()
            }
            is Categoria -> {
                deleteCategory()
                startActivity(Intent(this, HomeActivity::class.java))
                this.finish()
                Toast.makeText(this, "Se ha borrado", Toast.LENGTH_SHORT).show()
            }
            is Producto -> {
                deleteProduct()
                startActivity(Intent(this, HomeActivity::class.java))
                this.finish()
                Toast.makeText(this, "Se ha borrado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setupUpdate() {
        when (item) {
            is Proveedor -> {
                updateProvider()
            }
            is Categoria -> {
                updateCategory()
            }
            is Producto -> {
                updateProduct()
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
            is Producto -> {
                setupProduct()
            }
        }
    }

    fun setupProduct() {
        val item = this.item as Producto
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
            number {
                title = "Unidades"
                value = item.unity
            }
            number {
                title = "Categoria"
                value = item.categoryId
            }
            number {
                title = "Proveedor"
                value = item.providerId
            }
        }
    }

    fun setupProvider() {
        val item = this.item as Proveedor
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

    fun setupCategory() {
        val item = this.item as Categoria
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

    fun updateCategory() {
        val item = this.item as Categoria
        val name = formBuilder.getElementAtIndex(0)!!.value.toString()
        val description = formBuilder.getElementAtIndex(1)!!.value.toString()
        val newCategory = Categoria(item.id, name, description)
        Repository().updateCategory(newCategory, Consumer {
            Toast.makeText(this, "Se ha actualizado", Toast.LENGTH_SHORT).show()
        }, Consumer {})
    }

    fun updateProduct() {
        val item = this.item as Producto
        val name = formBuilder.getElementAtIndex(0)!!.value.toString()
        val description = formBuilder.getElementAtIndex(1)!!.value.toString()
        val unity = formBuilder.getElementAtIndex(2)!!.value.toString()
        val categoryId = formBuilder.getElementAtIndex(3)!!.value.toString()
        val providerId = formBuilder.getElementAtIndex(4)!!.value.toString()
        val newProduct = Producto(item.id, name, description, unity, categoryId, providerId)
        Repository().updateProduct(newProduct, Consumer {
            Toast.makeText(this, "Se ha actualizado", Toast.LENGTH_SHORT).show()
        }, Consumer {})
    }

    fun deleteProvider() {
        val item = this.item as Proveedor
        val name = formBuilder.getElementAtIndex(0)!!.value.toString()
        val description = formBuilder.getElementAtIndex(1)!!.value.toString()
        val provider = Proveedor(item.id, name, description)
        Repository().deleteProvider(provider, Consumer {
            Toast.makeText(this, "Se ha borrado", Toast.LENGTH_SHORT).show()
        }, Consumer {})
    }

    fun deleteProduct() {
        val item = this.item as Producto
        val name = formBuilder.getElementAtIndex(0)!!.value.toString()
        val description = formBuilder.getElementAtIndex(1)!!.value.toString()
        val unity = formBuilder.getElementAtIndex(2)!!.value.toString()
        val categoryId = formBuilder.getElementAtIndex(3)!!.value.toString()
        val providerId = formBuilder.getElementAtIndex(4)!!.value.toString()
        val product = Producto(item.id, name, description, unity, categoryId, providerId)
        Repository().deleteProduct(product, Consumer {
            Toast.makeText(this, "Se ha borrado", Toast.LENGTH_SHORT).show()
        }, Consumer {})
    }

    fun deleteCategory() {
        val item = this.item as Categoria
        val name = formBuilder.getElementAtIndex(0)!!.value.toString()
        val description = formBuilder.getElementAtIndex(1)!!.value.toString()
        val category = Categoria(item.id, name, description)
        Repository().deleteCategory(category, Consumer {
            Toast.makeText(this, "Se ha borrado", Toast.LENGTH_SHORT).show()
        }, Consumer {})
    }
}