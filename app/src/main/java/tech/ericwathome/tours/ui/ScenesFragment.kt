package tech.ericwathome.tours.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import tech.ericwathome.tours.R
import tech.ericwathome.tours.adapter.SceneAdapter
import tech.ericwathome.tours.data.DataManager
import tech.ericwathome.tours.model.viewmodels.ScenesFragmentViewModel
import tech.ericwathome.tours.util.TAG

@AndroidEntryPoint
class ScenesFragment : Fragment() {
    private val viewModel: ScenesFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scenes, container, false)
        initializeSceneList(view)
        return view
    }

    private fun initializeSceneList(view: View?) {
        val context = requireContext()
        val recyclerView = view?.findViewById<RecyclerView>(R.id.scenes_recyclerview)
        recyclerView?.setHasFixedSize(true)
        val adapter = SceneAdapter(context, DataManager.scenes)
        recyclerView?.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView?.layoutManager = layoutManager

        lifecycleScope.launchWhenCreated {
            viewModel.photos
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    Log.d(TAG, "initializeSceneList: $it")
                }
        }
    }
}