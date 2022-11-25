package com.example.cocktailbarapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktailbarapp.databinding.PopularItemBinding
import com.example.cocktailbarapp.model.drink.CategoryItems



class MostPopularAdapter() : RecyclerView.Adapter<MostPopularAdapter.PopularViewHolder>() {
    lateinit var onItemClick: ((CategoryItems) -> Unit)
    private var categoryList = ArrayList<CategoryItems>()


    fun setCocktails(categoryList: ArrayList<CategoryItems>){

        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    class PopularViewHolder(val binding: PopularItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(categoryList[position].strDrinkThumb)
            .into(holder.binding.imgPopularMeal)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(categoryList[position])
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }


}