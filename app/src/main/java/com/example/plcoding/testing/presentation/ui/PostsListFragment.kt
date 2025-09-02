package com.example.plcoding.testing.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plcoding.testing.data.utils.Resource
import com.example.plcoding.testing.databinding.FragmentPostsListBinding
import com.example.plcoding.testing.presentation.adapter.PostAdapter
import com.example.plcoding.testing.presentation.viewModel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsListFragment : Fragment() {
    private var _binding: FragmentPostsListBinding? = null
    private val binding get() = _binding!!
    private lateinit var postsAdapter: PostAdapter
    private val viewModel: PostsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostsListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postsAdapter = PostAdapter()

        setUpPostsRecyclerView()
        setUpSwipeRefresh()
        observePosts()
        onClickListener()

    }

    private fun setUpPostsRecyclerView() {
        binding.rcvPosts.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observePosts() {
        lifecycleScope.launchWhenStarted {
            viewModel.posts.collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        binding.loadingProgressBar.visibility = View.GONE
                        resource.data?.let { postsAdapter.submitList(it) }
                    }

                    is Resource.Loading -> binding.loadingProgressBar.visibility = View.VISIBLE
                    is Resource.Error -> {
                        binding.loadingProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        binding.edtSearch.addTextChangedListener { text ->
            postsAdapter.filter(text.toString())
        }
    }

    private fun setUpSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            try {
                viewModel.refresh()
            } finally {
                binding.swipeRefreshLayout.isRefreshing = false
            }

        }
    }

    private fun onClickListener() {
        postsAdapter.setOnClickListener {
            val action = PostsListFragmentDirections.actionFirstFragmentToSecondFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}