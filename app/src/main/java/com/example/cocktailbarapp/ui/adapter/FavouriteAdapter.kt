package com.example.cocktailbarapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktailbarapp.databinding.PopularItemBinding
import com.example.cocktailbarapp.model.drink.Drink

class FavouriteAdapter: RecyclerView.Adapter<FavouriteAdapter.FavViewHolder>(){


    class FavViewHolder(val binding: PopularItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        return FavViewHolder(PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
     val drink = differ.currentList[position]
        Glide.with(holder.itemView).load(drink.strDrinkThumb).into(holder.binding.imgPopularMeal)
       holder.binding.tvName.text = drink.strDrink
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }


    private val diffUtil = object : DiffUtil.ItemCallback<Drink>(){
        override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem.idDrink == newItem.idDrink

        }

        override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean {
                    return oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this, diffUtil)




}