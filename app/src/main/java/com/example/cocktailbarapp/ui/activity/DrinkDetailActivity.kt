package com.example.cocktailbarapp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cocktailbarapp.R
import com.example.cocktailbarapp.databinding.ActivityDetailDrinkBinding
import com.example.cocktailbarapp.db.DrinkDb
import com.example.cocktailbarapp.model.drink.Drink
import com.example.cocktailbarapp.ui.fragment.HomeFragment
import com.example.cocktailbarapp.viewmodel.DrinkDetailViewModel
import com.example.cocktailbarapp.viewmodel.DrinkDetailViewModelFactory


class DrinkDetailActivity: AppCompatActivity() {
    lateinit var binding: ActivityDetailDrinkBinding
    private lateinit var detailViewModel: DrinkDetailViewModel

    private  var drinkId: String = ""
    private  var drinkName: String = ""
    private  var drinkUrl: String= ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailDrinkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drinkDb =DrinkDb.getInstance(this)
        val viewModelFactory = DrinkDetailViewModelFactory(drinkDb)

        detailViewModel = ViewModelProvider(this, viewModelFactory)[DrinkDetailViewModel::class.java]

        getDrinkDetailPage()

        detailViewModel.getDrinkDetails(drinkId)
        observeDrinkDetailLiveData()


        onFavourite()


    }
    fun onFavourite(){
        binding.fav.setOnClickListener {
            drinkToSave?.let {
                detailViewModel.insertDrink(it)
                Toast.makeText(this, " ${it.strDrink} saved to fav", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDrinkDetailPage(){
        val intent = intent
        drinkId = intent.getStringExtra(HomeFragment.DRINK_ID)!!
        drinkName = intent.getStringExtra(HomeFragment.DRINK_NAME)!!
        drinkUrl = intent.getStringExtra(HomeFragment.DRINK_URL)!!



        Glide.with(applicationContext)
            .load(drinkUrl)
            .into(binding.imgDrink)

        binding.collapsingToolbar.title = drinkName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))




    }

    private var drinkToSave: Drink? = null

    private fun observeDrinkDetailLiveData(){

        detailViewModel.observeDrinkDetail().observe(this
        ) { t ->
            if (t != null) {
                binding.apply {
                    drinkToSave = t
                    tvContent.text = t.strInstructions
                    tvCategory.text = "Category:  ${t.strCategory}"

                    if (t.strTags != null) {
                        tvAreaInfo.text = "Tags: ${t.strTags}"
                    } else {
                        tvAreaInfo.visibility = View.INVISIBLE
                    }
                    var myList = listOf(
                        t.strIngredient1,
                        t.strIngredient2,
                        t.strIngredient3,
                        t.strIngredient4,
                        t.strIngredient5,
                        t.strIngredient6,
                        t.strIngredient7,
                        t.strIngredient8,
                        t.strIngredient9,
                        t.strIngredient10,
                        t.strIngredient11,
                        t.strIngredient12,
                        t.strIngredient13,
                        t.strIngredient14,
                        t.strIngredient15
                    )


                    var filtered = myList.filterNotNull()
                    tvNeedsList.text = filtered.toString()

                }


            }
        }
    }
}