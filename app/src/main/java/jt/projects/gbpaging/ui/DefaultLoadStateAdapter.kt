package jt.projects.gbpaging.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import jt.projects.gbpaging.databinding.PartDefaultLoadstateBinding

typealias TryAgainAction = () -> Unit

class DefaultLoadStateAdapter(private val tryAgainAction: TryAgainAction) :
    LoadStateAdapter<DefaultLoadStateAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PartDefaultLoadstateBinding.inflate(inflater, parent, false)
        return Holder(binding, null, tryAgainAction)
    }

    // footer + main indicator...
    class Holder(
        private val binding: PartDefaultLoadstateBinding,
        private val swipeRefreshLayout: SwipeRefreshLayout?,
        private val tryAgainAction: TryAgainAction
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnTryAgain.setOnClickListener { tryAgainAction() }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                tvCantLoad.isVisible = loadState is LoadState.Error
                btnTryAgain.isVisible = loadState is LoadState.Error
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.isRefreshing = loadState is LoadState.Loading
                    progressBar.isVisible = false
                    tvLoading.isVisible = false
                } else {
                    progressBar.isVisible = loadState is LoadState.Loading
                    tvLoading.isVisible = loadState is LoadState.Loading
                }
            }

        }

    }


}