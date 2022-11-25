package com.example.cocktailbarapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktailbarapp.databinding.CategoryItemBinding
import com.example.cocktailbarapp.model.meal.MealCategory

class MealCategoryAdapter: RecyclerView.Adapter<MealCategoryAdapter.MealViewHolder>() {
        private var mealCategoryList = ArrayList<MealCategory>()
        lateinit var onItemClick: ((MealCategory) -> Unit)
    class MealViewHolder(val binding: CategoryItemBinding ): RecyclerView.ViewHolder(binding.root)


    fun setMealCategories(mealCategoryList: List<MealCategory>){
        this.mealCategoryList = mealCategoryList as ArrayList<MealCategory>
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealCategoryList[position].strCategoryThumb)
            .into(holder.binding.imgMeal)

        holder.binding.tvCategoryName.text = mealCategoryList[position].strCategory

        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealCategoryList[position])
        }
    }

    override fun getItemCount(): Int {
        return mealCategoryList.size
    }
}