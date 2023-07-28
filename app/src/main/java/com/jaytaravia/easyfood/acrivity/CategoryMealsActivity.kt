package com.jaytaravia.easyfood.acrivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.jaytaravia.easyfood.R
import com.jaytaravia.easyfood.adapter.CategoryMealsAdapter
import com.jaytaravia.easyfood.databinding.ActivityCategoryMealsBinding
import com.jaytaravia.easyfood.fragments.HomeFragment
import com.jaytaravia.easyfood.viewModel.CategoryMealsViewsModel

class CategoryMealsActivity : AppCompatActivity() {

    lateinit var binding : ActivityCategoryMealsBinding
    lateinit var categoryMealsViewModel : CategoryMealsViewsModel
    lateinit var categoryMealsAdapter : CategoryMealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()

        categoryMealsViewModel= ViewModelProvider(this@CategoryMealsActivity)[CategoryMealsViewsModel::class.java]

        categoryMealsViewModel.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)

        categoryMealsViewModel.observeMealsLiveData().observe(this, Observer { mealsList->
            binding.tvCategoryCount.text = mealsList.size.toString()
            categoryMealsAdapter.setMealsList(mealsList)
        })
    }

    private fun prepareRecyclerView() {
        categoryMealsAdapter = CategoryMealsAdapter()
        binding.rvMeals.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL, false)
            adapter = categoryMealsAdapter
        }
    }
}