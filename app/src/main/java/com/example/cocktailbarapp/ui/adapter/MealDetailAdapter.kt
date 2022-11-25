package com.example.cocktailbarapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktailbarapp.databinding.MealTypeItemBinding
import com.example.cocktailbarapp.model.meal.Meal

class MealDetailAdapter: RecyclerView.Adapter<MealDetailAdapter.MealDetailViewHolder>() {
        private var mealDetailList = ArrayList<Meal>()
    class MealDetailViewHolder(val binding: MealTypeItemBinding): RecyclerView.ViewHolder(binding.root)


    fun setMealDetail(mealDetailList: List<Meal>){
        this.mealDetailList = mealDetailList as ArrayList<Meal>
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealDetailViewHolder {
        return MealDetailViewHolder(MealTypeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MealDetailViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealDetailList[position].strMealThumb)
            .into(holder.binding.imgMealType)

        holder.binding.tvMealType.text = mealDetailList[position].strMeal
    }

    override fun getItemCount(): Int {
        return mealDetailList.size
    }
}