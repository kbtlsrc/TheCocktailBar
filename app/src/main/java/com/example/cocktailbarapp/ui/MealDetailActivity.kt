package com.example.cocktailbarapp.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailbarapp.R
import com.example.cocktailbarapp.databinding.ActivitiyDetailMealBinding
import com.example.cocktailbarapp.viewmodel.MealDetailViewModel

class MealDetailActivity: AppCompatActivity() {
    lateinit var binding: ActivitiyDetailMealBinding
    private lateinit var mealDetailViewModel: MealDetailViewModel
    lateinit var mealDetailAdapter: MealDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mealDetailViewModel =ViewModelProviders.of(this)[MealDetailViewModel::class.java]
        binding = ActivitiyDetailMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareMealDetailRV()
        mealDetailViewModel.getMealDetailCategory(intent.getStringExtra(HomeFragment.MEAL_NAME)!!)
        observeMealDetailCategory()

    }

    private fun observeMealDetailCategory(){
        mealDetailViewModel.observeMealDetailMutableList().observe(this, Observer {
            mealDetailList->
                mealDetailAdapter.setMealDetail(mealDetailList)
        })

    }

    private fun prepareMealDetailRV(){
        mealDetailAdapter = MealDetailAdapter()
        binding.rvMealDetail.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
       adapter = mealDetailAdapter
        }
    }

}