package jt.projects.gbpaging.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jt.projects.gbpaging.intercators.NewsInteractor
import jt.projects.gbpaging.model.News
import jt.projects.gbpaging.utils.LOG_TAG
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val interactor: NewsInteractor) : ViewModel() {

    private var job: Job? = null

    private val _resultRecycler = MutableStateFlow<List<News>>(listOf())
    val resultRecycler get() = _resultRecycler.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading get() = _isLoading.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        job?.cancel()
        _isLoading.tryEmit(true)

        launchOrError(
            action = {
                val data = interactor.getNews()
                _resultRecycler.tryEmit(data)
                _isLoading.tryEmit(false)
            },
            error = {
                _resultRecycler.tryEmit(listOf())
                _isLoading.tryEmit(false)
            }
        )
    }

    private fun launchOrError(
        dispatcher: CoroutineDispatcher = Dispatchers.Main,
        action: suspend () -> Unit,
        error: (Exception) -> Unit
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                action.invoke()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(LOG_TAG, "$e")
                error.invoke(e)
            }
        }
    }

}