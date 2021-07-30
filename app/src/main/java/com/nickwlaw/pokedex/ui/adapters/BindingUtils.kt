package com.nickwlaw.pokedex.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    recyclerView.layoutManager = GridLayoutManager(
        recyclerView.context,
        2
    )

    recyclerView.setHasFixedSize(true)
    recyclerView.adapter = adapter
}

@BindingAdapter("loadImageUrl")
fun loadImageUrl(imageView: ImageView, path: String?) {
    path?.let {
        Glide.with(imageView.context).load(path).into(imageView)
    }
}
