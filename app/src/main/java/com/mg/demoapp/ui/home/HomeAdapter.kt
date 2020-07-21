package com.mg.demoapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hamedsafari.home.views.HomeItemDiffCallback
import com.mg.demoapp.R
import com.mg.demoapp.data.model.GithubUser

class HomeAdapter(private val viewModel: HomeViewModel): RecyclerView.Adapter<HomeViewHolder>() {

    private val users: MutableList<GithubUser> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false))

    override fun getItemCount(): Int
            = users.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int)
            = holder.bindTo(users[position], viewModel)

    // ---

    fun updateData(items: List<GithubUser>) {
        val diffCallback = HomeItemDiffCallback(users, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        users.clear()
        users.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }
}