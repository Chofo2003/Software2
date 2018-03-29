package com.software2.software.features.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.software2.software.R
import com.software2.software.databinding.ActivityHomeBinding
import com.software2.software.features.index.IndexActivity
import com.software2.software.models.Item

/**
 * Created by chofo2003 on 26/03/18.
 */
class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var adapter: RendererRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.mainViewModel = viewModel
        binding.executePendingBindings()
        setupObserver()
        setupList()
    }

    private fun getViewModel() = binding.mainViewModel!!

    private fun setupObserver() {
        getViewModel().images.observe(this, Observer {
            val items = getItems()
            it!!.forEachIndexed { index, s ->
                items[index].url = s
            }
            adapter.setItems(items)
            adapter.notifyDataSetChanged()
        })
        getViewModel().images.value?.let {  } ?: run { getViewModel().getImages() }
    }

    private fun setupList(){
        adapter = RendererRecyclerViewAdapter()
        binding.homeList.layoutManager = LinearLayoutManager(this)
        adapter.registerRenderer(ViewBinder<Item>(R.layout.item_main, Item::class.java,
                this, ViewBinder.Binder { model, finder, _ ->
            finder.find<TextView>(R.id.item_name, {it.text = model.title})
            finder.find<ImageView>(R.id.item_image, { Glide.with(this)
                    .load(model.url).into(it)})
            finder.setOnClickListener {
                val intent = Intent(this, IndexActivity::class.java)
                intent.putExtra("item", model)
                startActivity(intent)
            }
        }))
        binding.homeList.adapter = adapter
    }


    private fun getItems(): MutableList<Item> {
        return mutableListOf(Item("Categorias"), Item("Compras"),
                Item("Productos"), Item("Proveedores"))
    }

}