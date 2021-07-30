package com.nickwlaw.pokedex.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.nickwlaw.pokedex.data.models.domain.Pokemon
import com.nickwlaw.pokedex.BR
import com.nickwlaw.pokedex.R

class PokemonAdapter(var pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            viewType,
            parent,
            false
        )

        return ViewHolder(binding).listen { position, type ->
            val item = pokemonList[position]

            item.sprite.displayUrl = if (item.sprite.displayUrl == item.sprite.frontUrl) {
                item.sprite.backUrl
            } else {
                item.sprite.frontUrl
            }

            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_pokemon
    }

    fun setItems(pokemonList: List<Pokemon>) {
        this.pokemonList = pokemonList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pokemon?) {
            binding.apply {
                setVariable(BR.pokemon, item)
                executePendingBindings()
            }
        }
    }

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }
}