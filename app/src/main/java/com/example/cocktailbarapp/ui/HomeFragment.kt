package com.example.cocktailbarapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cocktailbarapp.databinding.FragmentHomeBinding

import com.example.cocktailbarapp.model.CategoryItems
import com.example.cocktailbarapp.model.Drink
import com.example.cocktailbarapp.viewmodel.HomeViewModel


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var randomDrink: Drink
    lateinit var vm_home: HomeViewModel
    var popularAdapter =  MostPopularAdapter()
    var mealAdapter = MealCategoryAdapter()


    companion object{
        const val DRINK_ID = "com.example.cocktailbarapp.ui.idDrink"
        const val DRINK_NAME = "com.example.cocktailbarapp.ui.nameDrink"
        const val DRINK_URL = "com.example.cocktailbarapp.ui.urlDrink"
        const val MEAL_NAME = "com.example.cocktailbarapp.ui.mealName"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm_home = ViewModelProviders.of(this)[HomeViewModel::class.java]


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePopularItemRV()


        vm_home.getRandomDrink()
        observerRandomDrink()
        drinkDetailClick()


        vm_home.getPopularCategory()
        observePopularItem()
        popularOnClick()

        prepareMealItemRV()
        vm_home.getMealCategory()
        observeMealCategory()
        mealCategoryClick()




    }




    private fun prepareMealItemRV(){
        binding.rvCategorymeal.apply {
            layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            adapter = mealAdapter
        }
    }
    private fun preparePopularItemRV(){
        binding.rvPopular.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
        }
    }

    private fun mealCategoryClick(){
        mealAdapter.onItemClick = {
            mealCategory ->
            val intent =Intent(activity, MealDetailActivity::class.java)
            intent.putExtra(MEAL_NAME, mealCategory.strCategory)
            startActivity(intent)
        }

    }



    private fun drinkDetailClick(){
        binding.drinkCard.setOnClickListener {
            val intent = Intent(activity,DrinkDetailActivity::class.java)
            intent.putExtra(DRINK_ID, randomDrink.idDrink)
            intent.putExtra(DRINK_NAME, randomDrink.strDrink)
            intent.putExtra(DRINK_URL,randomDrink.strDrinkThumb)

            startActivity(intent)
        }
    }


    private fun popularOnClick(){
        popularAdapter.onItemClick = {
                drinks ->
            val intent = Intent(activity,DrinkDetailActivity::class.java)
            intent.putExtra(DRINK_ID, drinks.idDrink)
            intent.putExtra(DRINK_NAME, drinks.strDrink)
            intent.putExtra(DRINK_URL, drinks.strDrinkThumb)
            startActivity(intent)
        }
    }


    private fun observePopularItem(){
        vm_home.observePopularItemMutableList().observe(viewLifecycleOwner
        ) { categoryList->
            popularAdapter.setCocktails(categoryList = categoryList as ArrayList<CategoryItems>)
        }
    }

    private fun observeMealCategory(){
        vm_home.observeMealCategoryMutableList().observe(viewLifecycleOwner, Observer { mealcategories ->

            mealAdapter.setMealCategories(mealcategories)
        })
    }





    private fun observerRandomDrink(){
        vm_home.observeRandomMutableList().observe(viewLifecycleOwner
        ) { t ->
            Glide.with(this@HomeFragment)
                .load(t!!.strDrinkThumb)
                .into(binding.imgRandomDrink)
            this.randomDrink = t
        }
    }



}//class