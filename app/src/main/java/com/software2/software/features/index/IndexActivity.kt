package com.software2.software.features.index

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.TextView
import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.software2.software.R
import com.software2.software.databinding.ActivityMainBinding
import com.software2.software.features.show.ShowActivity
import com.software2.software.models.*
import java.io.Serializable

class IndexActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RendererRecyclerViewAdapter
    private lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(IndexViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainViewModel = viewModel
        binding.executePendingBindings()
        item = intent.getSerializableExtra("item") as Item
        setupObservers()
        setupList()
    }

    private fun getViewModel() = binding.mainViewModel!!

    private fun setupObservers() {
        when (item.title) {
            "Categorias" -> {
                getViewModel().categorias.observe(this, Observer {
                    Log.d("Probando", "Entre aqui")
                    adapter.setItems(it!!)
                    adapter.notifyDataSetChanged()
                })
                getViewModel().categorias.value?.let {} ?: run {
                    getViewModel().getCategorias() }
            }
            "Compras" -> {
                getViewModel().compras.observe(this, Observer {
                    adapter.setItems(it!!)
                    adapter.notifyDataSetChanged()
                })
                getViewModel().compras.value?.let {} ?: run { getViewModel().getCompras() }
            }
            "Proveedores" -> {
                getViewModel().proveedores.observe(this, Observer {
                    adapter.setItems(it!!)
                    adapter.notifyDataSetChanged()
                })
                getViewModel().proveedores.value?.let {} ?: run { getViewModel().getProveedores() }
            }
            "Productos" -> {
                getViewModel().productos.observe(this, Observer {
                    adapter.setItems(it!!)
                    adapter.notifyDataSetChanged()
                })
                getViewModel().productos.value?.let {} ?: run { getViewModel().getProductos() }
            }
        }
    }

    private fun setupList() {
        binding.mainList.layoutManager = LinearLayoutManager(this)
        adapter = RendererRecyclerViewAdapter()
        when (item.title) {
            "Categorias" -> {
                adapter.registerRenderer(ViewBinder<Categoria>(R.layout.item_index, Categoria::class.java,
                        this, ViewBinder.Binder { model, finder, _ ->
                    finder.find<TextView>(R.id.title_text, { it.text = model.nombre })
                    finder.setOnClickListener {
                        startShowActivity(model)
                    }
                }))
            }
            "Compras" -> {
                adapter.registerRenderer(ViewBinder<Compra>(R.layout.item_index, Compra::class.java,
                        this, ViewBinder.Binder { model, finder, _ ->
                    finder.find<TextView>(R.id.title_text, { it.text = model.id })
                }))
            }
            "Proveedores" -> {
                adapter.registerRenderer(ViewBinder<Proveedor>(R.layout.item_index, Proveedor::class.java,
                        this, ViewBinder.Binder { model, finder, _ ->
                    finder.find<TextView>(R.id.title_text, { it.text = model.name })
                    finder.setOnClickListener {
                        startShowActivity(model)
                    }
                }))
            }
            "Productos" -> {
                adapter.registerRenderer(ViewBinder<Producto>(R.layout.item_index, Producto::class.java,
                        this, ViewBinder.Binder { model, finder, _ ->
                    finder.find<TextView>(R.id.title_text, { it.text = model.nombre })
                }))
            }
        }
        binding.mainList.adapter = adapter
    }

    private fun startShowActivity(any: Serializable) {
        val intent = Intent(this, ShowActivity::class.java)
        intent.putExtra("item", any)
        startActivity(intent)
    }
}
