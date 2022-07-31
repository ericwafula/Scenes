package tech.ericwathome.tours.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tech.ericwathome.tours.adapter.PhotosPagingAdapter
import tech.ericwathome.tours.databinding.FragmentScenesBinding
import tech.ericwathome.tours.model.Photo
import tech.ericwathome.tours.model.viewmodels.ScenesFragmentViewModel
import tech.ericwathome.tours.util.toast

@AndroidEntryPoint
class ScenesFragment : Fragment() {
    private lateinit var binding: FragmentScenesBinding
    private val viewModel: ScenesFragmentViewModel by viewModels()
    private lateinit var pagingAdapter: PhotosPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScenesBinding.inflate(inflater, container, false)
        initializeSceneList()
        return binding.root
    }

    private fun initializeSceneList() {
        pagingAdapter = PhotosPagingAdapter {
            bookmarkPhoto(it)
        }
        binding.scenesRecyclerview.apply {
            adapter = pagingAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
        }

        lifecycleScope.launchWhenCreated {
            viewModel.photos
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    it?.let {
                        pagingAdapter.submitData(it)
                    }
                }
        }
    }

    private fun bookmarkPhoto(photo: Photo) {
        viewModel.addToBookmarks(photo)
        requireContext().toast("${photo.id} added to bookmarks")
    }
}