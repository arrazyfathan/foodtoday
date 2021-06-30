package com.example.foody.adapters

import android.view.*
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.R
import com.example.foody.data.database.entities.FavoritesEntity
import com.example.foody.databinding.FavoriteRecipesRowLayoutBinding
import com.example.foody.ui.fragments.favorite.FavoriteFragmentDirections
import com.example.foody.util.RecipesDiffUtil
import kotlinx.android.synthetic.main.favorite_recipes_row_layout.view.*

class FavoriteRecipesAdapter(
    private val requireActivity: FragmentActivity
) : RecyclerView.Adapter<FavoriteRecipesAdapter.MyViewHolder>(), ActionMode.Callback {

    private var favoriteRecipes = emptyList<FavoritesEntity>()

    class MyViewHolder(private val binding: FavoriteRecipesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(favoritesEntity: FavoritesEntity) {
            binding.favoritesEntity = favoritesEntity
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteRecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val selectedRecipe = favoriteRecipes[position]
        holder.bind(selectedRecipe)

        /*
        * Single click listener
        * */
        holder.itemView.favoriteRecipesRowLayout.setOnClickListener {
            val action =
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailsActivity(
                    selectedRecipe.result
                )

            holder.itemView.findNavController().navigate(action)
        }


        /*
        * Long click listener
        * */
        holder.itemView.favoriteRecipesRowLayout.setOnLongClickListener {
            requireActivity.startActionMode(this)
            true
        }


    }

    override fun getItemCount(): Int = favoriteRecipes.size

    fun setData(newFavoriteRecipe: List<FavoritesEntity>) {
        val favoriteRecipesDiffUtil =
            RecipesDiffUtil(favoriteRecipes, newFavoriteRecipe)

        val diffUtilResult = DiffUtil.calculateDiff(favoriteRecipesDiffUtil)
        favoriteRecipes = newFavoriteRecipe
        diffUtilResult.dispatchUpdatesTo(this)
    }

    override fun onCreateActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
        actionMode?.menuInflater?.inflate(R.menu.favorites_contextual_menu, menu)
        return true
    }

    override fun onPrepareActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(actionMode: ActionMode?, item: MenuItem?): Boolean {
        return true
    }

    override fun onDestroyActionMode(actionMode: ActionMode?) {

    }
}