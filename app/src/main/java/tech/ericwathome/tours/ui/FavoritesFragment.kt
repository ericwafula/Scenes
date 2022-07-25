package tech.ericwathome.tours.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import tech.ericwathome.tours.R
import tech.ericwathome.tours.adapter.FavoritesAdapter
import tech.ericwathome.tours.data.DataManager
import tech.ericwathome.tours.model.SceneInfo
import java.util.*

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private val TAG = "FavoritesFragment"
    private var recyclerView: RecyclerView? = null
    private lateinit var adapter: FavoritesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        initializeFavoritesList(view)
        return view
    }

    private fun initializeFavoritesList(view: View?) {
        val context = requireContext()
        recyclerView = view?.findViewById(R.id.favorites_recyclerview)
        recyclerView?.setHasFixedSize(true)
        adapter = FavoritesAdapter(context, DataManager.favoriteScenes)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = layoutManager
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val fromPosition = viewHolder.adapterPosition
            val toPosition = target.adapterPosition

            Collections.swap(DataManager.favoriteScenes, fromPosition, toPosition)
            adapter.notifyItemMoved(fromPosition, toPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
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
        adapter.notifyItemInserted(position)
        adapter.notifyItemRangeChanged(position, DataManager.favoriteScenes.size)
    }

    private fun notifyItemsChanged(deletedScene: SceneInfo, isFavorite: Boolean) {
        val position = DataManager.scenes.indexOf(deletedScene)
        DataManager.scenes[position].isFavorite = isFavorite
    }

    private fun deletedScene(position: Int) {
        DataManager.favoriteScenes.removeAt(position)
        adapter.notifyItemRemoved(position)
        adapter.notifyItemRangeChanged(position, DataManager.favoriteScenes.size)
    }
}