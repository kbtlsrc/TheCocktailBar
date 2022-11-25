package com.example.cocktailbarapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailbarapp.MainActivity
import com.example.cocktailbarapp.R
import com.example.cocktailbarapp.databinding.FragmentFavouritesBinding
import com.example.cocktailbarapp.ui.adapter.FavouriteAdapter
import com.example.cocktailbarapp.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar


class FavouritesFragment : Fragment() {
        private lateinit var binding: FragmentFavouritesBinding
        private lateinit var viewModel: HomeViewModel
        private lateinit var favAdapter: FavouriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = FragmentFavouritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRv()
        observeFav()

         val itemTouchHelper = object: ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,
         ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
             override fun onMove(
                 recyclerView: RecyclerView,
                 viewHolder: RecyclerView.ViewHolder,
                 target: RecyclerView.ViewHolder
             ) =  true

             override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                 viewModel.deleteDrink(favAdapter.differ.currentList[position])


                 Snackbar.make(requireView(),"Drink deleted",Snackbar.LENGTH_LONG).setAction(
                     "UNDO",View.OnClickListener {
                         viewModel.insertDrink(favAdapter.differ.currentList[position])
                     }
                 ).show()
             }
         }

        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rvFavDrink)
    }

    private fun prepareRv(){

        favAdapter = FavouriteAdapter()
        binding.rvFavDrink.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = favAdapter
        }
    }


    private fun observeFav(){

        viewModel.observeFavDrinkLiveData().observe(requireActivity(), Observer {
            drinks ->
             favAdapter.differ.submitList(drinks)
        })
    }


}