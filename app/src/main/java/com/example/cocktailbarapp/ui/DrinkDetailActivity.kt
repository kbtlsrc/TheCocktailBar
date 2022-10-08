package com.example.cocktailbarapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.cocktailbarapp.R
import com.example.cocktailbarapp.databinding.ActivityDetailDrinkBinding
import com.example.cocktailbarapp.viewmodel.DrinkDetailViewModel



class DrinkDetailActivity: AppCompatActivity() {
    lateinit var binding: ActivityDetailDrinkBinding
    private lateinit var detailViewModel: DrinkDetailViewModel

    private  var drinkId: String = ""
    private  var drinkName: String = ""
    private  var drinkUrl: String= ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewModel = ViewModelProviders.of(this)[DrinkDetailViewModel::class.java]
        binding = ActivityDetailDrinkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDrinkDetailPage()

        detailViewModel.getDrinkDetails(drinkId)
        observeDrinkDetailLiveData()



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

    private fun observeDrinkDetailLiveData(){

        detailViewModel.observeDrinkDetail().observe(this
        ) { t ->
            if (t != null) {
                binding.apply {
                    tvContent.text = t.strInstructions
                    tvCategory.text = "Category:  ${t.strCategory}"

                    if(t.strTags != null){
                        tvAreaInfo.text = "Tags: ${t.strTags}"
                    }
                    else{
                        tvAreaInfo.visibility = View.INVISIBLE
                    }
                    var myList = listOf(t.strIngredient1, t.strIngredient2,t.strIngredient3,t.strIngredient4,t.strIngredient5, t.strIngredient6,
                        t.strIngredient7,t.strIngredient8,
                        t.strIngredient9,t.strIngredient10,
                        t.strIngredient11, t.strIngredient12,
                        t.strIngredient13,t.strIngredient14,
                        t.strIngredient15)


                    var filtered=  myList.filterNotNull()
                    tvNeedsList.text = filtered.toString()

                }


            }
        }
    }
}