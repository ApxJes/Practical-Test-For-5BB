package com.example.plcoding.testing.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.plcoding.testing.databinding.PostsListLayoutBinding
import com.example.plcoding.testing.domain.model.PostsVo

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostsViewHolder>() {

    private var differCallBack = object: DiffUtil.ItemCallback<PostsVo>() {
        override fun areItemsTheSame(oldItem: PostsVo, newItem: PostsVo) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: PostsVo, newItem: PostsVo) = oldItem.title == newItem.title
    }

    val differ = AsyncListDiffer(this, differCallBack)

    private var fullList: List<PostsVo> = emptyList()

    fun submitList(list: List<PostsVo>) {
        fullList = list
        differ.submitList(list)
    }

    fun filter(query: String) {
        val filtered = if (query.isEmpty()) fullList
        else fullList.filter { it.title.contains(query, ignoreCase = true) }
        differ.submitList(filtered)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostsViewHolder(
        PostsListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = differ.currentList[position]
        holder.binding.txvTitle.text = post.title
        holder.binding.txvBody.text = post.body
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(post) }
        }
    }

    override fun getItemCount() = differ.currentList.size

    private var onItemClickListener: ((PostsVo) -> Unit)? = null
    fun setOnClickListener(listener: (PostsVo) -> Unit) {
        onItemClickListener = listener
    }

    inner class PostsViewHolder(val binding: PostsListLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}
