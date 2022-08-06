package tech.ericwathome.tours.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import tech.ericwathome.tours.databinding.LoadStateItemBinding

class PhotosLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PhotosLoadStateAdapter.PhotosLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: PhotosLoadStateViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PhotosLoadStateViewHolder {
        val binding =
            LoadStateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotosLoadStateViewHolder(binding, retry)
    }

    inner class PhotosLoadStateViewHolder(
        private val binding: LoadStateItemBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bindState(loadState: LoadState) {
            with(binding) {
                if (loadState is LoadState.Error) {
                    txvLoadError.text = loadState.error.localizedMessage
                }
                txvLoadError.isVisible = loadState !is LoadState.Loading
                progressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
            }
        }

    }

}