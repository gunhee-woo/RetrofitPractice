package com.example.retrofitpractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpractice.databinding.MainListItemBinding
import com.example.retrofitpractice.models.GithubRepo

class MainAdapter : RecyclerView.Adapter<MainAdapterViewHolder>() {
    private val githubRepos = mutableListOf<GithubRepo>()

    fun updateGithubRepos(repos: List<GithubRepo>) {
        githubRepos.clear()
        githubRepos.addAll(repos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_list_item, parent, false)
        return MainAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapterViewHolder, position: Int) {
        val repo = githubRepos[position]
        holder.bind(repo)
//        holder.itemView.setOnClickListener {
//
//        }
    }

    override fun getItemCount(): Int {
        return githubRepos.size
    }

}

class MainAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val binding = MainListItemBinding.bind(itemView)
    fun bind(repo: GithubRepo) {
        binding.idTv.text = repo.id
        binding.nameTv.text = repo.name
        binding.ownerTv.text = repo.owner.login
        binding.privateTv.text = repo.private.toString()
    }
}