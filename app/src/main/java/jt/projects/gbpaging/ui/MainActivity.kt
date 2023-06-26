package jt.projects.gbpaging.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import jt.projects.gbpaging.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private val viewModel by lazy { MainViewModel() }

    private val mainAdapter by lazy { MainAdapter() }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
        observeViewModelData()
        observeLoadingVisible()
    }

    private fun initUi() {
        with(binding.rvNews) {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }
    }

    private fun observeViewModelData() {
        this.lifecycleScope.launch {
            this@MainActivity.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .resultRecycler
                    .collect {
                        mainAdapter.setData(it)
                    }
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