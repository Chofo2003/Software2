package com.software2.software.features.create

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.software2.software.R
import com.software2.software.models.Categoria
import com.software2.software.models.Producto
import com.software2.software.models.Proveedor
import com.software2.software.repository.Repository
import com.software2.software.repository.sources.FirebaseSource
import com.thejuki.kformmaster.helper.*
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    companion object {
        val PROVIDER = 0
        val CATEGORY = 1
        val PURCHASE = 2
        val PRODUCT = 3
    }

    private lateinit var formBuilder: FormBuildHelper
    private val repository : Repository by lazy { Repository() }
    private var type : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        type = intent.getIntExtra("type", 0)
        setupForm()
        save_button.setOnClickListener {
            setupUpdate()
        }
    }

    private fun setupForm() {
        when (type) {
            PROVIDER -> {
                setupProvider()
            }
            CATEGORY -> {
                setupCategory()
            }
            PRODUCT -> {
                setupProduct()
            }
            PURCHASE -> {}
        }
    }

    private fun setupProduct() {
        repository.getAllProvidersAndCategories(Consumer {
            val providers = it.getValue("providers").filterIsInstance<Proveedor>()
            val categories = it.getValue("categories").filterIsInstance<Categoria>()

            formBuilder = form(this, create_form) {
                text {
                    title = "Nombre"
                }

                text {
                    title = "Descripción"
                }

                number {
                    title = "Unidades"
                }
                dropDown<Proveedor> {
                    title = "Proveedor"
                    dialogTitle = "Selecciona tu proveedor"
                    options = providers
                }
                dropDown<Categoria> {
                    title = "Categoria"
                    dialogTitle = "Selecciona tu categoria (hijo de puta)"
                    options = categories
                }
            }
        }, Consumer {  })
    }

    private fun setupProvider() {
        formBuilder = form(this, create_form) {
            text {
                title = "Nombre"
            }

            text {
                title = "Descripción"
            }
        }
    }

    private fun setupCategory() {
        formBuilder = form(this, create_form) {
            text {
                title = "Nombre"
            }

            text {
                title = "Descripción"
            }
        }
    }

    private fun setupUpdate() {
        when (type) {
            PROVIDER -> {
                createProvider()
            }
            CATEGORY -> {
//                createCategory()
            }
            PRODUCT -> {
                createProduct()
            }
            PURCHASE -> {}
        }
    }

    fun createProvider() {
        val name = formBuilder.getElementAtIndex(0)!!.value.toString()
        val description = formBuilder.getElementAtIndex(1)!!.value.toString()
        val newProvider = Proveedor(name, description)
        Repository().createProvider(newProvider, Consumer {
            Toast.makeText(this, "Se ha creado", Toast.LENGTH_SHORT).show()
        }, Consumer {})
    }

    fun createProduct() {
        val name = formBuilder.getElementAtIndex(0)!!.value.toString()
        val description = formBuilder.getElementAtIndex(1)!!.value.toString()
        val units = formBuilder.getElementAtIndex(2)!!.value.toString()
        val provider = formBuilder.getElementAtIndex(3)!!.value as Proveedor

        Log.d("PROVEEDOR", provider.id + " " + provider.nombre)
    }
}
