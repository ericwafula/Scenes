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

    private var recyclerView: RecyclerView? = null
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
        lifecycleScope.launchWhenCreated {
            viewModel.bookmarkedPhotos
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    Log.d(TAG, "initializeFavoritesList: $it")
                }

        }

        favoritesAdapter = FavoritesAdapter(requireContext(), DataManager.favoriteScenes)
        binding.favoritesRecyclerview.apply {
            adapter = favoritesAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
        }

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val fromPosition = viewHolder.oldPosition
            val toPosition = target.layoutPosition

            Collections.swap(DataManager.favoriteScenes, fromPosition, toPosition)
            favoritesAdapter.notifyItemMoved(fromPosition, toPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.oldPosition
            val deletedScene = DataManager.favoriteScenes[position]

            deletedScene(position)
            notifyItemsChanged(deletedScene, false)

            Snackbar.make(recyclerView!!, "Deleted", Snackbar.LENGTH_LONG)
                .setAction("undo") {
                    undoDelete(deletedScene, position)
                    notifyItemsChanged(deletedScene, true)
                }
                .show()
        }

    })

    private fun undoDelete(deletedScene: SceneInfo, position: Int) {
        DataManager.favoriteScenes.add(position, deletedScene)
        favoritesAdapter.notifyItemInserted(position)
        favoritesAdapter.notifyItemRangeChanged(position, DataManager.favoriteScenes.size)
    }

    private fun notifyItemsChanged(deletedScene: SceneInfo, isFavorite: Boolean) {
        val position = DataManager.scenes.indexOf(deletedScene)
        DataManager.scenes[position].isFavorite = isFavorite
    }

    private fun deletedScene(position: Int) {
        DataManager.favoriteScenes.removeAt(position)
        favoritesAdapter.notifyItemRemoved(position)
        favoritesAdapter.notifyItemRangeChanged(position, DataManager.favoriteScenes.size)
    }
}