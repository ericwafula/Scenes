package tech.ericwathome.tours.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import tech.ericwathome.tours.adapter.FavoritesAdapter
import tech.ericwathome.tours.data.DataManager
import tech.ericwathome.tours.databinding.FragmentFavoritesBinding
import tech.ericwathome.tours.model.SceneInfo
import tech.ericwathome.tours.model.viewmodels.FavoritesFragmentViewModel
import java.util.*

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
        favoritesAdapter = FavoritesAdapter(requireContext(), listOf())
          lifecycleScope.launchWhenCreated {
            viewModel.bookmarkedPhotos
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    favoritesAdapter.favorites = it
                    Log.d(TAG, "initializeFavoritesList: $it")
                }

        }

        binding.favoritesRecyclerview.apply {
            adapter = favoritesAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
        }

    }
}