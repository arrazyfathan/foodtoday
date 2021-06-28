package com.example.foody.ui.fragments.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.R
import com.example.foody.adapters.FavoriteRecipesAdapter
import com.example.foody.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.view.*


@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val mAdapter: FavoriteRecipesAdapter by lazy { FavoriteRecipesAdapter() }
    private val mainViewMode: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        setupRecyclerView(view.favoriteRecipesRecyclerView)
        mainViewMode.readFavoriteRecipes.observe(viewLifecycleOwner, { favoriteEntity ->
            mAdapter.setData(favoriteEntity)
        })
        return view
    }

    private fun setupRecyclerView(recycler: RecyclerView) {
        recycler.adapter = mAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }


}