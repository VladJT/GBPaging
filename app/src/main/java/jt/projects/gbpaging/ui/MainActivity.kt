package jt.projects.gbpaging.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import jt.projects.gbpaging.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel


    private val tryAgainAction: TryAgainAction = { mainAdapter.retry() }
    private val mainAdapter by lazy { MainPagingAdapter() }
    private val footerAdapter by lazy { DefaultLoadStateAdapter(tryAgainAction) }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AndroidInjection.inject(this)
        initUi()
        observeViewModelData()
        observeLoadState()
        observeLoadingVisible()
    }

    private fun initUi() {
        with(binding.rvNews) {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter.withLoadStateFooter(footerAdapter)
        }
    }

    private fun observeViewModelData() {
//        this.lifecycleScope.launch {
//            this@MainActivity.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel
//                    .resultRecycler
//                    .collect {
//                        mainAdapter.setData(it)
//                    }
//            }
//        }
        lifecycleScope.launch {
            viewModel.newsFlow.collectLatest {
                mainAdapter.submitData(it)
            }
        }
    }

    private fun observeLoadState() {
        lifecycleScope.launch {
            mainAdapter.loadStateFlow.debounce(200).collectLatest {
                //отображаем индикатор, когда подгружаются данные
                binding.progressCircular.isVisible = it.append is LoadState.Loading
            }
        }
    }

    private fun observeLoadingVisible() {
        this.lifecycleScope.launch {
            this@MainActivity.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect {
                    binding.loadingFrameLayout.root.isVisible = it
                }
            }
        }
    }

}