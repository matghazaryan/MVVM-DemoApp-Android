package com.mg.demoapp.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mg.demoapp.data.model.GithubUser
import com.mg.demoapp.databinding.ItemHomeBinding

class HomeViewHolder(parent: View): RecyclerView.ViewHolder(parent) {

    private val binding = ItemHomeBinding.bind(parent)

    fun bindTo(user: GithubUser, viewModel: HomeViewModel) {
        binding.user = user
        binding.viewmodel = viewModel
    }
}