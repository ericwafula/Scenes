package tech.ericwathome.scenes.feature_scenes.presentation.ui.fragments

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
import tech.ericwathome.scenes.feature_scenes.domain.model.Photo
import tech.ericwathome.scenes.feature_scenes.presentation.ui.adapter.FavoritesAdapter
import tech.ericwathome.scenes.feature_scenes.presentation.viewmodels.FavoritesFragmentViewModel
import tech.ericwathome.tours.databinding.FragmentFavoritesBinding
import tech.ericwathome.scenes.util.toast

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    companion object {
        private val TAG = this::class.simpleName
    }

    private val viewModel: FavoritesFragmentViewModel by viewModels()

    private lateinit var binding: FragmentFavoritesBinding

    private lateinit var favoritesAdapter: FavoritesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        initializeFavoritesList()
        return binding.root
    }

    private fun initializeFavoritesList() {
        favoritesAdapter = FavoritesAdapter(listOf()) { photo, position ->
            deletePhoto(photo, position)
        }
          lifecycleScope.launchWhenCreated {
            viewModel.bookmarkedPhotos
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    favoritesAdapter.favorites = it
                }
        }

        binding.favoritesRecyclerview.apply {
            adapter = favoritesAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
        }

    }

    private fun deletePhoto(photo: Photo, position: Int) {
        var photos: List<Photo>? = null
        var currentPosition: Int = -1
        viewModel.deletePhoto(photo)
        requireContext().toast("photo deleted successfully")

        lifecycleScope.launchWhenCreated {
            viewModel.bookmarkedPhotos.collect {
                currentPosition = it.size
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.bookmarkedPhotos
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    photos = it
                }
        }
    }
}