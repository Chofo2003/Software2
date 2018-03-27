package com.software2.software.features.index

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
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
import com.software2.software.models.Proveedor

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RendererRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainViewModel = viewModel
        binding.executePendingBindings()
        setupObservers()
        setupList()
    }

    private fun getViewModel() = binding.mainViewModel!!

    private fun setupObservers() {
        getViewModel().proveedores.observe(this, Observer {
            Log.d("Test", it.toString())
            adapter.setItems(it!!)
            adapter.notifyDataSetChanged()
        })
        getViewModel().proveedores.value?.let {} ?: run { getViewModel().getProveedores() }
    }

    private fun setupList() {
        binding.mainList.layoutManager = LinearLayoutManager(this)
        adapter = RendererRecyclerViewAdapter()
        adapter.registerRenderer(ViewBinder<Proveedor>(R.layout.item_index, Proveedor::class.java,
                this, ViewBinder.Binder { model, finder, _ ->
            finder.find<TextView>(R.id.title_text, { it.text = model.nombre })
        }))
        binding.mainList.adapter = adapter
    }
}
